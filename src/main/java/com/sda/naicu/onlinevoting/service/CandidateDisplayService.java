package com.sda.naicu.onlinevoting.service;

import com.sda.naicu.onlinevoting.dao.CandidateDao;
import com.sda.naicu.onlinevoting.model.Candidate;

import java.util.List;

public class CandidateDisplayService {

    private CandidateDao candidateDao;

    public CandidateDisplayService() {
        candidateDao = new CandidateDao();
    }

    public Candidate getCandidateById(int id) {
        return candidateDao.getCandidateById(id);
    }

    public List<Candidate> getAllCandidates() {
        return candidateDao.getAllCandidates();
    }
}
