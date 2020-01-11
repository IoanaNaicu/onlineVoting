package com.sda.naicu.onlinevoting.dao;

import com.sda.naicu.onlinevoting.model.Team;
import com.sda.naicu.onlinevoting.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class TeamDao {
    Team team = new Team();
    public List<String> getAllTeamsNames(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select name from Team order by idTeam");

        List<String> allTeams = query.list();
        session.close();
        return allTeams;
    }

    public List<Team> getAllTeams(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Team");

        List<Team> allTeams = query.list();
        session.close();
        return allTeams;
    }

//    public Integer getIdByTeamName(String teamName){
//
//        for(String name: allTeamNames){
//            if (name.equals(teamName)){
//                return teamDao.
//            }
//        }
//    }

    public void saveTeam(Team team){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(team);
        transaction.commit();

        session.close();
    }


}