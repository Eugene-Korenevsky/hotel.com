package com.company.model.service;

public interface Transaction {
     public void start() throws TransactionException;

     public void commit() throws TransactionException;

     public void rollback() throws TransactionException;

}
