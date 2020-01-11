package com.sda.naicu.onlinevoting.service;


import com.sda.naicu.onlinevoting.dao.UserDao;
import com.sda.naicu.onlinevoting.model.User;
import com.sda.naicu.onlinevoting.model.UserType;

import java.util.List;

public class UserDisplayService {
    private UserDao userDao;

    public UserDisplayService() {
        userDao = new UserDao();
    }

    public void saveUser(User user){
        userDao.saveUser(user);
    }

    public List<User> getUsersByUserType(UserType userType){
        return userDao.getUsersByUserType(userType);
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public List<String> getAllUsersCnp(){
        return userDao.getAllUsersCnp();
    }

}
