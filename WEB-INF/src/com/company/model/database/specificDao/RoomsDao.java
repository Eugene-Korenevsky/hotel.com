package com.company.model.database.specificDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.company.model.database.BaseDao;
import com.company.model.database.Dao;
import com.company.model.database.DaoException;
import com.company.model.room.Room;
import org.apache.log4j.Logger;

public class RoomsDao extends BaseDao implements Dao<Room> {
    private Logger logger;

    public RoomsDao() {
        logger = Logger.getLogger(RoomsDao.class);
    }

     @Override
    public Room read(long id) throws DaoException {
        Room room = new Room();
        String sql = "SELECT * FROM  mydb.rooms WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            room = new Room();
            if (resultSet.next()) {
                room.setNumber(resultSet.getInt("number"));
                room.setPrice(resultSet.getDouble("price"));
                room.setSits(resultSet.getInt("sits"));
                room.setComfort(resultSet.getString("room_class"));
                room.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
        	 logger.error(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
            	 logger.error(e);
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
            	 logger.error(e);
            }
        }
        return room;
    }

    @Override
    public void delete(long id) throws DaoException {
        String sql = "DELETE  FROM mydb.rooms WHERE id = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
        	 logger.error(e);
        }finally {
            if (statement!=null){
                try {
                    statement.close();
                }catch (Exception e){
                	 logger.error(e);
                }
            }
        }
    }

    @Override
    public void update(Room room) throws DaoException {
        String sql = "UPDATE mydb.rooms SET number= ?, room_class = ?, sits= ?, price= ? WHERE id= ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, room.getNumber());
            statement.setString(2, room.getComfort());
            statement.setInt(3, room.getSits());
            statement.setDouble(4, room.getPrice());
            statement.setLong(5, room.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
        	 logger.error(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                	 logger.error(e);
                }
            }
        }
    }

    @Override
    public void create(Room room) throws DaoException {
        String sql = "INSERT INTO `mydb`.`rooms` (`number`,`room_class`, `sits`, `price`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, room.getNumber());
            statement.setString(2, room.getComfort());
            statement.setInt(3, room.getSits());
            statement.setDouble(4, room.getPrice());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            System.out.println("new if for the room" + resultSet.getInt("id"));
        } catch (SQLException e) {
        	 logger.error(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                	 logger.error(e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                	 logger.error(e);
                }
            }
        }

    }

    public List<Room> readAll() throws DaoException {
        List<Room> rooms = new ArrayList<Room>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM rooms");
            while (resultSet.next()) {
                Room room = new Room();
                room.setPrice(resultSet.getDouble("price"));
                room.setSits(resultSet.getInt("sits"));
                room.setNumber(resultSet.getInt("number"));
                room.setComfort(resultSet.getString("room_class"));
                room.setId(resultSet.getInt("id"));
                rooms.add(room);
            }
        } catch (SQLException e) {
        	 logger.error(e);
        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (Exception e) {
                	 logger.error(e);
                }
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (Exception e) {
                	 logger.error(e);
                }
        }
        return rooms;
    }

    public HashMap<String,Room> readAllMap() throws DaoException{
        HashMap<String,Room> rooms =new HashMap<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM rooms");
            while (resultSet.next()) {
                Room room = new Room();
                room.setPrice(resultSet.getDouble("price"));
                room.setSits(resultSet.getInt("sits"));
                room.setNumber(resultSet.getInt("number"));
                room.setComfort(resultSet.getString("room_class"));
                room.setId(resultSet.getInt("id"));
                rooms.put(String.valueOf(room.getId()),room);
            }
        } catch (SQLException e) {
        	 logger.error(e);
        } finally {
            if (statement != null)
                try {
                    statement.close();
                } catch (Exception e) {
                	 logger.error(e);
                }
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (Exception e) {
                	 logger.error(e);
                }
        }
        return rooms;
   }

}
