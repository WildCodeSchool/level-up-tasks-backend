package com.leveluptasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leveluptasks.entity.UserHasGroup;

@Repository
public interface UserHasGroupRepository extends JpaRepository<UserHasGroup, Long>{
    
}
