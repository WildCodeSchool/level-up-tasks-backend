package com.leveluptasks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leveluptasks.entity.Task;
import com.leveluptasks.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    
    public List<Task> getAll() {
        List<Task> tasks = this.taskRepository.findAll();
        return tasks;
    }

    public Task getById(Long id) {
        Task task = this.taskRepository.findById(id).get();
        return task;
    }

    public Task create(Task task) {
        Task createdTask = this.taskRepository.save(task);
        return createdTask;
    }

    public Task update(Long id, Task task) {
        Task retrievedTask = this.taskRepository.findById(id).get();
        retrievedTask.setDescription(task.getDescription());
        retrievedTask.setDeadline(task.getDeadline());
        retrievedTask.setPriority(task.getPriority());
        retrievedTask.setCompleted(task.isCompleted());
        retrievedTask.setCompletedAt(task.getCompletedAt());
        Task updatedTask = this.taskRepository.save(retrievedTask);
        return updatedTask;
    }

    public void delete(Long id) {
        this.taskRepository.deleteById(id);
    }
}
