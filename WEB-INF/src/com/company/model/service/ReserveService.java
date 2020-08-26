package com.company.model.service;

import java.util.List;

import com.company.model.order.Reserve;

public interface ReserveService {
      public void update(Reserve reserve) throws ServiceException;

      public List<Reserve> readAllByUserId(long id) throws ServiceException;

      public List<Reserve> readAll() throws ServiceException;

      public void delete(long id) throws ServiceException;

      public void create(Reserve reserve) throws ServiceException;
}
