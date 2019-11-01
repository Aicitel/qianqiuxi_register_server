package net.qianqiuxi.register.service.impl;

import net.qianqiuxi.register.model.dao.User;
import net.qianqiuxi.register.model.dto.ServiceResponse;
import net.qianqiuxi.register.model.mapper.UserMapper;
import net.qianqiuxi.register.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    public ServiceResponse register(String username, String password) {
        try {
            int result = userMapper.insert(new User(username, password));
            return new ServiceResponse( SUCCEED, ServiceResponse.Status.SUCCEED, "succeed");
        } catch (Exception e){
            //TODO set fail message according to exception
            //if username exists
            boolean exists = true;
            if(exists) {
                return new ServiceResponse(USER_EXIST_ERROR, ServiceResponse.Status.FAIL, "Username exists");
            } else{
                return new ServiceResponse(SERVER_ERROR, ServiceResponse.Status.FAIL, "unknown error");
            }
        }
    }
}
