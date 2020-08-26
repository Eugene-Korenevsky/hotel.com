package com.company.web.actions.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.order.Order;
import com.company.model.service.MainServiceFactory;
import com.company.model.service.OrderService;
import com.company.model.service.ServiceException;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class ConfirmOrderAction extends Action{
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        Forward forward = null;
        HttpSession session= request.getSession();
        Order order = (Order) session.getAttribute("order");
        if (order != null) {
            MainServiceFactory mainServiceFactory = new MainServiceFactory();
            OrderService orderService = mainServiceFactory.getOrderService();
            try {
                orderService.create(order);
                forward = new Forward("/rooms.html",true);
            } catch (ServiceException e) {
                logger.error(e);
            }finally {
                mainServiceFactory.close();
            }
        }else {
           forward = new Forward("loginForm",false);
        }
        return forward;
    }
}