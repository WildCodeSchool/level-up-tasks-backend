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
        return groupService.saveGroup(groupe);
    }  
   
    @PutMapping("/{id}")
    public Groupe updateGroupe( @RequestBody Groupe groupe, @PathVariable Long id) throws NoSuchAlgorithmException {
        return groupService.updateGroupe( id, groupe);
    }
    

     @PutMapping("/{id}/addUser")
    public Groupe updateUserToGroupe( @RequestBody List<String> UserEmail, @PathVariable Long id) throws NoSuchAlgorithmException {
        return groupService.updateUserToGroupe( id,UserEmail);
    }

    @PostMapping("/{id}/addUser")
    public Groupe addUserToGroupe( @RequestBody List<String> UserEmail, @PathVariable Long id) throws NoSuchAlgorithmException {
        return groupService.addUserToGroupe( id,UserEmail);
    }
   
    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }
    
}
