package com.leveluptasks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leveluptasks.dto.GroupCreationDTO;
import com.leveluptasks.entity.Groupe;
import com.leveluptasks.service.GroupService;



@RestController
@RequestMapping("/groupes")
public class GroupController {
     @Autowired
    private GroupService groupService;
  

    @GetMapping
    public List<Groupe> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public Groupe getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @PostMapping
    public String createGroupWithUser(@RequestBody GroupCreationDTO groupCreationDTO) {
        return groupService.saveGroup(groupCreationDTO);
    }  
    @PutMapping("/{id}")
    public String updateGroup(@PathVariable Long id, @RequestBody GroupCreationDTO groupCreationDTO) {
        return groupService.updateGroup(id, groupCreationDTO);
    }

   

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }
    
}
