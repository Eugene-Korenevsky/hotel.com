package com.company.model.database.specificDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.model.database.BaseDao;
import com.company.model.database.Dao;
import com.company.model.database.DaoException;
import com.company.model.order.Reserve;

public class ReserveDao extends BaseDao implements Dao<Reserve> {
    private Logger logger;

    public ReserveDao() {
        logger = Logger.getLogger(ReserveDao.class);
    }
     @Override
     public Reserve read(long id) throws DaoException {
       Reserve reserve = new Reserve();
        String sql = "SELECT * FROM mydb.`reserved` WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                reserve = new Reserve();
                reserve.setId(resultSet.getLong("id"));
                reserve.setUserId(resultSet.getLong("user_id"));
                reserve.setDateIn(resultSet.getTimestamp("date_in"));
                reserve.setDateOut(resultSet.getTimestamp("date_out"));
                reserve.setRoomId(resultSet.getLong("room_id"));
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
        return reserve;
    }

     @Override
    public void delete(long id) throws DaoException {
        String sql = "DELETE  FROM mydb.reserved WHERE id = ?";
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
    public void update(Reserve reserve) throws DaoException {
        String sql = "UPDATE mydb.reserved SET date_in = ?, date_out = ?, user_id = ?,room_id = ? WHERE id= ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setTimestamp(1, reserve.getDateIn());
            statement.setTimestamp(2, reserve.getDateOut());
            statement.setLong(3, reserve.getUserId());
            statement.setLong(4,reserve.getRoomId());
            statement.setLong(5, reserve.getId());
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
    public void create(Reserve reserve) throws DaoException {
        String sql = "INSERT INTO `mydb`.`reserved` (`date_in`,`date_out`, `user_id`, `room_id`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1,  reserve.getDateIn());
            statement.setTimestamp(2,  reserve.getDateOut());
            statement.setLong(3, reserve.getUserId());
            statement.setLong(4,reserve.getRoomId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
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

    public List<Reserve> readAll() throws DaoException {
        List<Reserve> reserves = new ArrayList<Reserve>();
        String sql = "SELECT * FROM mydb.`reserved`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Reserve reserve = new Reserve();
                reserve.setId(resultSet.getLong("id"));
                reserve.setUserId(resultSet.getLong("user_id"));
                reserve.setDateIn(resultSet.getTimestamp("date_in"));
                reserve.setDateOut(resultSet.getTimestamp("date_out"));
                reserve.setRoomId(resultSet.getLong("room_id"));
                reserves.add(reserve);
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
        return reserves;
    }

    public List<Reserve> readAllByUserId(long userId) throws DaoException {
        List<Reserve> reserves = new ArrayList<Reserve>();
        String sql = "SELECT * FROM mydb.`reserved` WHERE user_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Reserve reserve = new Reserve();
                reserve.setId(resultSet.getLong("id"));
                reserve.setUserId(resultSet.getLong("user_id"));
                reserve.setDateIn(resultSet.getTimestamp("date_in"));
                reserve.setDateOut(resultSet.getTimestamp("date_out"));
                reserve.setRoomId(resultSet.getLong("room_id"));
                reserves.add(reserve);
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
        return reserves;
    }
}
