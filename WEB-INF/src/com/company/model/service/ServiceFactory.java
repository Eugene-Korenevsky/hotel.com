package com.company.model.service;

import java.sql.Connection;

import com.company.model.service.RoomService;
import com.company.model.database.specificDao.DescriptionRoomAddictionDao;
import com.company.model.database.specificDao.DescriptionsDao;
import com.company.model.database.specificDao.OrderDao;
import com.company.model.database.specificDao.ReserveDao;
import com.company.model.database.specificDao.RoleDao;
import com.company.model.database.specificDao.RoomsDao;
import com.company.model.database.specificDao.UserDao;
import com.company.model.service.logic.RoomServiceImpl;

public interface ServiceFactory extends AutoCloseable {
        public OrderService getOrderService();

        public ReserveService getReserveService();

        public RoomService getRoomService();

        public UserService getUserService();

        public Transaction getTransaction();

        public Connection getConnection();

        public OrderDao getOrderDao();

        public RoleDao getRoleDao();

        public UserDao getUserDao();

        public RoomsDao getRoomDao();

        public ReserveDao getReserveDao();

        public DescriptionRoomAddictionDao getDescAddictionDao();

        public DescriptionsDao getDescriptionsDao();

}
