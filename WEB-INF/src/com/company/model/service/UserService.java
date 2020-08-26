package com.company.model.service;

import java.util.List;

import com.company.model.user.User;

public interface UserService {
     public User loginUser(String password, String email) throws ServiceException;

     public List<User> readAllUsers() throws ServiceException;

     public void updateUser(User user) throws ServiceException;

     public void deleteUser(long id) throws ServiceException;

     public User findUserById(long id) throws ServiceException;

     public void createUser(User user) throws ServiceException;
    
     public User create(User user) throws ServiceException;

}
