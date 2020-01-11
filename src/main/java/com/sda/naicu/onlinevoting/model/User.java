package com.sda.naicu.onlinevoting.model;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    private String firstName;
    private String lastName;
    private String address;
    private String county;
    private String cnp;
    @Enumerated(EnumType.STRING)
    private UserType userType;
//    @OneToOne
//    @JoinColumn(name = "idVote")
//    private Vote userVote;

    public int getIdUser() {
        return idUser;
    }

    public User() {
    }

    public User(String firstName, String lastName, String address, String county, String cnp, UserType userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.county = county;
        this.cnp = cnp;
        this.userType = userType;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", county='" + county + '\'' +
                ", cnp='" + cnp + '\'' +
                ", UserType='" + userType + '\'' +
                '}';
    }
}
