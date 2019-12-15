package com.sda.naicu.onlinevoting.service;

import com.sda.naicu.onlinevoting.dao.TeamDao;
import com.sda.naicu.onlinevoting.model.Team;

import java.util.List;

public class TeamDisplayService {

    private TeamDao teamDao;

    public TeamDisplayService() {
        teamDao = new TeamDao();
    }

    public List<String> getAllTeams(){
        return teamDao.getAllTeams();
    }
}
