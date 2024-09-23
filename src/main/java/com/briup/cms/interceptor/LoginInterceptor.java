package com.briup.cms.interceptor;

import com.briup.cms.util.BriupAssert;
import com.briup.cms.util.JwtUtil;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.briup.cms.util.ResultCode.USER_NOT_LOGIN;

/**
 * @author zzx
 * @version 1.0
 * @date 2024-08-28 19:37
 */

/**
 * 登录拦截器，当用户访问资源时，进行拦截
 * 当拦截到基于请求url拦截时，自动调用拦截器中方法
 * 配置拦截器的规则：
 */
public class LoginInterceptor implements HandlerInterceptor {
    //调用处理器方法前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.当请求路径是/login 表示正在登录，不需要身份校验，提供拦截通过 return true
        //2.当请求路径是/login 不需要拦截该路径，在配置类中添加排除路径
        String uri = request.getRequestURI();
        System.out.println("拦截路径：" + uri);
        if ("/login".equals(uri)){
            return true;
        }
        //3.拦截路径是非登录路径。必须先通过验证，然后才能访问资源

        //4.如何获取token信息？通过request对象获取请求头信息
        String token = request.getHeader("token");
        System.out.println("token--------------"+token);
        /*if (token == null) {//5.判断是否有token?  没有token,提示用户未登录
            throw new CustomException(USER_NOT_LOGIN);
        }*/
        BriupAssert.notNull(token,USER_NOT_LOGIN);
        JwtUtil.checkSign(token);//不合法token 提示用户重新登录

        /*
            5.判断是否有token?  没有token,提示用户未登录
                是否合法?       不合法token 提示用户重新登录
         */


        //6.拦截验证通过，可以继续访问业务资源
        return true;//拦截通过，可以继续访问资源
        //return false;//拦截不通过，不能继续访问资源

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //System.out.println("postHandle");
        //在处理器调用方法后执行
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //System.out.println("afterCompletion");
        //完成整个请求和响应后执行
    }
}
