package com.briup.cms.web.interceptor;

import com.briup.cms.exception.CustomException;
import com.briup.cms.util.BriupAssert;
import com.briup.cms.util.JwtUtil;
import com.briup.cms.util.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt登录验证，验证用户是否有权限访问资源
 */
@Slf4j
@Component //创建拦截器对象
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求方式是预检请求布不拦截Options
        if (request.getMethod().equals("OPTIONS")){
            return true;
        }
        log.info("请求的路径：{}",request.getRequestURI());
        //当用户登录请求时，直接拦截通过
        if("/login".equals(request.getRequestURI())){
            return true;
        }
        System.out.println(request.getRequestURI());
        //核心功能：获取请求头中token字符串，验证是否合法 是否存在
        String token = request.getHeader("token");
        System.out.println(token+"--------------------");
        //当token为空，用户未登录直接访问资源，拦截不通过，提示用户请登录
        BriupAssert.notNull(token, ResultCode.USER_NOT_LOGIN);
        //当token不为空，验证是否合法的身份信息
        try{
            JwtUtil.checkSign(token);
        }catch (Exception ex){
            throw new CustomException(ResultCode.USER_TOKEN_FAILURE);
        }
        return true;//默认通过拦截
    }
}
