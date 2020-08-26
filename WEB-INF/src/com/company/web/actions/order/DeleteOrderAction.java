package com.company.web.actions.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.service.MainServiceFactory;
import com.company.model.service.OrderService;
import com.company.model.service.ServiceException;
import com.company.model.user.User;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class DeleteOrderAction extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session= request.getSession();
        User user = (User) session.getAttribute("user");
        if( user != null) {
            MainServiceFactory mainServiceFactory = new MainServiceFactory();
            OrderService orderService = mainServiceFactory.getOrderService();
            long orderId = Long.parseLong(request.getParameter("id"));
            try {
                orderService.delete(orderId);
            } catch (ServiceException e) {
                logger.error(e);
            }finally {
                mainServiceFactory.close();
            }
             return new Forward("/orders.html",true);
        }else {
             return new Forward("loginForm",false);
        }
    }
}