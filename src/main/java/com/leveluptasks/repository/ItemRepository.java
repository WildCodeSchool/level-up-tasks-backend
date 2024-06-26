package com.leveluptasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leveluptasks.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
    
}
