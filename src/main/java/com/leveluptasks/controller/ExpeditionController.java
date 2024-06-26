package com.leveluptasks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Book;
import com.leveluptasks.entity.Expedition;
import com.leveluptasks.entity.Task;
import com.leveluptasks.service.ExpeditionService;
import com.leveluptasks.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/expeditions")
public class ExpeditionController {
    @Autowired
    private ExpeditionService expeditionService;
    private TaskService taskService;

    @Operation(summary = "Get all expeditions", description = "Get all expeditions")
    @GetMapping("")
    public List<Expedition> getAll() {
        return this.expeditionService.getAll();
    }

    @Operation(summary = "Get expedition by id", description = "Get expedition by id")
    @GetMapping("/{id}")
    public Expedition getById(@PathVariable Long id) {
        return this.expeditionService.getById(id);
    }

    @Operation(summary = "Create expedition", description = "Create expedition")
    @PostMapping("")
    public Expedition create(@RequestBody Expedition expedition) {
        return this.expeditionService.create(expedition);
    }

    @Operation(summary = "Update expedition", description = "Update expedition")
    @PutMapping("/{id}")
    public Expedition update(@PathVariable Long id, @RequestBody Expedition expedition) {
        return this.expeditionService.update(id, expedition);
    }

    @Operation(summary = "Delete expedition", description = "Delete expedition")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.expeditionService.delete(id);
    }

    @Operation(summary = "Add Task to expedition", description = "Add Task to expedition")
    @PostMapping("/{expeditionId}/tasks")
    public Expedition addTask(@PathVariable Long expeditionId, @RequestBody Task task) {
        return expeditionService.addTask(expeditionId, task);
    }

    @Operation(summary = "Delete Task from expedition", description = "Delete Task from expedition")
    @DeleteMapping("/{expeditionId}/tasks/{taskId}")
    public Expedition removeTask(@PathVariable Long expeditionId, @PathVariable Long taskId) {
        return expeditionService.removeTask(expeditionId, taskService.getById(taskId));
    }
}
