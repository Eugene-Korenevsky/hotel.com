package com.company.web.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.service.MainServiceFactory;
import com.company.model.service.ServiceException;
import com.company.model.service.UserService;
import com.company.model.user.User;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class LoginAction extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        Forward forward = null;
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        MainServiceFactory serviceFactory = new MainServiceFactory();
        UserService userService = serviceFactory.getUserService();
        User user = new User();
        try {
            user = userService.loginUser(password, email);
        } catch (ServiceException e) {
            logger.error(e);
        }
        serviceFactory.close();
        HttpSession session= request.getSession(true); 
        String role =  user.getRole();
        if(role != null) {
            request.setAttribute("user",user);
            request.setAttribute("message","login.welcome");
            session.setAttribute("user",user);
            forward = new Forward("user",false); 
        }else {        			
            request.setAttribute("message","login.wrong");
            forward = new Forward("loginForm",false);
        }
        request.setAttribute("user",user);       
        return forward;   	 
     }
}