package com.company.model.service;

public class TransactionException extends Exception {
    public TransactionException() {
    }

    public TransactionException(String massage) {
        super(massage);
    }

    public TransactionException(String massage, Throwable cause) {
        super(massage, cause);
    }

    public TransactionException(Throwable cause) {
        super(cause);
    }

}
