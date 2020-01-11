package com.sda.naicu.onlinevoting.dao;

import com.sda.naicu.onlinevoting.model.Candidate;
import com.sda.naicu.onlinevoting.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CandidateDao {

    public Candidate getCandidateById(int idCandidate){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Candidate candidate = session.get(Candidate.class, idCandidate);

        session.close();
        return candidate;
    }


    public List<Candidate> getAllCandidates(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Candidate");
        List<Candidate> candidateList = query.list();

        session.close();
        return candidateList;
    }

    public List<String> getAllCnps(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("select cnp from Candidate");
        List<String> cnpList = query.list();

        session.close();
        return cnpList;
    }

    public void saveCandidate(Candidate candidate){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(candidate);
        transaction.commit();

        session.close();
    }

    public void deleteCandidate(Candidate candidate){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(candidate);
        transaction.commit();

        session.close();
    }
}
