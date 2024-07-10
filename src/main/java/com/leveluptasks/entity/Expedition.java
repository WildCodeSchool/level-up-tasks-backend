package com.leveluptasks.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "expedition")
public class Expedition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "expedition", cascade = CascadeType.ALL)
    private List<Task> tasks;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private  User user;

    public Expedition() {
    }

    public Expedition(String name) {
        this.name = name;
    }

    public Expedition(String name, List<Task> tasks, User user) {
        this.name = name;
        this.tasks = tasks;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;

        for(Task task : tasks) {
            task.setExpedition(this);
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setExpedition(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }
}
