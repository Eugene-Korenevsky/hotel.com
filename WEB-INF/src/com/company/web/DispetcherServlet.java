package com.company.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.company.model.service.MainServiceFactory;
import com.company.model.service.ServiceFactory;
import com.company.web.actions.Action;
import com.company.web.actions.ActionFactory;
import com.company.web.actions.Forward;

@WebServlet("/DispetcherServlet")
public class DispetcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DispetcherServlet() {
        super();
       
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request,response);
    }

    public ServiceFactory getServiceFactory() {
        return new MainServiceFactory();
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = Logger.getLogger(DispetcherServlet.class);
        String url = request.getRequestURI();
        String context = request.getContextPath();
        int index = url.lastIndexOf(".html");
        if (index != -1) {
            url = url.substring(context.length(),index);
        }else {
          url = url.substring(context.length());
        }		
         Action action = ActionFactory.getAction(url);
         Forward forward = null;
         if(action != null) {
        	 forward = action.execute(request, response);
             if(forward != null && forward.isRedirect()) {
                try {
                    response.sendRedirect(context + forward.getUrl());
                    } catch (IOException e) {
                      logger.error(e);
                    }
                }else {
                  if(forward != null && forward.getUrl() != null) {
                  url = forward.getUrl();
                  }
                  try {
                    request.getRequestDispatcher("/WEB-INF/jsp/" + url + ".jsp").forward(request, response);
                  } catch (ServletException e) {
                   logger.error(e);
                  } catch (IOException e) {
                   logger.error(e);
                  }
                 }

            }
    }
}