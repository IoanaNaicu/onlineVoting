package com.sda.naicu.onlinevoting.service;

import com.sda.naicu.onlinevoting.dao.TeamDao;
import com.sda.naicu.onlinevoting.model.Team;

import java.util.List;

public class TeamDisplayService {

    private TeamDao teamDao;

    public TeamDisplayService() {
        teamDao = new TeamDao();
    }

    public List<String> getAllTeamsNames() {
        return teamDao.getAllTeamsNames();
    }

    public List<Team> getAllTeams() {
        return teamDao.getAllTeams();
    }

    public void saveTeam(Team team) {
        teamDao.saveTeam(team);
    }

    public Integer getIdByTeamName(String teamName) {
        int idTeam = 0;
        List<Team> allTeamNames = teamDao.getAllTeams();
        for (Team team : allTeamNames) {
            if (team.getName().equals(teamName)) {
                idTeam = team.getIdTeam();
            }
        }
        return idTeam;
    }


}