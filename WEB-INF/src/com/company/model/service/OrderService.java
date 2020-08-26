package com.company.model.service;

import java.util.List;

import com.company.model.order.Order;

public interface OrderService {
     public void update(Order order) throws ServiceException;

     public List<Order> readAllByUserId(long id) throws ServiceException;

     public List<Order> readAll() throws ServiceException;

     public void delete(long id) throws ServiceException;

     public void create(Order order) throws ServiceException;

     public void doReserve(long orderId) throws ServiceException;
    
     public Order readOrderById(long id) throws ServiceException;

}
