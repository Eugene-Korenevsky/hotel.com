package com.company.web.actions.reserve;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.order.Order;
import com.company.model.order.Reserve;
import com.company.model.service.MainServiceFactory;
import com.company.model.service.OrderService;
import com.company.model.service.ReserveService;
import com.company.model.service.ServiceException;
import com.company.model.user.User;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class ConfirmReserveAction extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        boolean found = false;
        HttpSession session= request.getSession();
        User user = (User) session.getAttribute("user");
        if( user != null) {
             MainServiceFactory mainServiceFactory = new MainServiceFactory();
             OrderService orderService = mainServiceFactory.getOrderService();
             ReserveService reserveService = mainServiceFactory.getReserveService();
             List<Reserve> reserves = new ArrayList<Reserve>();
             long orderId = Long.parseLong(request.getParameter("id"));
                try {
                    reserves = reserveService.readAll();
                } catch (ServiceException e1) {
                    logger.error(e1);
                }
                Order order = new Order();
                try {
                    order = orderService.readOrderById(orderId);
                } catch (ServiceException e) {
                    logger.error(e);
                }
                mainServiceFactory.close();
                long inLong = order.getDateIn().getTime();
                long outLong = order.getDateOut().getTime();
                for(Reserve reserve : reserves) {
                    if(reserve.getRoomId() == order.getRoomId()) {
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
                     return new Forward("orders",false);
                 }else {
                     try {
                         orderService.doReserve(orderId);
                     } catch (ServiceException e) {
                         logger.error(e);
                     }
                       return new Forward("/orders.html",true);
                  }
            }else {
                return new Forward("loginForm",false);
            }
    }
}