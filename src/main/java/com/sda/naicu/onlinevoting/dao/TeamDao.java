package com.sda.naicu.onlinevoting.dao;

import com.sda.naicu.onlinevoting.model.Team;
import com.sda.naicu.onlinevoting.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.List;

public class TeamDao {
    public List<String> getAllTeams(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select name from Team order by idTeam");

        List<String> allTeams = query.list();
        session.close();
        return allTeams;
    }

}
