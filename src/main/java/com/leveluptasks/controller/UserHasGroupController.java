package com.leveluptasks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leveluptasks.entity.UserHasGroup;
import com.leveluptasks.service.UserHasGroupService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/userhasgroups")
public class UserHasGroupController {
    @Autowired
    private UserHasGroupService userHasGroupService;

    @GetMapping
    public List<UserHasGroup> getAllUserHasGroups() {
        return userHasGroupService.getAllUserHasGroups();
    }

    @GetMapping("/{id}")
    public UserHasGroup getUserHasGroupById(@PathVariable Long id) {
        return userHasGroupService.getUserHasGroupById(id);
    }

    @PostMapping
    public UserHasGroup createUserHasGroup(@RequestBody UserHasGroup userHasGroup) {
        return userHasGroupService.saveUserHasGroup(userHasGroup);
    }

    @PutMapping("/{id}")
    public UserHasGroup updateUserHasGroup(@PathVariable Long id, @RequestBody UserHasGroup userHasGroup) {
        userHasGroup.setId(id);
        return userHasGroupService.saveUserHasGroup(userHasGroup);
    }

    @DeleteMapping("/{id}")
    public void deleteUserHasGroup(@PathVariable Long id) {
        userHasGroupService.deleteUserHasGroup(id);
    }
    
}
