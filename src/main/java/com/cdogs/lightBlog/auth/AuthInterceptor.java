package com.cdogs.lightBlog.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cdogs.lightBlog.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.PrintWriter;
import java.net.URLEncoder;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("5555555555555555");
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);

            //没有声明需要权限,或者声明不验证权限
            if(authPassport == null || authPassport.validate() == false)
                return true;
            else{
                //在这里实现自己的权限验证逻辑
/*                String contextPath=request.getContextPath();
                String  url=request.getServletPath().toString();*/
                HttpSession session = request.getSession();
                System.out.println("888888888888888");
                User user = (User) session.getAttribute("user");

                //这里可以根据session的用户来判断角色的权限，根据权限来重定向不同的页面，简单起见，这里只是做了一个重定向
/*                if (StringUtils.isEmpty(user)) {
                    //被拦截，重定向到login界面
                    response.sendRedirect(contextPath+"/login.htm?redirectURL="
                            + URLEncoder.encode(url));
                    return false;
                }
                return true;*/
                System.out.println(user);
                if(null != user)//如果验证成功返回true（这里直接写false来模拟验证失败的处理）
                    return true;
                else//如果验证失败
                {
                    //返回到登录界面
                    //response.sendRedirect("account/login");
                    response.setCharacterEncoding("utf-8");
                    PrintWriter out = response.getWriter();
                    out.print("LOGOUT");
                    out.flush();
                    return false;
                }
            }
        }
        else
            return true;
    }
}