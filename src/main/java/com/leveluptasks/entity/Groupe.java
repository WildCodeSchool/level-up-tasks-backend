package com.leveluptasks.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table(name = "groupe")
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idgroup;
    private String name;

    @OneToMany(mappedBy = "groupe",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserHasGroup> userHasGroups;

    @Transient
    private List<String> userNames;
    public Groupe() {
    }
    public Groupe(String name) {
        this.name = name;
    }



    public long getIdgroup() {
        return this.idgroup;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserHasGroup> getUserHasGroups() {
        return this.userHasGroups;
    }

    public void setUserHasGroups(Set<UserHasGroup> userHasGroups) {
        this.userHasGroups = userHasGroups;
    }
     public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }
  

    
}
