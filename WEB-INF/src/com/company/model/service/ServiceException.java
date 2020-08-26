package com.company.model.service;

public class ServiceException extends Exception {
    public ServiceException() {
    }

    public ServiceException(String massage) {
        super(massage);
    }

    public ServiceException(String massage, Throwable cause) {
        super(massage, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
