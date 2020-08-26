package com.company.model.database;

public class DaoException extends Exception {
    public DaoException() {
    }

    public DaoException(String massage) {
        super(massage);
    }
    
    public DaoException(String massage, Throwable cause) {
        super(massage, cause);
    }
    
   public DaoException(Throwable cause) {
        super(cause);
    }

}
