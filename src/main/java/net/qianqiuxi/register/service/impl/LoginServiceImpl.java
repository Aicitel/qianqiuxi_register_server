package net.qianqiuxi.register.service.impl;

import net.qianqiuxi.register.auth.TokenManager;
import net.qianqiuxi.register.auth.token.Token;
import net.qianqiuxi.register.model.dao.User;
import net.qianqiuxi.register.model.dto.LoginResponse;
import net.qianqiuxi.register.model.dto.ServiceResponse;
import net.qianqiuxi.register.model.mapper.UserMapper;
import net.qianqiuxi.register.service.LoginService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenManager tokenManager;

    public ServiceResponse login(String username, String password) {
        try {
            Integer userId = userMapper.checkAuth(new User(username, password));
            if(userId!=null) {
                return new LoginResponse(SUCCEED, ServiceResponse.Status.SUCCEED, "succeed", tokenManager.createTokenForUser(userId).getToken());
            } else {
                return new ServiceResponse(NOT_MATCH, ServiceResponse.Status.FAIL, "Username and password not match");
            }
        }catch (MyBatisSystemException e){
            return new ServiceResponse(SERVER_ERROR, ServiceResponse.Status.FAIL, "unknown error");
        }
    }

    @Override
    public ServiceResponse auth(String username, String tokenStr) {
        try {
            Integer userId = userMapper.getUserId(new User(username, null));
            if(tokenManager.checkTokenForUser(new Token(userId, tokenStr))){
                return new LoginResponse(SUCCEED, ServiceResponse.Status.SUCCEED, "succeed",tokenStr);
            } else {
                return new ServiceResponse(TOKEN_NOT_VALID, ServiceResponse.Status.FAIL, "Auth Failed");
            }
        }catch (MyBatisSystemException e){
            return new ServiceResponse(SERVER_ERROR, ServiceResponse.Status.FAIL, "unknown error");
        }
    }
}
