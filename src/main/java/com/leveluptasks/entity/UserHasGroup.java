
package com.leveluptasks.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "userhasgroup")
public class UserHasGroup {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "groupe_idgroup")
    private Groupe groupe;

    private boolean isAdmin;
 
    public UserHasGroup() {
    }

    public UserHasGroup(User user, Groupe groupe, boolean isAdmin) {
        this.user = user;
        this.groupe = groupe;
        this.isAdmin = isAdmin;
    }

   

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Groupe getGroupe() {
        return this.groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }


    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    
}
