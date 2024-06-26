package com.leveluptasks.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Date deadline;
    @Column(nullable = false)
    private Boolean completed;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private Date completedAt;
    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "expedition_id", nullable = false)
    @JsonIgnore
    private Expedition expedition;

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    public Task() {
    }

    public Task(String description, Date deadline, Priority priority, Date createdAt) {
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.createdAt = createdAt;
        this.completed = false;
    }

    public Task(String description, Date deadline, Boolean completed, Priority priority, Date completedAt, Date createdAt) {
        this.description = description;
        this.deadline = deadline;
        this.completed = completed;
        this.priority = priority;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return this.deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getCompletedAt() {
        return this.completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }
    
    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Expedition getExpedition() {
        return this.expedition;
    }

    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }
}
