package com.company.model.database;

import java.sql.Connection;

public abstract class BaseDao {
    private Connection connection;

    protected Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
