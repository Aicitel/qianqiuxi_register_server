package net.qianqiuxi.register.service.impl;

import net.qianqiuxi.register.model.dao.User;
import net.qianqiuxi.register.model.dto.ServiceResponse;
import net.qianqiuxi.register.model.mapper.UserDetailMapper;
import net.qianqiuxi.register.model.mapper.UserMapper;
import net.qianqiuxi.register.service.RegisterService;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailMapper userDetailMapper;

    public ServiceResponse register(String username, String password) {
        try {
            User user = new User(username, password);
            int result = userMapper.insert(user);
            int detailResult = userDetailMapper.insert(user.getId());
            return new ServiceResponse( SUCCEED, ServiceResponse.Status.SUCCEED, "succeed");
        } catch (DuplicateKeyException e){
            return new ServiceResponse(USER_EXIST_ERROR, ServiceResponse.Status.FAIL, "Username exists");
        } catch (MyBatisSystemException e){
            return new ServiceResponse(SERVER_ERROR, ServiceResponse.Status.FAIL, "unknown error");
        }
    }
}
