package net.qianqiuxi.register.service;

import net.qianqiuxi.register.model.dto.ServiceResponse;

public interface RegisterService {

    /**
     * Register response code begins with 2330
     */
    int SUCCEED = 233000;
    int USER_EXIST_ERROR = 233001;
    int SERVER_ERROR = 233002;

    /**
     * Register a user with name & pwd
     * @param username
     * @param password
     * @return register result
     */
    ServiceResponse register(String username, String password);
}
