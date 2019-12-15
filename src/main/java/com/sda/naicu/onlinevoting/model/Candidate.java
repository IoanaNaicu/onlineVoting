package com.sda.naicu.onlinevoting.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCandidate;
    private String firstName;
    private String lastName;
    @Column(name = "dateOfBirth")
    private Date birthDate;
    @OneToMany(mappedBy = "candidate")
    private List<Vote> candidateVotes = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "idTeam")
    private Team team;

    public int getIdCandidate() {
        return idCandidate;
    }

    public void setIdCandidate(int idCandidate) {
        this.idCandidate = idCandidate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Vote> getCandidateVotes() {
        return candidateVotes;
    }

    public void setCandidateVotes(List<Vote> candidateVotes) {
        this.candidateVotes = candidateVotes;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "idCandidate=" + idCandidate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", candidateVotes=" + candidateVotes +
                ", team=" + team.getName() +
                '}';
    }
}
