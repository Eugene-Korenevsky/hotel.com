package com.company.model.database;

public interface Dao<T> {
    public T read(long id) throws DaoException;

    public void delete(long id) throws DaoException;

    public void update(T entity) throws DaoException;

    public void create(T entity) throws DaoException;

}
