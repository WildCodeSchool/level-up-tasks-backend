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

        if(this.expeditionRepository.count() == 0 && this.taskRepository.count() == 0) {
            expeditions.add(new Expedition("Liste des tâches"));
            expeditions.add(new Expedition("Travail"));

            tasks.add(new Task("Réunion", Date.valueOf(LocalDate.now()), Priority.Haute, Date.valueOf(LocalDate.now())));
            tasks.add(new Task("Yoga pendant 30min", Date.valueOf(LocalDate.now()), Priority.Bas, Date.valueOf(LocalDate.now())));
            tasks.add(new Task("Réviser", Date.valueOf(LocalDate.now()), Priority.Moyenne, Date.valueOf(LocalDate.now())));
            
            tasks.get(0).setExpedition(expeditions.get(1));
            tasks.get(1).setExpedition(expeditions.get(0));
            tasks.get(2).setExpedition(expeditions.get(0));

            this.expeditionRepository.saveAll(expeditions);
            this.taskRepository.saveAll(tasks);
        }
    }

}