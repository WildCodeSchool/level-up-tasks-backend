package com.leveluptasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leveluptasks.entity.Expedition;

@Repository
public interface ExpeditionRepository extends JpaRepository<Expedition, Long>{
    
}
