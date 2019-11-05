package net.qianqiuxi.register.controller;

import net.qianqiuxi.register.auth.annotation.NoneAuth;
import net.qianqiuxi.register.model.dto.ServiceResponse;
import net.qianqiuxi.register.model.request.BaseRequest;
import net.qianqiuxi.register.model.request.UpdateRequest;
import net.qianqiuxi.register.service.LoginService;
import net.qianqiuxi.register.service.InfoService;
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
    private InfoService infoService;

    @Autowired
    private LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(InfoController.class);

    /**
     * Init game token for redis
     * @param updateRequest including init token
     * @param header request header
     * @return Init result
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public ServiceResponse init(
        @RequestBody UpdateRequest updateRequest, @RequestHeader Map<String,String> header){
        if(header.containsKey("token") && header.get("token").equals("233233233233")){
            return infoService.init(updateRequest.getGameToken());
        }
        //TODO change to 400
        return new ServiceResponse(InfoService.INVALID_TOKEN, ServiceResponse.Status.FAIL, "");
    }

    /**
     * Base update endpoint.
     * @param updateRequest including base info
     * @param header request header
     * @return Update result
     */
    @NoneAuth
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ServiceResponse update(
            @RequestBody UpdateRequest updateRequest, @RequestHeader Map<String,String> header){
        if(header.containsKey("token")){
            ServiceResponse serviceResponse = loginService.auth(updateRequest.getUsername(), String.valueOf(header.get("token")));
            if(Objects.equals(ServiceResponse.Status.SUCCEED, serviceResponse.getStatus())) {
                return infoService.update(updateRequest);
            }
        }
        //TODO change to 400
        return new ServiceResponse(InfoService.AUTH_FAIL, ServiceResponse.Status.FAIL, "");
    }


    /**
     * Base register endpoint.
     * @param infoRequest including base info
     * @param header request header
     * @return get info result
     */
    @NoneAuth
    @RequestMapping(value = "/game", method = RequestMethod.POST)
    public ServiceResponse getWinLoseTitle(
            @RequestBody BaseRequest infoRequest, @RequestHeader Map<String,String> header){
        if(header.containsKey("token")){
            ServiceResponse serviceResponse = loginService.auth(infoRequest.getUsername(), String.valueOf(header.get("token")));
            if(Objects.equals(ServiceResponse.Status.SUCCEED, serviceResponse.getStatus())) {
                return infoService.getInfo(infoRequest);
            }
        }
        //TODO change to 400
        return new ServiceResponse(InfoService.AUTH_FAIL, ServiceResponse.Status.FAIL, "");
    }
}
