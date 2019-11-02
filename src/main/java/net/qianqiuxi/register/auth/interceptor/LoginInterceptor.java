package net.qianqiuxi.register.auth.interceptor;

import net.qianqiuxi.register.auth.TokenManager;

import net.qianqiuxi.register.auth.annotation.NoneAuth;
import net.qianqiuxi.register.auth.token.Token;
import net.qianqiuxi.register.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true;
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        if(method.getAnnotation(NoneAuth.class) != null) return true;
//        String authStr = request.getHeader(Constants.NEED_AUTH);
//        TODO check auth valid
//
//        if(tokenManager.checkTokenForUser(authStr)) {
//        return false;
    }
}