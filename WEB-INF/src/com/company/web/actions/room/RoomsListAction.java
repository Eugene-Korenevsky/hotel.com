package com.company.web.actions.room;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.company.model.room.Room;
import com.company.model.service.MainServiceFactory;
import com.company.model.service.RoomService;
import com.company.model.service.ServiceException;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class RoomsListAction  extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(RoomsListAction.class);
        MainServiceFactory mainServiceFactory = new MainServiceFactory();
        RoomService roomService = mainServiceFactory.getRoomService();
        logger.info("hello");
        ArrayList<Room> rooms = null;
            try {
                rooms = (ArrayList<Room>) roomService.readAll();
            } catch (ServiceException e) {
                logger.error(e);
            }
            mainServiceFactory.close();
            request.setAttribute("result",rooms);
        return new Forward("rooms",false);
    }
}