package com.pxc.interraptor;

import com.pxc.user.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by PXC on 2017/6/23.
 * springMVC的拦截器
 */
public class IndexInterRaptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri=request.getRequestURI();
        if(uri.indexOf("post")>=0){
            return  true;
        }else {
            HttpSession session=request.getSession();
            User user= (User) session.getAttribute("user");
            if(user!=null){
                return true;
            }else {

                request.getRequestDispatcher("/post").forward(request,response);
                return  false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
