package com.leveluptasks.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leveluptasks.entity.Groupe;
import com.leveluptasks.entity.User;
import com.leveluptasks.entity.UserHasGroup;
import com.leveluptasks.repository.GroupRepository;
import com.leveluptasks.repository.UserHasGroupRepository;
import com.leveluptasks.repository.UserRepository;



@Service
public class GroupService {
     @Autowired
    private GroupRepository groupRepository;

     @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHasGroupRepository userHasGroupRepository;
    

    

    public List<Groupe> getAllGroups() {
        return groupRepository.findAll();
    }

    public Groupe getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Groupe saveGroup(Groupe groupe) {
        return groupRepository.save(groupe);
    }

    public Groupe addUserToGroupe (Long id , List<String> UserEmail) {
        Groupe groupe = groupRepository.findById(id).orElse(null);
        if (groupe != null) {
            for (String email : UserEmail) {
                Optional<User> userOptional = userRepository.findByEmail(email);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    UserHasGroup userHasGroup = new UserHasGroup();
                    userHasGroup.setUser(user);
                    userHasGroup.setGroupe(groupe);
                    userHasGroupRepository.save(userHasGroup);
                } else {
                    throw new IllegalArgumentException("User not found with email: " + email);
                }
            }
        }
        groupRepository.save(groupe);
        return groupe;
    }
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }
    public Groupe updateGroupe(Long id,Groupe groupe) {
        return groupRepository.save(groupe);
    }

    public Groupe updateUserToGroupe(Long id , List<String> UserEmail) {
        Groupe groupe = groupRepository.findById(id).orElse(null);
        if (groupe != null) {
            //Check the UserEmail to see if this list contains new members
            for (String email : UserEmail) {
                Optional<User> userOptional = userRepository.findByEmail(email);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    if(!groupe.hasUser(user)){
                        UserHasGroup userHasGroup = new UserHasGroup();
                        userHasGroup.setUser(user);
                        userHasGroup.setGroupe(groupe);
                        userHasGroupRepository.save(userHasGroup);
                    }
                } else {
                    throw new IllegalArgumentException("User not found with email: " + email);
                }
            }
            //Check if UserEmail does not contain an user present in groupe
            for (UserHasGroup userHasGroup : groupe.getUserHasGroups()) {
                if(!UserEmail.contains(userHasGroup.getUser().getEmail())){
                    userHasGroupRepository.delete(userHasGroup);
                }
            }
        }

        groupRepository.save(groupe);
        return groupe;
    }


    
}
