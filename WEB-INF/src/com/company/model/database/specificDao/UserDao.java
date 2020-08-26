package com.company.model.database.specificDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.model.database.BaseDao;
import com.company.model.database.Dao;
import com.company.model.database.DaoException;
import com.company.model.user.User;

public class UserDao extends BaseDao implements Dao<User> {
    private Logger logger;

     public UserDao() {
        logger = Logger.getLogger(UserDao.class);
    }
     @Override
    public User read(long id) throws DaoException {
        String sql = "SELECT * FROM  mydb.user WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = new User();
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            user = new User();
            if (resultSet.next()) {
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("id"));
                user.setRoleId(resultSet.getInt("role_id"));
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
        return user;
    }

     public User readByEmailAndPassword(String email,String password) throws DaoException{
        String sql = "SELECT * FROM  mydb.user WHERE password = ? and email = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = new User();
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, password);
            statement.setString(2,email);
            resultSet = statement.executeQuery();
            user = new User();
            if (resultSet.next()) {
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("id"));
                user.setRoleId(resultSet.getInt("role_id"));
            }
        } catch (SQLException  e) {
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
        return user;
    }

     @Override
    public void delete(long id) throws DaoException {
        String sql = "DELETE  FROM mydb.user WHERE id = ?";
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
    public void update(User user) throws DaoException {
        String sql = "UPDATE mydb.user SET name= ?, surname = ?, password= ?, role_id= ?, email=? WHERE id= ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRoleId());
            statement.setString(5, user.getEmail());
            statement.setLong(6,user.getId());
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
    public void create(User user) throws DaoException {
        String sql = "INSERT INTO `mydb`.`user` (`name`,`surname`, `password`, `role_id`,`email`) VALUES (?, ?, ?, ?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRoleId());
            statement.setString(5, user.getEmail());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            System.out.println("new if for the room" + resultSet.getInt("id"));
        } catch (SQLException e) {

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

    public User createUser(User user) throws DaoException{
        User user1 = user;
        String sql = "INSERT INTO `mydb`.`user` (`name`,`surname`, `password`, `role_id`,`email`) VALUES (?, ?, ?, ?,?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getRoleId());
            statement.setString(5, user.getEmail());
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
        return user;
    }

    public List<User> readAll()throws DaoException{
        List<User> users = new ArrayList<User>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM mydb.user");
            while (resultSet.next()) {
                User user = new User();
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("id"));
                user.setRoleId(resultSet.getInt("role_id"));
                users.add(user);
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
        return users;
    }

    public HashMap<String,User> readAllMap(){
        HashMap<String,User> users = new HashMap<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM mydb.user");
            while (resultSet.next()) {
                User user = new User();
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("id"));
                user.setRoleId(resultSet.getInt("role_id"));
                users.put(String.valueOf(user.getId()),user);
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
        return users;
    }
}
