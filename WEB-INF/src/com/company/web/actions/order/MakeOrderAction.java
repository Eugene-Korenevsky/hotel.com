package com.company.web.actions.order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.order.CurrentDate;
import com.company.model.order.Order;
import com.company.model.order.Reserve;
import com.company.model.room.Room;
import com.company.model.room.TotalPrice;
import com.company.model.service.MainServiceFactory;
import com.company.model.service.ReserveService;
import com.company.model.service.RoomService;
import com.company.model.service.ServiceException;
import com.company.model.user.User;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class MakeOrderAction extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
       long userId;
       long roomId;
       Timestamp dateIn;
       Timestamp dateOut;
       Forward forward = null;
       boolean found = false;
            HttpSession session= request.getSession();
            User user=(User) session.getAttribute("user");
       if(user != null) {
              userId = user.getId();
              roomId = Long.parseLong(request.getParameter("roomId"));
              String in = request.getParameter("dayIn");
              String out = request.getParameter("dayOut");
              String timeIn = request.getParameter("timeIn");
              String timeOut = request.getParameter("timeOut");
       if(out.length() < 1 || in.length() < 1) {
                  request.setAttribute("message","message.nodates.input"); 
                  forward = new Forward("uncorrect",false);
       }else if(timeIn.length() < 3) {
           request.setAttribute("message","timeIn.wrong"); 
           forward = new Forward("uncorrect",false);
       }else if(timeOut.length() < 3) {
          request.setAttribute("message","timeOut.wrong"); 
          forward = new Forward("uncorrect",false);
       } else {
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
          if(inLong > outLong || inLong < current) {
          request.setAttribute("message","message.uncorrect.dates");
          forward = new Forward("uncorrect",false); 
          }else {
          MainServiceFactory mainServiceFactory = new MainServiceFactory();
               ReserveService reserveService = mainServiceFactory.getReserveService();
               RoomService roomService = mainServiceFactory.getRoomService();
               List<Reserve> reserves = new ArrayList<Reserve>();
                 try {
                     reserves = reserveService.readAll();
                 } catch (ServiceException e1) {
                    logger.error(e1);
                 }
                    for(Reserve reserve : reserves) {
                       if(reserve.getRoomId() == roomId) {
                            long in1 = reserve.getDateIn().getTime();
                            long out1 = reserve.getDateOut().getTime();
                            if((inLong > in1 && inLong < out1)||(outLong > in1 && outLong < out1  )) {
                                found = true;
                                break;
                             }
                        }
                    }
                    if (found) {
                        request.setAttribute("message","message.is.already.reserved");
                        forward = new Forward("uncorrect",false);
                    }else {
                         Order order = new Order();
                         order.setDateIn(dateIn);
                         order.setDateOut(dateOut);
                         order.setUserId(userId);
                         order.setRoomId(roomId);
                         session.setAttribute("order", order);
                         Room room = new Room();
                            try {
                                room = roomService.findById(roomId);
                            } catch (ServiceException e) {
                              logger.error(e);
                            }
                          TotalPrice totalPrice = new TotalPrice(dateIn,dateOut,room.getPrice());
                          String totalPriceOut = totalPrice.totalPrice();
                          request.setAttribute("price",totalPriceOut);
                          mainServiceFactory.close();
                          forward = new Forward("dates",false);
                     }       
            }
        }
        }else {
              forward = new Forward("loginForm",false);
        }
              return forward;
     }
}