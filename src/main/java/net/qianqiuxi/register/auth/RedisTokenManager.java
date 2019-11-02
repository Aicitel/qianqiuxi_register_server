package net.qianqiuxi.register.auth;

import net.qianqiuxi.register.auth.token.Token;
import net.qianqiuxi.register.repo.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class RedisTokenManager implements TokenManager {

    private static final Logger logger = LoggerFactory.getLogger(RedisTokenManager.class);

    @Autowired
    private RedisClient redisClient;

    private String createToken(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public Token createTokenForUser(Integer id) {
        if(id == null){
            throw new RuntimeException("User Id is null, please double check");
        }
        Token token = new Token(id, this.createToken());
        if(redisClient.saveKeyValue(String.valueOf(id), token.getToken(), RedisClient.TOKEN_EXPIRES_SECOND)){
            return token;
        } else {
            throw new RuntimeException("Token saved fail");
        }
    }

    @Override
    public boolean checkTokenForUser(Token token) {
        if(token != null && token.getToken()!=null && token.getUserId()!=null) {
            String userId = String.valueOf(token.getUserId());
            if(Objects.equals(redisClient.getValueByKey(userId), token.getToken())){
                //refresh the token expire time
                redisClient.updateExpireTimeForKey(userId, RedisClient.TOKEN_EXPIRES_SECOND);
                return true;
            }
        }
        return false;
    }

}
