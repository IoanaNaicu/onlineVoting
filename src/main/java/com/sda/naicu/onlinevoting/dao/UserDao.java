package com.sda.naicu.onlinevoting.dao;

import com.sda.naicu.onlinevoting.model.User;
import com.sda.naicu.onlinevoting.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.List;

public class UserDao {

    public List<User> getAllUsers(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User");

        List<User> userList = query.list();
        session.close();
        return userList;
    }

    public List<User> getUsersByUserType(String userType){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query  query = session.createQuery("from User where userType = ?1");
        query.setParameter(1, userType);

        List<User> userList = query.list();
        session.close();
        return userList;
    }
}
