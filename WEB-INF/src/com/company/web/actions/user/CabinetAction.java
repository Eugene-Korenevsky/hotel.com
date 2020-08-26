package com.company.web.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.user.User;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class CabinetAction  extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session= request.getSession();
        User user=(User) session.getAttribute("user");	
        if(user == null) {
            user = new User();
            user.setRole("guest");
        }
        request.setAttribute("user", user);
        return new Forward("cabinet",false);
    }
}