package com.leveluptasks.controller;

import java.util.List;

import com.leveluptasks.entity.Expedition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leveluptasks.entity.Task;
import com.leveluptasks.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Get all tasks", description = "Get all tasks")
    @GetMapping("")
    public List<Task> getAll() {
        return this.taskService.getAll();
    }

    @Operation(summary = "Get task by id", description = "Get task by id")
    @GetMapping("/{id}")
    public Task getById(@PathVariable Long id) {
        return this.taskService.getById(id);
    }

    @Operation(summary = "Create task", description = "Create task")
    @PostMapping("")
    public Task create(@RequestBody Task task) {
        return this.taskService.create(task);
    }

    @Operation(summary = "Update task", description = "Update task")
    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
        return this.taskService.update(id, task);
    }

    @Operation(summary = "Delete task", description = "Delete task")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.taskService.delete(id);
    }

    @PostMapping("/{taskId}/expedition")
    public Expedition getExpByTask(@PathVariable Long taskId) {
        return taskService.getExpByTask(taskId);
    }
}
