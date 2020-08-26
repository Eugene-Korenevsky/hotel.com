package com.company.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.company.model.service.ServiceFactory;

abstract public class Action {
   private ServiceFactory serviceFactory;
   protected Logger logger = Logger.getLogger(Action.class);
   
   public ServiceFactory getServiceFactory() {
       return serviceFactory;
   }
   
   public void setServiceFactory(ServiceFactory serviceFactory) {
      this.serviceFactory = serviceFactory;
   }
   
   abstract public Forward execute(HttpServletRequest request, HttpServletResponse response);
}
