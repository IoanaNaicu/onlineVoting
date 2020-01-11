package com.sda.naicu.onlinevoting.model;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate birthDate;
    private String cnp;
    @OneToMany(mappedBy = "candidate")
    private List<Vote> candidateVotes = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "idTeam")
    private Team team;

    public Candidate(String firstName, String lastName, LocalDate birthDate, String cnp, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.cnp = cnp;
        this.team = team;
    }

    public Candidate() {
    }

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "idCandidate=" + idCandidate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", cnp=" + cnp +
                ", candidateVotes=" + candidateVotes +
                ", team=" + team.getName() +
                '}';
    }
}
