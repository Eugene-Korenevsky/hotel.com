package com.company.model.database.specificDao;
import com.company.model.room.DescriptionRoomAddiction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.model.database.BaseDao;
import com.company.model.database.Dao;
import com.company.model.database.DaoException;

public class DescriptionRoomAddictionDao extends BaseDao implements Dao<DescriptionRoomAddiction> {
    private Logger logger;

    public DescriptionRoomAddictionDao() {
       logger = Logger.getLogger(DescriptionRoomAddictionDao.class);
    }
     @Override
    public DescriptionRoomAddiction read(long id) throws DaoException {
        return new DescriptionRoomAddiction();
    }

     @Override
    public void delete(long id) throws DaoException {

    }

     @Override
    public void update(DescriptionRoomAddiction entity) throws DaoException {

    }

     @Override
    public void create(DescriptionRoomAddiction entity) throws DaoException {

    }
    public List<DescriptionRoomAddiction> readAll(){
        List<DescriptionRoomAddiction> descriptions = new ArrayList<DescriptionRoomAddiction>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM mydb.`rooms-descriptions`");
            while (resultSet.next()) {
                DescriptionRoomAddiction description = new DescriptionRoomAddiction();
                description.setRoomId(resultSet.getInt("room_id"));
                description.setDescriptionId(resultSet.getInt("descriptions_id"));
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

}
