package com.leveluptasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leveluptasks.entity.Groupe;

@Repository
public interface GroupRepository extends JpaRepository<Groupe, Long>{
    
}
