package com.sxy.zongce.interceptor;

import com.sxy.zongce.exception.ResponseCode;
import com.sxy.zongce.exception.ResponseRes;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object stu_obj = request.getSession().getAttribute("stu_Id");
        Object menter_obj = request.getSession().getAttribute("menter_Id");
        Object admin_obj = request.getSession().getAttribute("admin_Id");
        if(stu_obj == null && menter_obj == null && admin_obj == null){
            response.sendRedirect("/html/login.html");
            return false;
        }
        return true;
    }
}
