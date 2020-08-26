package com.company.web.actions.reserve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.service.MainServiceFactory;
import com.company.model.service.ReserveService;
import com.company.model.service.ServiceException;
import com.company.model.user.User;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class DeleteReserveAction extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session= request.getSession();
        User user = (User) session.getAttribute("user");
        if( user != null) {
            MainServiceFactory mainServiceFactory = new MainServiceFactory();
            ReserveService reserveService = mainServiceFactory.getReserveService();
            long reserveId = Long.parseLong(request.getParameter("id"));
            try {
                reserveService.delete(reserveId);
            } catch (ServiceException e) {
                logger.error(e);
            }finally {
                mainServiceFactory.close();
            }
            return new Forward("/reserves.html",true);	
            }else {
            return new Forward("loginForm",false);
        }
    }
}