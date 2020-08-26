package com.company.model.pool;

import java.sql.Connection;

public class ConnectionFactory {
     public Connection getConnection() throws PoolException {
        try {
            return ConnectionPool.getInstance().getConnection();
        } catch (PoolException e) {
           throw new PoolException();
        }
    }

}
