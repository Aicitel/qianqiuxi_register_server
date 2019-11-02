package net.qianqiuxi.register.controller;

import net.qianqiuxi.register.auth.annotation.NoneAuth;
import net.qianqiuxi.register.model.dto.ServiceResponse;
import net.qianqiuxi.register.model.request.RegisterRequest;
import net.qianqiuxi.register.model.request.UpdateRequest;
import net.qianqiuxi.register.service.LoginService;
import net.qianqiuxi.register.service.UpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/infos")
public class InfoController {

    @Autowired
    private UpdateService updateService;

    @Autowired
    private LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(InfoController.class);

    /**
     * Base register endpoint.
     * @param updateRequest including base info
     * @param header request header
     * @return Register result
     */
    @NoneAuth
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ServiceResponse register(
            @RequestBody UpdateRequest updateRequest, @RequestHeader Map<String,String> header){
        if(header.containsKey("token")){
            ServiceResponse serviceResponse = loginService.auth(updateRequest.getUsername(), String.valueOf(header.get("token")));
            if(Objects.equals(ServiceResponse.Status.SUCCEED, serviceResponse.getStatus())) {
                return updateService.update(updateRequest);
            }
        }
        //TODO change to 400
        return new ServiceResponse(UpdateService.AUTH_FAIL, ServiceResponse.Status.FAIL, "");
    }

}
