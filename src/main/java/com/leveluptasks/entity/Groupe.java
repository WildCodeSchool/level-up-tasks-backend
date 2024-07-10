package com.leveluptasks.entity;


import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "groupe")
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idgroup;
    @NotNull(message = "Group name cannot be null")
    private String name;

    @OneToMany(mappedBy = "groupe",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIncludeProperties( {"id", "user"})
    private Set<UserHasGroup> userHasGroups;


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
 

    
}
