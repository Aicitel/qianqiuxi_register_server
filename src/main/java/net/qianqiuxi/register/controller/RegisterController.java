package net.qianqiuxi.register.controller;

import net.qianqiuxi.register.model.dto.ServiceResponse;
import net.qianqiuxi.register.model.request.RegisterRequest;
import net.qianqiuxi.register.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/info")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    /**
     * Base register endpoint.
     * @param registerRequest including base info
     * @param header request header
     * @return Register result
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ServiceResponse register(
            @RequestBody RegisterRequest registerRequest, @RequestHeader Map<String,String> header){
        return registerService.register(registerRequest.getUsername(), registerRequest.getPassword());
    }
}
