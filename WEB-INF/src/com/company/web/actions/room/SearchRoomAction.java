package com.company.web.actions.room;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.model.order.CurrentDate;
import com.company.model.order.Reserve;
import com.company.model.room.Room;
import com.company.model.room.SearchRoom;
import com.company.model.service.MainServiceFactory;
import com.company.model.service.ReserveService;
import com.company.model.service.RoomService;
import com.company.model.service.ServiceException;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class SearchRoomAction extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        Forward forward = null;
        Timestamp dateIn;
        Timestamp dateOut;
        String in = request.getParameter("dayIn");
        String out = request.getParameter("dayOut");
        String timeIn = request.getParameter("timeIn");
        String timeOut = request.getParameter("timeOut");
        String sits = request.getParameter("sits");
        String price = request.getParameter("price");  
        if(out.length() < 1 || in.length() < 1) {
            request.setAttribute("message","message.nodates.input");
            forward = new Forward("uncorrect",false);			   
        }else if(timeIn.length() < 3) {
            request.setAttribute("message","timeIn.wrong");
            forward = new Forward("uncorrect",false);
        }else if(timeOut.length() < 3) {
            request.setAttribute("message","timeOut.wrong");
            forward = new Forward("uncorrect",false);
        }else if(sits.length()<1) {
            request.setAttribute("message","write sits");
            forward = new Forward("uncorrect",false);
        }else if(price.length()<1) {
            request.setAttribute("message","write price");
            forward = new Forward("uncorrect",false);
        }else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(in).append(" ").append(timeIn).append(":00");
            dateIn = Timestamp.valueOf(stringBuilder.toString());
            stringBuilder.delete(0,stringBuilder.length());
            stringBuilder.append(out).append(" ").append(timeOut).append(":00");
            dateOut = Timestamp.valueOf(stringBuilder.toString());
            long inLong = dateIn.getTime();
            long outLong = dateOut.getTime();
            CurrentDate date = new CurrentDate();
            long current = date.getCurrentDate();
            ArrayList<Room> res = new ArrayList<>();
            if(inLong > outLong || inLong < current) {
                request.setAttribute("message","message.uncorrect.dates");
                forward = new Forward("uncorrect",false); 
            }else {
                MainServiceFactory mainServiceFactory = new MainServiceFactory();
                ReserveService reserveService = mainServiceFactory.getReserveService();
                RoomService roomService = mainServiceFactory.getRoomService();
                List<Reserve> reserves = new ArrayList<Reserve>();
                List<Room> rooms = new ArrayList<>();
                    try {
                        reserves = reserveService.readAll();
                        rooms = roomService.readAll();
                        } catch (ServiceException e1) {
                        logger.error(e1);
                        }
                        mainServiceFactory.close();
                        try {
                            int sitsInt = Integer.parseInt(sits);
                            double priceDouble = Double.parseDouble(price);
                            if (priceDouble <= 0) {
                                request.setAttribute("message","wrong data");
                                forward = new Forward("uncorrect",false);
                            }else {
                                SearchRoom search = new SearchRoom(rooms,reserves,dateIn,dateOut,priceDouble,sitsInt);
                                res = (ArrayList<Room>) search.doSearch();
                                request.setAttribute("result",res);
                                forward = new Forward("rooms",false);	
                            }					        
                         }catch(NumberFormatException e) {
                             request.setAttribute("message","wrong data");
                             forward = new Forward("uncorrect",false); 	
                         }													         
              }	       
         }
         return forward; 
    }
}