package com.leveluptasks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leveluptasks.entity.Expedition;
import com.leveluptasks.entity.Task;
import com.leveluptasks.repository.ExpeditionRepository;

@Service
public class ExpeditionService {
    @Autowired
    private ExpeditionRepository expeditionRepository;
    
    public List<Expedition> getAll() {
        List<Expedition> expeditions = this.expeditionRepository.findAll();
        return expeditions;
    }

    public Expedition getById(Long id) {
        Expedition expedition = this.expeditionRepository.findById(id).get();
        return expedition;
    }

    public Expedition create(Expedition expedition) {
        Expedition createdExpedition = this.expeditionRepository.save(expedition);
        return createdExpedition;
    }

    public Expedition update(Long id, Expedition expedition) {
        Expedition retrievedExpedition = this.expeditionRepository.findById(id).get();
        retrievedExpedition.setName(expedition.getName());
        Expedition updatedExpedition = this.expeditionRepository.save(retrievedExpedition);
        return updatedExpedition;
    }

    public void delete(Long id) {
        this.expeditionRepository.deleteById(id);
    }

    public Expedition addTask(Long expeditionId, Task task) {
        Expedition expedition = getById(expeditionId);
        if(expedition != null) {
            expedition.addTask(task);
            return create(expedition);
        }
        return null;
    }

    public Expedition removeTask(Long expeditionId, Task task) {
        Expedition expedition = getById(expeditionId);
        if(expedition != null) {
            if (expedition.getTasks().contains(task)) {
                expedition.removeTask(task);
                return create(expedition);
            }
        }
        return null;
    }
}
