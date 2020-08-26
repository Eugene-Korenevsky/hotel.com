package com.company.model.service.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.model.database.DaoException;
import com.company.model.database.specificDao.OrderDao;
import com.company.model.database.specificDao.ReserveDao;
import com.company.model.database.specificDao.RoomsDao;
import com.company.model.database.specificDao.UserDao;
import com.company.model.order.Order;
import com.company.model.order.Reserve;
import com.company.model.service.BaseService;
import com.company.model.service.OrderService;
import com.company.model.service.ServiceException;
import com.company.model.service.TransactionException;
import com.company.model.room.Room;
import com.company.model.user.User;

public class OrderServiceImpl extends BaseService implements OrderService {
     private OrderDao orderDao;
     private UserDao userDao;
     private RoomsDao roomsDao;
     private ReserveDao reserveDao;
     private Logger logger;
    
     public OrderServiceImpl() {
         logger = Logger.getLogger(OrderServiceImpl.class);
         }

    public void setReserveDao(ReserveDao reserveDao) {
        this.reserveDao = reserveDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoomsDao(RoomsDao roomsDao) {
        this.roomsDao = roomsDao;
    }


    @Override
    public void update(Order order) throws ServiceException {
        try {
            getTransaction().start();
            orderDao.update(order);
            getTransaction().commit();
            } catch (DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
                } catch (TransactionException e1) {
                logger.error(e);
               }
            logger.error(e);
            }
       }

    @Override
    public List<Order> readAllByUserId(long id) throws ServiceException {
        List<Order> orders = new ArrayList<Order>();
        try {
            getTransaction().start();
            orders = orderDao.readAllByUserId(id);
            if (orders.size() > 0){
                HashMap<String, Room> rooms = roomsDao.readAllMap();
                HashMap<String, User> users = userDao.readAllMap();
                for (Order order:orders){
                    order.setRoom(rooms.get(String.valueOf(order.getRoomId())));
                    order.setUser(users.get(String.valueOf(order.getUserId())));
                }
            }
            getTransaction().commit();
        } catch (TransactionException | DaoException e) {
            try {
                 getTransaction().rollback();
                } catch (TransactionException e1) {
                 logger.error(e);
           }
               logger.error(e);
         }
        return orders;
    }

    @Override
    public List<Order> readAll() throws ServiceException {
        List<Order> orders = new ArrayList<Order>();
        try {
            getTransaction().start();
            orders = orderDao.readAll();
            if (orders.size() > 0){
                HashMap<String, Room> rooms = roomsDao.readAllMap();
                HashMap<String, User> users = userDao.readAllMap();
                for (Order order:orders){
                    order.setRoom(rooms.get(String.valueOf(order.getRoomId())));
                    order.setUser(users.get(String.valueOf(order.getUserId())));
                }
            }
            getTransaction().commit();
        } catch (TransactionException | DaoException e) {
        	try {
                 getTransaction().rollback();
                } catch (TransactionException e1) {
                logger.error(e);
                }
                logger.error(e);
           }
        return orders;
    }

    @Override
    public void delete(long id) throws ServiceException {
        try {
            getTransaction().start();
            orderDao.delete(id);
            getTransaction().commit();
        } catch (DaoException | TransactionException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) {
              logger.error(e);
            }
              logger.error(e);
        }
    }

    @Override
    public void create(Order order) throws ServiceException {
        try {
             getTransaction().start();
             orderDao.create(order);
             getTransaction().commit();
            } catch (TransactionException | DaoException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException e1) {
                 logger.error(e);
              }
              logger.error(e);
            }
    }

    @Override
    public void doReserve(long orderId){
       Reserve reserve = new Reserve();
       Order order = new Order();
         try {
         getTransaction().start();
             order = orderDao.read(orderId);
             reserve.setDateOut(order.getDateOut());
             reserve.setDateIn(order.getDateIn());
             reserve.setRoomId(order.getRoomId());
             reserve.setUserId(order.getUserId());
             reserveDao.create(reserve);
             orderDao.delete(order.getId());
             getTransaction().commit();
         } catch (TransactionException | DaoException e) {
             try {
                 getTransaction().rollback();
             } catch (TransactionException ex) {
                 logger.error(e);
               }
             logger.error(e);
         }
    }
    
    public Order readOrderById(long id) {
    Order order = new Order();
       try {
          getTransaction().start();
          order = orderDao.read(id);
          getTransaction().commit();
         } catch (DaoException |TransactionException e) {
            try {
                getTransaction().rollback();
                } catch (TransactionException e1) {
                logger.error(e);
                }
                logger.error(e);
            }
             return order;
            }
}
