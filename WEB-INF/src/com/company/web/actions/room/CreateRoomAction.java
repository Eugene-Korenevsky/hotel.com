package com.company.web.actions.room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.room.Room;
import com.company.model.service.MainServiceFactory;
import com.company.model.service.RoomService;
import com.company.model.service.ServiceException;
import com.company.model.user.User;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class CreateRoomAction extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session= request.getSession();
        User user = (User) session.getAttribute("user");
        String sitsString = request.getParameter("sits");
        String numberString = request.getParameter("number");
        String priceString = request.getParameter("price");
        String comfort = request.getParameter("class");
        if(user != null) {
            if(sitsString.length() < 1 || numberString.length() < 1 || priceString.length() <1) {
                request.setAttribute("message", "message.put.values.in.fields");
                return new Forward("createRoomForm",false);
            }else {
                try {
                    int sits = Integer.parseInt(request.getParameter("sits"));
                    int number = Integer.parseInt(request.getParameter("number"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    Room room = new Room();
                    room.setComfort(comfort);
                    room.setNumber(number);
                    room.setPrice(price);
                    room.setSits(sits);
                    MainServiceFactory mainServiceFactory = new MainServiceFactory();
                    RoomService roomService = mainServiceFactory.getRoomService();
                    try {
                        roomService.createRoom(room);
                    } catch (ServiceException e) {
                        logger.error(e);
                    }
                    mainServiceFactory.close();
                    return new Forward("/rooms.html",true);
                }catch(NumberFormatException e) {
                    request.setAttribute("message", "message.un.correct.data");
                    return new Forward("createRoomForm",false);
                 }
            }
        }else {
            return new Forward("loginForm",false);
        }
    }
}