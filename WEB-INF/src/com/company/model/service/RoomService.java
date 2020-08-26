package com.company.model.service;

import java.util.List;

import com.company.model.room.Room;

public interface RoomService {
      public void saveRoom(Room room) throws ServiceException;

      public void deleteRoom(long id) throws ServiceException;

      public Room findById(long id) throws ServiceException;

      public List<Room> readAll() throws ServiceException;

      public void createRoom(Room room) throws ServiceException;
      public List<Room> readAllWithDescriptions() throws ServiceException;
      public Room readByIdWithDescriptions( long id) throws ServiceException;

}
