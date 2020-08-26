package com.company.web.actions.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.room.Room;
import com.company.model.service.MainServiceFactory;
import com.company.model.service.RoomService;
import com.company.model.service.ServiceException;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class FindRoomAction extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
    long  id =Long.parseLong(request.getParameter("id")) ;
    HttpSession session= request.getSession(true);
    MainServiceFactory mainServiceFactory = new MainServiceFactory();
    RoomService roomService = mainServiceFactory.getRoomService();
    Room room = null;
    try {
        room = roomService.findById(id);
    } catch (ServiceException e) {
        logger.error(e);
    }
    session.setAttribute("roomId",id);
    request.setAttribute("room",room);
    mainServiceFactory.close();
    return new Forward("roomsfind",false);
    }
}
