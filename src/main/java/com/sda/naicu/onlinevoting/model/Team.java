package com.sda.naicu.onlinevoting.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTeam;
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Candidate> candidateList = new ArrayList<>();

    public Team(String name) {

        this.name = name;
    }

    public Team(int idTeam, String name) {
        this.idTeam = idTeam;
        this.name = name;
    }

    public Team() {
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Candidate> getCandidateList() {
        return candidateList;
    }

    public void setCandidateList(List<Candidate> candidateList) {
        this.candidateList = candidateList;
    }

    @Override
    public String toString() {
        return "Team{" +
                "idTeam=" + idTeam +
                ", name='" + name + '\'' +
                ", candidateList=" + candidateList +
                '}';
    }
}
