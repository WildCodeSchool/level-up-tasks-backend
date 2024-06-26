package com.leveluptasks.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.leveluptasks.entity.Expedition;
import com.leveluptasks.entity.Task;
import com.leveluptasks.entity.Task.Priority;

@Component
public class TaskGeneratorRepository implements CommandLineRunner {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ExpeditionRepository expeditionRepository;
    
    @Override
    public void run(String... args) throws Exception {

        List<Expedition> expeditions = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();

        if(this.expeditionRepository.count() == 0) {
            expeditions.add(new Expedition("Liste des tâches"));
            expeditions.add(new Expedition("Travail"));

            this.expeditionRepository.saveAll(expeditions);
        }

        if(this.taskRepository.count() == 0) {
            tasks.add(new Task("Réunion", Date.valueOf(LocalDate.now()), Priority.HIGH, Date.valueOf(LocalDate.now())));
            tasks.add(new Task("Yoga pendant 30min", Date.valueOf(LocalDate.now()), Priority.LOW, Date.valueOf(LocalDate.now())));
            tasks.add(new Task("Réviser", Date.valueOf(LocalDate.now()), Priority.MEDIUM, Date.valueOf(LocalDate.now())));

            this.taskRepository.saveAll(tasks);
        }
    }

}
