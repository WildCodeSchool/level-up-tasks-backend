package com.leveluptasks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leveluptasks.entity.UserHasGroup;
import com.leveluptasks.repository.UserHasGroupRepository;

@Service
public class UserHasGroupService {
     @Autowired
    private UserHasGroupRepository userHasGroupRepository;

    public List<UserHasGroup> getAllUserHasGroups() {
        return userHasGroupRepository.findAll();
    }

    public UserHasGroup getUserHasGroupById(Long id) {
        return userHasGroupRepository.findById(id).orElse(null);
    }

    public UserHasGroup saveUserHasGroup(UserHasGroup userHasGroup) {
        return userHasGroupRepository.save(userHasGroup);
    }

    public void deleteUserHasGroup(Long id) {
        userHasGroupRepository.deleteById(id);
    }
    
}
