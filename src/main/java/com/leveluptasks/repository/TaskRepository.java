package com.leveluptasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leveluptasks.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    
}