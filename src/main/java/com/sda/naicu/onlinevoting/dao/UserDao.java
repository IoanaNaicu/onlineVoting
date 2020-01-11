package com.sda.naicu.onlinevoting.dao;

import com.sda.naicu.onlinevoting.model.User;
import com.sda.naicu.onlinevoting.model.UserType;
import com.sda.naicu.onlinevoting.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    //testing  git

    public List<User> getUsersByUserType(UserType userType){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query  query = session.createQuery("from User where userType = ?1");
        query.setParameter(1, userType);

        List<User> userList = query.list();
        session.close();
        return userList;
    }

    public void saveUser(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(user);
        transaction.commit();

        session.close();
    }

    public List<String> getAllUsersCnp(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select cnp from User");

        List<String> allCnp = query.list();
        session.close();
        return allCnp;
    }
}
