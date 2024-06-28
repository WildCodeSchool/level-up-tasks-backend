package com.leveluptasks.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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

    public Expedition() {
    }

    public Expedition(String name) {
        this.name = name;
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

    public void removeTask(Task task) {
        tasks.remove(task);
    }
}