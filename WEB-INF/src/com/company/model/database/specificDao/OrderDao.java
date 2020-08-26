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
import com.company.model.order.Order;

public class OrderDao extends BaseDao implements Dao<Order> {
    private Logger logger;

    public OrderDao() {
       logger = Logger.getLogger(OrderDao.class);
    }
     @Override
    public Order read(long id) throws DaoException {
        Order order = new Order();
        String sql = "SELECT * FROM mydb.`order` WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                 order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setUserId(resultSet.getLong("user_id"));
                order.setDateIn(resultSet.getTimestamp("date_in"));
                order.setDateOut(resultSet.getTimestamp("date_out"));
                order.setRoomId(resultSet.getLong("room_id"));
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
        return order;
    }

    @Override
    public void delete(long id) throws DaoException {
        String sql = "DELETE  FROM mydb.order WHERE id = ?";
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
    public void update(Order order) throws DaoException {
        String sql = "UPDATE mydb.order SET date_in = ?, date_out = ?, user_id = ?,room_id = ? WHERE id= ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setTimestamp(1, order.getDateIn());
            statement.setTimestamp(2, order.getDateOut());
            statement.setLong(3, order.getUserId());
            statement.setLong(4,order.getRoomId());
            statement.setLong(5, order.getId());
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
    public void create(Order order) throws DaoException {
        String sql = "INSERT INTO `mydb`.`order` (`date_in`,`date_out`, `user_id`, `room_id`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1,  order.getDateIn());
            statement.setTimestamp(2,  order.getDateOut());
            statement.setLong(3, order.getUserId());
            statement.setLong(4,order.getRoomId());
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

    public List<Order> readAll() throws DaoException {
        List<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * FROM mydb.`order`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setUserId(resultSet.getLong("user_id"));
                order.setDateIn(resultSet.getTimestamp("date_in"));
                order.setDateOut(resultSet.getTimestamp("date_out"));
                order.setRoomId(resultSet.getLong("room_id"));
                orders.add(order);
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
        return orders;
    }

    public List<Order> readAllByUserId(long userId) throws DaoException {
        List<Order> orders = new ArrayList<Order>();
        String sql = "SELECT * FROM mydb.`order` WHERE user_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setUserId(resultSet.getLong("user_id"));
                order.setDateIn(resultSet.getTimestamp("date_in"));
                order.setDateOut(resultSet.getTimestamp("date_out"));
                order.setRoomId(resultSet.getLong("room_id"));
                orders.add(order);
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
        return orders;
    }

}
