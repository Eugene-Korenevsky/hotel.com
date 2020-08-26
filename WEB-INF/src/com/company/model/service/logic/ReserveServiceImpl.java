package com.company.model.service.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.model.user.User;
import com.company.model.room.Room;

import com.company.model.database.DaoException;
import com.company.model.database.specificDao.ReserveDao;
import com.company.model.database.specificDao.RoomsDao;
import com.company.model.database.specificDao.UserDao;
import com.company.model.order.Reserve;
import com.company.model.service.BaseService;
import com.company.model.service.ReserveService;
import com.company.model.service.ServiceException;
import com.company.model.service.TransactionException;

public class ReserveServiceImpl extends BaseService implements ReserveService {
    private ReserveDao reserveDao;
    private RoomsDao roomsDao;
    private UserDao userDao;
    private Logger logger;
    
    public ReserveServiceImpl() {
    	logger = Logger.getLogger(ReserveServiceImpl.class);
    }

    public void setReserveDao(ReserveDao reserveDao) {
        this.reserveDao = reserveDao;
    }

    public void setRoomsDao(RoomsDao roomsDao) {
        this.roomsDao = roomsDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void update(Reserve reserve) throws ServiceException {
        try {
        	getTransaction().start();
            reserveDao.update(reserve);
            getTransaction().commit();
        } catch (TransactionException | DaoException e) {
        	logger.error(e);
        	try {
                getTransaction().rollback();
            } catch (TransactionException e1) {
                logger.error(e);
            }
        }
    }

    @Override
    public List<Reserve> readAllByUserId(long id) throws ServiceException {
        List<Reserve> reserves = new ArrayList<Reserve>();
        try {
            getTransaction().start();
            reserves = reserveDao.readAllByUserId(id);
            if (reserves.size() > 0){
                HashMap<String, Room> rooms = roomsDao.readAllMap();
                HashMap<String, User> users = userDao.readAllMap();
                for (Reserve reserve:reserves){
                    reserve.setRoom(rooms.get(String.valueOf(reserve.getRoomId())));
                    reserve.setUser(users.get(String.valueOf(reserve.getUserId())));
                }
            }
            getTransaction().commit();;
        } catch (TransactionException | DaoException e) {
        	try {
                 getTransaction().rollback();
                } catch (TransactionException e1) {
                  logger.error(e);
                }
        	logger.error(e);
        }
        return reserves;
    }

    @Override
    public List<Reserve> readAll() throws ServiceException {
        List<Reserve> reserves = new ArrayList<Reserve>();
        try {
            getTransaction().start();
            reserves = reserveDao.readAll();
            if (reserves.size() > 0){
                HashMap<String, Room> rooms = roomsDao.readAllMap();
                HashMap<String, User> users = userDao.readAllMap();
                for (Reserve reserve:reserves){
                    reserve.setRoom(rooms.get(String.valueOf(reserve.getRoomId())));
                    reserve.setUser(users.get(String.valueOf(reserve.getUserId())));
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
        return reserves;
    }

    @Override
    public void delete(long id) throws ServiceException {
    	try {
        	getTransaction().start();
            reserveDao.delete(id);
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
    public void create(Reserve reserve) throws ServiceException {
        try {
        	getTransaction().start();
            reserveDao.create(reserve);
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
}
