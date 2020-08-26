package com.company.web.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class LoginFormAction  extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
       return new Forward("loginForm",false);
    }
}