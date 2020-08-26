package com.company.web.actions.reserve;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.order.Reserve;
import com.company.model.room.TotalPrice;
import com.company.model.service.MainServiceFactory;
import com.company.model.service.ReserveService;
import com.company.model.service.ServiceException;
import com.company.model.user.User;
import com.company.web.actions.Action;
import com.company.web.actions.Forward;

public class ReserveListAction extends Action {
    @Override 
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
    Forward forward = null;
    MainServiceFactory mainServiceFactory = new MainServiceFactory();
    ReserveService reserveService = mainServiceFactory.getReserveService();
    List<Reserve> reserves = new ArrayList<Reserve>();
    HttpSession session= request.getSession();
    User user=(User) session.getAttribute("user");
    if (user != null) {
        if(user.getRole().equals("user")) {
           try {
                reserves = reserveService.readAllByUserId(user.getId());
                for(Reserve reserve : reserves) {
                    Timestamp in = reserve.getDateIn();
                    Timestamp out = reserve.getDateOut();
                    TotalPrice totalPrice = new TotalPrice(in,out,reserve.getRoom().getPrice());
                    reserve.setTotalPrice(totalPrice.totalPrice());
                }
                request.setAttribute("reserves",reserves);
                forward = new Forward("reserves",false);
           } catch (ServiceException e) {
               logger.error(e);
            }finally {
                mainServiceFactory.close();
            }
         }else if(user.getRole().equals("administration")) {
         try {
            reserves = reserveService.readAll();
            for(Reserve reserve : reserves) {
                Timestamp in = reserve.getDateIn();
                Timestamp out = reserve.getDateOut();
                TotalPrice totalPrice = new TotalPrice(in,out,reserve.getRoom().getPrice());
                reserve.setTotalPrice(totalPrice.totalPrice());
            }
            request.setAttribute("reserves",reserves);
            forward = new Forward("reserves",false);
        } catch (ServiceException e) {
            logger.error(e);
        }finally {
           mainServiceFactory.close();
        }
        }else {
            mainServiceFactory.close();
            forward = new Forward("loginForm",false);
        }
    }else {
        mainServiceFactory.close();
        forward = new Forward("loginForm",false);
    }
    return forward;
}
}