package com.leveluptasks.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( nullable = false)
    private String firstname;
    @Column( nullable = false)
    private String lastname;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String profileImage;
    private int level;
    private int rewardPercentage;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Expedition> expeditions;


    public enum Role {
        ADMIN, USER
    }
    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRewardPercentage() {
        return rewardPercentage;
    }

    public void setRewardPercentage(int rewardPercentage) {
        this.rewardPercentage = rewardPercentage;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public List<Expedition> getExpeditions() {
        return expeditions;
    }

    public void setExpeditions(List<Expedition> expeditions) {
        this.expeditions = expeditions;
    }

    public User() {
    }
    public User(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
    public User(String firstname, String lastname, String email, String password, String profileImage, int level , int rewardPercentage) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.level = level;
        this.rewardPercentage = rewardPercentage;
    }

}
