package com.company.web.actions.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class CreateRoomFormAction extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        return new Forward("/createRoomForm",false);
    }
}