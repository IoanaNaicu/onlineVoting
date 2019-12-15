package com.sda.naicu.onlinevoting;

import com.sda.naicu.onlinevoting.dao.CandidateDao;
import com.sda.naicu.onlinevoting.model.Candidate;
import com.sda.naicu.onlinevoting.service.CandidateDisplayService;
import com.sda.naicu.onlinevoting.view.OnlineVotingMainView;

import java.util.List;

public class Application extends OnlineVotingMainView{

    public static void main(String[] args) {
        System.out.println("Starting Application");

        CandidateDao candidateDao = new CandidateDao();
        CandidateDisplayService candidateDisplayService = new CandidateDisplayService();

        //printList(candidateDisplayService.getAllCandidates());

        //System.out.println(candidateDao.getCandidateById(1));
        //printList(candidateDao.getAllCandidates());
        OnlineVotingMainView.launch();
    }

    public static <T> void printList(List<T> list){
        for (T t: list) {
            System.out.println(t);
        }
    }
}
