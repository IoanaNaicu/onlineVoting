package com.sda.naicu.onlinevoting.service;

import com.sda.naicu.onlinevoting.dao.CandidateDao;
import com.sda.naicu.onlinevoting.model.Candidate;


import java.util.ArrayList;
import java.util.List;

public class CandidateDisplayService {

    private CandidateDao candidateDao;

    public CandidateDisplayService() {

        candidateDao = new CandidateDao();
    }

    public Candidate getCandidateById(int id) {

        return candidateDao.getCandidateById(id);
    }

    public List<Candidate> getCandidatesByTeam(String teamName) {
        List<Candidate> allCandidates = candidateDao.getAllCandidates();
        List<Candidate> candidatesByTeam = new ArrayList<>();

        for (Candidate candidate : allCandidates) {
            if (candidate.getTeam().getName().equalsIgnoreCase(teamName)) {

                candidatesByTeam.add(candidate);
            }
        }
        return candidatesByTeam;
    }

    public List<Candidate> getAllCandidates() {

        return candidateDao.getAllCandidates();
    }

    public List<String> getAllCnps() {
        return candidateDao.getAllCnps();
    }

    public void saveCandidate(Candidate candidate) {
        candidateDao.saveCandidate(candidate);
    }

    public void deleteCandidate(Candidate candidate) {
        candidateDao.deleteCandidate(candidate);
    }
}
