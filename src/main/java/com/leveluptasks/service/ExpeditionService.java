package com.leveluptasks.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.leveluptasks.entity.User;
import com.leveluptasks.repository.TaskRepository;
import com.leveluptasks.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leveluptasks.entity.Expedition;
import com.leveluptasks.entity.Task;
import com.leveluptasks.repository.ExpeditionRepository;

@Service
public class ExpeditionService {
    @Autowired
    private ExpeditionRepository expeditionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<Expedition> getAll() {
        List<Expedition> expeditions = this.expeditionRepository.findAll();
        return expeditions;
    }

    public Expedition getById(Long id) {
        Expedition expedition = this.expeditionRepository.findById(id).get();
        return expedition;
    }

    @Transactional
    public Expedition create(Expedition expedition , Long id) {
        User user = userRepository.findById(id).get();
        expedition.setUser(user);
        return expeditionRepository.save(expedition);
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

    @Transactional
    public Task addTask(Long expeditionId, Task task) {
        Optional<Expedition> optionalExpedition = expeditionRepository.findById(expeditionId);
        if (optionalExpedition.isPresent()) {
            Expedition expedition = optionalExpedition.get();
            if (task.getId() != null) {
                task = taskRepository.findById(task.getId()).orElse(task);
            }
            task.setExpedition(expedition);
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Expedition not found");
        }
    }
    public List<Task> getTasks(Long expeditionId) {
        Expedition expedition = getById(expeditionId);
        if(expedition != null) {
            return expedition.getTasks();
        }
        return null;
    }

    @Transactional
    public void removeTask(Long expeditionId, Long taskId) {
        Expedition expedition = getById(expeditionId);
        Optional<Task> task = taskRepository.findById(taskId);
            if (task.isPresent()) {
                Task foundedTasks = task.get();
                if (foundedTasks.getExpedition().equals(expedition)) {
                    foundedTasks.setExpedition(null);
                    taskRepository.delete(task.get());
                }
            }else
                throw new RuntimeException("Task not found");
    }

    public void completeTask(Long taskId, Long expeId) {
        Optional<Expedition> optionalExpeditionexpedition = expeditionRepository.findById(expeId);
        if (!optionalExpeditionexpedition.isPresent()) {
            throw new RuntimeException("Expedition not found");
        }

        Expedition expedition = optionalExpeditionexpedition.get();
        User user = expedition.getUser();
        Task task = taskRepository.findById(taskId).get();
        if (!task.isCompleted()) {
            task.setCompleted(true);

            switch (task.getPriority()) {
                case Haute:
                    user.setRewardPercentage(user.getRewardPercentage() + 15);
                    break;
                case Moyenne:
                    user.setRewardPercentage(user.getRewardPercentage() + 10);
                    break;
                case Bas:
                    user.setRewardPercentage(user.getRewardPercentage() + 5);
                    break;
            }

            taskRepository.save(task);
        }
        boolean allTasksCompleted = expedition.getTasks().stream().allMatch(Task::isCompleted);

        if (allTasksCompleted) {
            user.setLevel(user.getLevel() + 1);
        }

        userRepository.save(user);

    }
}
