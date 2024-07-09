package com.leveluptasks.controller;

import java.security.NoSuchAlgorithmException;
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
    public Groupe createGroupWithUser(@RequestBody Groupe groupe )throws NoSuchAlgorithmException {
        System.out.println("test");
        System.out.println("groupe: "+groupe.getName());

        System.out.println("groupe: "+groupe);
        return groupService.saveGroup(groupe);
       

       

        //return groupService.saveGroup(groupCreationDTO);
    }  
    @PutMapping("/{id}")
    public Groupe updateGroup( @RequestBody GroupCreationDTO groupCreationDTO, @PathVariable Long id) throws NoSuchAlgorithmException {
        return groupService.updateGroup( groupCreationDTO);
    }

     @PutMapping("/{id}/addUser")
    public Groupe addUseGroupe( @RequestBody List<String> UserEmail, @PathVariable Long id) throws NoSuchAlgorithmException {
        return groupService.addUserGroupe( id,UserEmail);
    }

    

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }
    
}
