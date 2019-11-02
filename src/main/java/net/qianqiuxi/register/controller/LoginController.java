package net.qianqiuxi.register.controller;

import net.qianqiuxi.register.auth.annotation.NoneAuth;
import net.qianqiuxi.register.model.dto.ServiceResponse;
import net.qianqiuxi.register.model.request.LoginRequest;
import net.qianqiuxi.register.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * Base login endpoint.
     * @param loginRequest including base info
     * @param header request header
     * @return Register result
     */
    @NoneAuth
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServiceResponse login(
            @RequestBody LoginRequest loginRequest, @RequestHeader Map<String,String> header){
        return loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }

    /**
     * Base login endpoint.
     * @param loginRequest including base info
     * @param header request header
     * @return Register result
     */
    @NoneAuth
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ServiceResponse auth(
            @RequestBody LoginRequest loginRequest, @RequestHeader Map<String,String> header){
        return loginService.auth(loginRequest.getUsername(), loginRequest.getToken());
    }
}
