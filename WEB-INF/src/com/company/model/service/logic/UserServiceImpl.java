package com.company.model.service.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.model.database.DaoException;
import com.company.model.database.specificDao.RoleDao;
import com.company.model.database.specificDao.UserDao;
import com.company.model.service.BaseService;
import com.company.model.service.ServiceException;
import com.company.model.service.TransactionException;
import com.company.model.service.UserService;
import com.company.model.user.Role;
import com.company.model.user.User;

public class UserServiceImpl extends BaseService implements UserService {
    private UserDao userDao;
    private RoleDao roleDao;
    private Logger logger;
    
    public UserServiceImpl() {
    	logger = Logger.getLogger(UserServiceImpl.class);
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User loginUser(String password, String email) throws ServiceException {
        User user = new User();
        try {
            getTransaction().start();
            user = userDao.readByEmailAndPassword(email,password);
            Role role = roleDao.read(user.getRoleId());
            user.setRole(role.getRole());
            getTransaction().commit();
        } catch (TransactionException e) {
        	logger.error(e);
            try {
                getTransaction().rollback();
            } catch (TransactionException ex) {
            	logger.error(e);
            }
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException ex) {
            	logger.error(e);
            }
            logger.error(e);
        }
        return user;
    }

    @Override
    public List<User> readAllUsers() throws ServiceException {
        List<User> users = new ArrayList<User>();
        List<Role> roles = new ArrayList<Role>();
        try {
            getTransaction().start();
            users = userDao.readAll();
            roles = roleDao.readAll();
            for (Role role:roles){
                for (User user:users){
                    if (role.getId()==user.getRoleId()){
                        user.setRole(role.getRole());
                    }
                }
            }
            getTransaction().commit();
        } catch (TransactionException | DaoException e) {
        	logger.error(e);
        	try {
				getTransaction().rollback();
			} catch (TransactionException e1) {
				logger.error(e);
			}
        }
        return users;
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        try {
            getTransaction().start();
            User changeUser = userDao.read(user.getId());
            long id = changeUser.getId();
            if (id != 0) {
                userDao.update(user);
            } else {
                System.out.println("Room is not exist");
            }
            getTransaction().commit();
        } catch (TransactionException | DaoException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException ex) {
            	logger.error(e);
            }
            logger.error(e);
        }
    }

    @Override
    public void deleteUser(long id) throws ServiceException {
        try {
        	getTransaction().start();
            userDao.delete(id);
            getTransaction().commit();
        } catch (TransactionException | DaoException e) {
        	try {
				getTransaction().rollback();
			} catch (TransactionException e1) {
				logger.error(e);
			}
        	logger.error(e);
        }
    }

    @Override
    public User findUserById(long id) throws ServiceException {
        User user = new User();
        try {
            getTransaction().start();
            user = userDao.read(id);
            Role role = roleDao.read(user.getRoleId());
            user.setRole(role.getRole());
            getTransaction().commit();
        } catch (TransactionException | DaoException e) {
        	logger.error(e);
            try {
                getTransaction().rollback();
            } catch (TransactionException ex) {
            	logger.error(e);
            }
        }
        return user;
    }

    @Override
    public void createUser(User user) throws ServiceException {
        int defaultRoleId = 1;
        try {
            boolean found = false;
            getTransaction().start();
            List<User> users = userDao.readAll();
            for (User user1:users){
                if (user1.getEmail().equals(user.getEmail())){
                    found = true;
                }
            }
            if (found){
                System.out.println("email is already exist");
            }else {
                user.setRoleId(defaultRoleId);
                userDao.create(user);
            }
            getTransaction().commit();
        } catch (TransactionException | DaoException e) {
            try {
                getTransaction().rollback();
            } catch (TransactionException ex) {
            	logger.error(e);
            }
            logger.error(e);
        }

    }
    
    @Override
    public User create(User user) throws ServiceException {
    	 List<Role> roles = new ArrayList<Role>();
         int defaultRoleId = 1;
         try {
             boolean found = false;
             getTransaction().start();
             List<User> users = userDao.readAll();
             for (User user1 : users) {
                 if (user1.getEmail().equals(user.getEmail())) {
                     found = true;
                 }
             }
             if (found) {
                 System.out.println("email is already exist");
             } else {
                 user.setRoleId(defaultRoleId);
                 userDao.create(user);
                 roles = roleDao.readAll();
                 for (Role role : roles) {
                     if (role.getId() == user.getRoleId()) {
                         user.setRole(role.getRole());
                     }
                 }
             }
             getTransaction().commit();
         } catch (TransactionException | DaoException e) {
             try {
                 getTransaction().rollback();
             } catch (TransactionException ex) {
            	 logger.error(e);
             }
             logger.error(e);
         }
         return user;
    }
}
