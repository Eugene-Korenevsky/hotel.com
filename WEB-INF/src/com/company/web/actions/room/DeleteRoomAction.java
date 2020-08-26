package com.company.web.actions.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.service.MainServiceFactory;
import com.company.model.service.RoomService;
import com.company.model.service.ServiceException;
import com.company.model.user.User;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class DeleteRoomAction extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session= request.getSession();
        User user = (User) session.getAttribute("user");
        if( user != null) {
            long id = Long.parseLong(request.getParameter("id")); 
            MainServiceFactory mainServiceFactory = new MainServiceFactory();
            RoomService roomService = mainServiceFactory.getRoomService();
            try {
                roomService.deleteRoom(id);
            } catch (ServiceException e) {
                 logger.error(e);
            }
            mainServiceFactory.close();
            return  new Forward("/rooms.html",true);
            }else {
                return new Forward("loginForm",false);
            }
        }
}