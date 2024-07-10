package com.leveluptasks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.leveluptasks.entity.Expedition;
import com.leveluptasks.entity.Task;
import com.leveluptasks.service.ExpeditionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/expeditions")
public class ExpeditionController {
    @Autowired
    private ExpeditionService expeditionService;

    @Operation(summary = "Get all expeditions", description = "Get all expeditions")
    @GetMapping("")
    public List<Expedition> getAll() {
        return this.expeditionService.getAll();
    }



    @GetMapping("/{expeditionId}/tasks")
    public List<Task> getTasksByExpeditionId(@PathVariable Long expeditionId) {
        return  expeditionService.getTasks(expeditionId);

    }
    @Operation(summary = "Get expedition by id", description = "Get expedition by id")
    @GetMapping("/{id}")
    public Expedition getById(@PathVariable Long id) {
        return this.expeditionService.getById(id);
    }

    @Operation(summary = "Create expedition", description = "Create expedition")
    @PostMapping("/{userId}")
    public Expedition create(@RequestBody Expedition expedition, @PathVariable Long userId) {
        return this.expeditionService.create(expedition,userId);
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
    public ResponseEntity<Task> addTask(@PathVariable Long expeditionId, @RequestBody Task task) {
        Task createdTask = expeditionService.addTask(expeditionId, task);
        return ResponseEntity.ok(createdTask);
    }

    @Operation(summary = "Delete Task from expedition", description = "Delete Task from expedition")
    @DeleteMapping("/{expeditionId}/tasks/{taskId}")
    public void removeTask(@PathVariable Long expeditionId, @PathVariable Long taskId) {
        expeditionService.removeTask(expeditionId,taskId);
    }


    @Operation(summary = "Increment user reward and level ", description = "Increment user reward and level when task is completed")
    @PostMapping("/complete/{expeId}/{taskId}")
    public void completeTask( @PathVariable Long expeId,@PathVariable Long taskId) {
        expeditionService.completeTask(expeId,taskId);

    }
}
