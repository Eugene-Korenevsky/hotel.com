package com.company.model.database.specificDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.model.database.BaseDao;
import com.company.model.database.Dao;
import com.company.model.database.DaoException;
import com.company.model.room.RoomDescription;

public class DescriptionsDao extends BaseDao implements Dao<RoomDescription> {
    private Logger logger;
	
    public DescriptionsDao() {
        logger = Logger.getLogger(DescriptionsDao.class);
    }
     public List<RoomDescription> readAll(){
        List<RoomDescription> descriptions = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM mydb.descriptions");
            while (resultSet.next()) {
                RoomDescription description = new RoomDescription();
                description.setId(resultSet.getInt("id"));
                description.setDescription(resultSet.getString("descriptionscol"));
                descriptions.add(description);
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
        return descriptions;
    }

    @Override
    public RoomDescription read(long id) throws DaoException {
        return null;
    }

    @Override
    public void delete(long id) throws DaoException {

    }

    @Override
    public void update(RoomDescription entity) throws DaoException {

    }

    @Override
    public void create(RoomDescription entity) throws DaoException {

    }
}
