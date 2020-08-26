package com.company.web.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.service.MainServiceFactory;
import com.company.model.service.ServiceException;
import com.company.model.service.UserService;
import com.company.model.user.Email;
import com.company.model.user.User;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class RegisterAction extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String surname=request.getParameter("surname");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        if(name.length() < 2 ) {
            request.setAttribute("message", "message.write.your.name");
            return new Forward("registerForm",false);
        }else if(surname.length() < 2) {
            request.setAttribute("message", "message.write.your.surname");
            return new Forward("registerForm",false);
        }else if(password.length() < 2) {
           request.setAttribute("message", "message.write.your.password");
           return new Forward("registerForm",false);
        }else {
            Email email1 = new Email(email);
            if(email1.isEmail()) {
            HttpSession session= request.getSession(true); 
            MainServiceFactory serviceFactory = new MainServiceFactory();
            UserService userService = serviceFactory.getUserService();
            User user = new User();
            user.setEmail(email);
            user.setSurname(surname);
            user.setPassword(password);
            user.setName(name);
            try {
                user = userService.create(user);
            } catch (ServiceException e) {
                logger.error(e);
            }
            String role = user.getRole();
            if(role == null) {
            request.setAttribute("message", "register.wrong");
            }else {
                request.setAttribute("message", "register.welcome");
                request.setAttribute("user", user);
                session.setAttribute("user",user);
            }
            serviceFactory.close();
            return new Forward("registerForm",false);
            }else {
                request.setAttribute("message", "message.wrong.email");
                return new Forward("registerForm",false);
             }	
        }   
    }
}