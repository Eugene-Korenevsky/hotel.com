package com.company.model.database.specificDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.model.database.BaseDao;
import com.company.model.database.Dao;
import com.company.model.database.DaoException;
import com.company.model.user.Role;
import org.apache.log4j.Logger;

public class RoleDao extends BaseDao implements Dao<Role> {
    private Logger logger;


    public RoleDao() {
        logger = Logger.getLogger(RoleDao.class);
    }
     @Override
    public Role read(long id) throws DaoException {
        Role role = new Role();
        String sql = "SELECT * FROM  mydb.role WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            role = new Role();
            if (resultSet.next()) {
               role.setId(resultSet.getInt("id"));
               role.setRole(resultSet.getString("role_name"));
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
        return role;
    }

    public Role read(String role){
        Role role1 = new Role();
        String sql = "SELECT * FROM  mydb.role WHERE role_name = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, role);
            resultSet = statement.executeQuery();
            role1 = new Role();
            if (resultSet.next()) {
                role1.setId(resultSet.getInt("id"));
                role1.setRole(resultSet.getString("role_name"));
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
        return role1;
    }

    public List<Role> readAll(){
        List<Role> roles = new ArrayList<Role>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM mydb.role");
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRole(resultSet.getString("role_name"));
                roles.add(role);
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
        return roles;
    }

    @Override
    public void delete(long id) throws DaoException {

    }

    @Override
    public void update(Role entity) throws DaoException {

    }

    @Override
    public void create(Role entity) throws DaoException {

    }

}
