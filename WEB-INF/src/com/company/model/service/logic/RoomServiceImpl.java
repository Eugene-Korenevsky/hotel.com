package com.company.model.service.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.model.database.DaoException;
import com.company.model.database.specificDao.DescriptionRoomAddictionDao;
import com.company.model.database.specificDao.DescriptionsDao;
import com.company.model.database.specificDao.RoomsDao;
import com.company.model.room.DescriptionRoomAddiction;
import com.company.model.room.Room;
import com.company.model.room.RoomDescription;
import com.company.model.service.BaseService;
import com.company.model.service.RoomService;
import com.company.model.service.ServiceException;
import com.company.model.service.TransactionException;

public class RoomServiceImpl extends BaseService implements RoomService {
     private RoomsDao roomsDao;
     private DescriptionsDao descriptionsDao;
     private DescriptionRoomAddictionDao descriptionRoomAddictionDao;
     private Logger logger;
     
     public RoomServiceImpl() {
    	 logger = Logger.getLogger(RoomServiceImpl.class);
     }

	    public void setDescriptionRoomAddictionDao(DescriptionRoomAddictionDao descriptionRoomAddictionDao) {
	        this.descriptionRoomAddictionDao = descriptionRoomAddictionDao;
	    }

	    public void setDescriptionsDao(DescriptionsDao descriptionsDao) {
	        this.descriptionsDao = descriptionsDao;
	    }

	    public void setRoomsDao(RoomsDao roomsDao) {
	        this.roomsDao = roomsDao;
	    }

	    @Override
	    public void saveRoom(Room room) throws ServiceException {
	        try {
	            getTransaction().start();
	            Room changeRoom = roomsDao.read(room.getId());
	            long id = changeRoom.getId();
	            if (id != 0) {
	                roomsDao.update(room);
	            } else {
	                System.out.println("Room is not exist");
	            }
	            getTransaction().commit();
	        } catch (TransactionException | DaoException e) {
	        	logger.error(e);
	            try {
	                getTransaction().rollback();
	            } catch (TransactionException ex) {
	            	logger.error(e);
	            }
	        }

	    }

	    @Override
	    public void deleteRoom(long id) throws ServiceException {
	        try {
	        	getTransaction().start();
	            roomsDao.delete(id);
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
	    public Room findById(long id) throws ServiceException {
	    	Room room = new Room();
	        try {
	        	getTransaction().start();
	            room = roomsDao.read(id);
	            getTransaction().commit();
	        } catch (TransactionException | DaoException e) {
	        	logger.error(e);
	        	try {
					getTransaction().rollback();
				} catch (TransactionException e1) {
					logger.error(e);
				}
	        }
	        return room;
	    }

	    @Override
	    public List<Room> readAll() throws ServiceException {
	    	List <Room> rooms = new ArrayList<Room>();
	        try {
	        	getTransaction().start();
	            rooms = roomsDao.readAll();
	            getTransaction().rollback();
	        } catch (TransactionException | DaoException e) {
	        	logger.error(e);
	        	try {
					getTransaction().rollback();
				} catch (TransactionException e1) {
					logger.error(e);
				}
	        }
	        return rooms;
	    }

	    @Override
	    public void createRoom(Room room) throws ServiceException {
	        try {
	            getTransaction().start();
	                roomsDao.create(room);
	            getTransaction().commit();
	        } catch (TransactionException  |DaoException e) {
	        	logger.error(e);
	            try {
	                getTransaction().rollback();
	            } catch (TransactionException ex) {
	            	logger.error(e);
	            }
	        }
	    }

	    public List<Room> readAllWithDescriptions() throws ServiceException {
	        List<Room> rooms = new ArrayList<Room>();
	        List<RoomDescription> descriptions = null;
	        List<DescriptionRoomAddiction> addictions = null;
	        try {
	            getTransaction().start();
	            rooms = roomsDao.readAll();
	            descriptions = descriptionsDao.readAll();
	            addictions = descriptionRoomAddictionDao.readAll();
	            for (Room room : rooms) {
	                long id = room.getId();
	                for (DescriptionRoomAddiction addiction : addictions) {
	                    if (addiction.getRoomId() == id) {
	                        long id1 = addiction.getDescriptionId();
	                        for (RoomDescription description : descriptions) {
	                            if (id1 == description.getId()) {
	                                room.addDescription(description.getDescription());
	                                //System.out.println(description.getDescription());
	                            }
	                        }
	                    }
	                }
	            }
	            getTransaction().commit();
	        } catch (TransactionException | DaoException e) {
	        	logger.error(e);
	            try {
	                getTransaction().rollback();
	            } catch (TransactionException ex) {
	            	logger.error(e);
	            }
	        }
	        return rooms;
	    }

	    @Override
	    public Room readByIdWithDescriptions(long id) throws ServiceException {
	        List<RoomDescription> descriptions = new ArrayList<RoomDescription>();
	        List<DescriptionRoomAddiction> addictions =  new ArrayList<DescriptionRoomAddiction>();
	        Room room = null;
	        try {
	             room = roomsDao.read(id);
	            getTransaction().start();
	            descriptions = descriptionsDao.readAll();
	            addictions = descriptionRoomAddictionDao.readAll();
	            for (DescriptionRoomAddiction addiction : addictions) {
	                if (addiction.getRoomId() == room.getId()) {
	                    long id1 = addiction.getDescriptionId();
	                    for (RoomDescription description : descriptions) {
	                        if (id1 == description.getId()) {
	                            room.addDescription(description.getDescription());	                           
	                        }
	                    }
	                }
	            }
	            getTransaction().commit();
	        } catch (DaoException | TransactionException e) {
	        	logger.error(e);
	            try {
	                getTransaction().rollback();
	            } catch (TransactionException ex) {
	            	logger.error(e);
	            }
	        }
	        return room;
	    }
}
