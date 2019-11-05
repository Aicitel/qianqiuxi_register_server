package net.qianqiuxi.register.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.qianqiuxi.register.model.dao.Game;
import net.qianqiuxi.register.model.dao.User;
import net.qianqiuxi.register.model.dao.UserDetail;
import net.qianqiuxi.register.model.dto.InfoResponse;
import net.qianqiuxi.register.model.dto.InitResponse;
import net.qianqiuxi.register.model.dto.ServiceResponse;
import net.qianqiuxi.register.model.mapper.GameMapper;
import net.qianqiuxi.register.model.mapper.UserDetailMapper;
import net.qianqiuxi.register.model.mapper.UserMapper;
import net.qianqiuxi.register.model.request.BaseRequest;
import net.qianqiuxi.register.model.request.UpdateRequest;
import net.qianqiuxi.register.repo.RedisClient;
import net.qianqiuxi.register.service.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

import static net.qianqiuxi.register.repo.RedisClient.GAME_TOKEN_EXPIRES_SECOND;

@Service
public class InfoServiceImpl implements InfoService {

    private static final Logger logger = LoggerFactory.getLogger(InfoServiceImpl.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailMapper userDetailMapper;

    @Autowired
    private RedisClient redisClient;

    @Override
    public ServiceResponse init(String gameToken) {
//        String gameToken = UUID.randomUUID().toString().replaceAll("-", "");
        if(redisClient.saveKeyValue(gameToken, "init",GAME_TOKEN_EXPIRES_SECOND )){
            //only insert into game table, no touch user table to avoid overhead
            gameMapper.insert(gameToken);
            return new InitResponse(SUCCEED, ServiceResponse.Status.SUCCEED, "init succeed", gameToken);
        } else {
            return new ServiceResponse(SERVER_ERROR, ServiceResponse.Status.FAIL, "init failed");
        }
    }

    @Override
    public ServiceResponse update(UpdateRequest updateRequest) {
        //TODO need generate a match key when a match begins, and update the key when match finished
        //TODO check whether redis contains another part, if true, construct a game and insert into db
        //TODO get the pair userIds and sort , use the sorted key as redis key to find another part
        boolean match = false;
        if(redisClient.getValueByKey(updateRequest.getGameToken()) == null){
            return new ServiceResponse(INVALID_TOKEN, ServiceResponse.Status.FAIL, "save game fail");
        }
        synchronized (this) {
            if (Objects.equals(redisClient.getValueByKey(updateRequest.getGameToken()),INIT)) {
                //if game result token unsaved, same the one side token
                try {
                    redisClient.saveKeyValue(updateRequest.getGameToken(), objectMapper.writeValueAsString(updateRequest), GAME_TOKEN_EXPIRES_SECOND);
                } catch (JsonProcessingException je) {
                    logger.error("save game token fail {}", updateRequest.toString());
                    return new ServiceResponse(SERVER_ERROR, ServiceResponse.Status.FAIL, "save game fail");
                }
            } else {
                //if game result token saved, check whether it matches
                try {
                    UpdateRequest savedUpdateRequest = objectMapper.readValue(redisClient.getValueByKey(updateRequest.getGameToken()), UpdateRequest.class);
                    //if matches, save into mysql and remove the record in redis
                    if(this.match(savedUpdateRequest, updateRequest)) {
                        Integer player1Id = userMapper.getUserIdByName(updateRequest.getUsername());
                        Integer player2Id = userMapper.getUserIdByName(updateRequest.getOpponent());
                        Game game = new Game(player1Id, player2Id, updateRequest.isWin()?0:1, updateRequest.getGameToken());
                        gameMapper.update(game);
                        userDetailMapper.updateWin(new UserDetail(updateRequest.isWin()?player1Id:player2Id));
                        userDetailMapper.updateLose(new UserDetail(updateRequest.isWin()?player2Id:player1Id));
                        redisClient.removeKeyValue(savedUpdateRequest.getGameToken());
                    } else {
                        return new ServiceResponse(INVALID_TOKEN, ServiceResponse.Status.FAIL, "save game fail");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    logger.error("save game token fail {}", updateRequest.toString());
                    return new ServiceResponse(SERVER_ERROR, ServiceResponse.Status.FAIL, "update game fail");
                }
            }
        }
        return new ServiceResponse(SUCCEED, ServiceResponse.Status.SUCCEED, "save game succeed");
    }

    @Override
    public ServiceResponse getInfo(BaseRequest baseRequest) {
        return new InfoResponse(SUCCEED, ServiceResponse.Status.SUCCEED, ""
            ,new InfoResponse.WinLoseTitleWrapper(
                    userDetailMapper.get(userMapper.getUserIdByName(baseRequest.getUsername()))
                //TODO implement title
                    ,"--"));
    }

    private boolean match(UpdateRequest updateRequest1, UpdateRequest updateRequest2){
        return Objects.equals(updateRequest1.getGameToken(), updateRequest2.getGameToken())
                &&Objects.equals(updateRequest1.getUsername(), updateRequest2.getOpponent())
                &&Objects.equals(updateRequest1.getOpponent(), updateRequest2.getUsername())
                &&( 1 == (updateRequest1.isWin()?1:0) + (updateRequest2.isWin()?1:0));
    }
}
