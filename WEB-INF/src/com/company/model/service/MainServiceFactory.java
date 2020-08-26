package com.company.model.service;


import com.company.model.database.specificDao.DescriptionRoomAddictionDao;
import com.company.model.database.specificDao.DescriptionsDao;
import com.company.model.database.specificDao.OrderDao;
import com.company.model.database.specificDao.ReserveDao;
import com.company.model.database.specificDao.RoleDao;
import com.company.model.database.specificDao.RoomsDao;
import com.company.model.database.specificDao.UserDao;
import com.company.model.pool.ConnectionFactory;
import com.company.model.pool.PoolException;
import com.company.model.service.logic.OrderServiceImpl;
import com.company.model.service.logic.ReserveServiceImpl;
import com.company.model.service.logic.RoomServiceImpl;
import com.company.model.service.logic.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class MainServiceFactory implements ServiceFactory {
      private Connection connection;

      @Override
      public ReserveService getReserveService(){
          ReserveServiceImpl reserveService = new ReserveServiceImpl();
          reserveService.setReserveDao(getReserveDao());
          reserveService.setRoomsDao(getRoomDao());
          reserveService.setUserDao(getUserDao());
          reserveService.setTransaction(getTransaction());
          return reserveService;
      }

      @Override
      public OrderService getOrderService() {
          OrderServiceImpl orderService = new OrderServiceImpl();
          orderService.setRoomsDao(getRoomDao());
          orderService.setUserDao(getUserDao());
          orderService.setOrderDao(getOrderDao());
          orderService.setReserveDao(getReserveDao());
          orderService.setTransaction(getTransaction());
           return orderService;
      }

      @Override
      public RoomService getRoomService() {
          RoomServiceImpl roomService = new RoomServiceImpl();
          roomService.setRoomsDao(getRoomDao());
          roomService.setTransaction(getTransaction());
          roomService.setDescriptionRoomAddictionDao(getDescAddictionDao());
          roomService.setDescriptionsDao(getDescriptionsDao());
          return roomService;
      }

      @Override
      public UserService getUserService() {
          UserServiceImpl userService = new UserServiceImpl();
          userService.setRoleDao(getRoleDao());
          userService.setUserDao(getUserDao());
          userService.setTransaction(getTransaction());
          return userService;
      }

      @Override
      public Transaction getTransaction() {
          TransactionImpl transaction = new TransactionImpl();
          transaction.setConnection(getConnection());
          return transaction;
      }

      @Override
      public Connection getConnection() {
      	 if (connection == null) {
             ConnectionFactory connectionFactory = new ConnectionFactory();
               try {
                   connection = connectionFactory.getConnection();
               } catch (PoolException e) {
                   e.printStackTrace();
               }
           }
           return connection;
      }

      @Override
      public ReserveDao getReserveDao(){
          ReserveDao reserveDao = new ReserveDao();
          reserveDao.setConnection(getConnection());
          return reserveDao;
      }

      @Override
      public RoomsDao getRoomDao() {
          RoomsDao roomsDao = new RoomsDao();
          roomsDao.setConnection(getConnection());
          return roomsDao;
      }

      @Override
      public OrderDao getOrderDao() {
          OrderDao orderDao = new OrderDao();
          orderDao.setConnection(getConnection());
          return orderDao;
      }

      @Override
      public DescriptionRoomAddictionDao getDescAddictionDao() {
          DescriptionRoomAddictionDao descriptionRoomAddictionDao = new DescriptionRoomAddictionDao();
          descriptionRoomAddictionDao.setConnection(getConnection());
          return descriptionRoomAddictionDao;
      }

      @Override
      public DescriptionsDao getDescriptionsDao() {
          DescriptionsDao descriptionsDao = new DescriptionsDao();
          descriptionsDao.setConnection(getConnection());
          return descriptionsDao;
      }

      @Override
      public RoleDao getRoleDao() {
          RoleDao roleDao = new RoleDao();
          roleDao.setConnection(getConnection());
          return roleDao;
      }

      @Override
      public UserDao getUserDao() {
          UserDao userDao = new UserDao();
          userDao.setConnection(getConnection());
          return userDao;
      }

      @Override
      public void close() {
          try {
              connection.close();
          } catch (SQLException e) {
              e.printStackTrace();
          }
          connection = null;
      }
}
