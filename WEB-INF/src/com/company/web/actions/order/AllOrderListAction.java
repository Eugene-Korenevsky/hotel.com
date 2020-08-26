package com.company.web.actions.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.order.Order;
import com.company.model.service.MainServiceFactory;
import com.company.model.service.OrderService;
import com.company.model.service.ServiceException;
import com.company.model.user.User;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class AllOrderListAction extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = new ArrayList<Order>(); 
        Forward forward = null;
        MainServiceFactory mainServiceFactory = new MainServiceFactory();
        OrderService orderService = mainServiceFactory.getOrderService();
        HttpSession session= request.getSession();
        User user=(User) session.getAttribute("user");
            if(user != null) {
                 if(user.getRole().equals("user")) {
                     try {
                         orders =orderService.readAllByUserId(user.getId());
                         request.setAttribute("orders",orders);
                         forward = new Forward("orders",false);
                         } catch (ServiceException e) {
                            logger.error(e);
                         }finally {
                             mainServiceFactory.close();
                         }
                         }else if(user.getRole().equals("administration")){
                             try {
                             orders = orderService.readAll();
                             request.setAttribute("orders",orders);
                             forward = new Forward("orders",false);
                         } catch (ServiceException e) {
                             logger.error(e);
                         }finally {
                             mainServiceFactory.close();
                         }
                        }else {
                            mainServiceFactory.close();
                        }
                     }else {
                         mainServiceFactory.close();
                         forward = new Forward("loginForm",false);
                     }
                     return forward;
           }
}