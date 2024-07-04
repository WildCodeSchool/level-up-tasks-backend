package com.leveluptasks.service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leveluptasks.dto.GroupCreationDTO;
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
    
      
    public String saveGroup( GroupCreationDTO groupCreationDTO) {
        Groupe groupe = new Groupe();
        groupe.setName(groupCreationDTO.getGroupName());
        Set<UserHasGroup> userHasGroups = new HashSet<>();
        List<String> userNames = groupCreationDTO.getUserNames();
        String result = "KO";
        if (userNames != null) {
            for (String userName : userNames) {
                User user = userRepository.findByFirstname(userName);
                if (user != null) {
                    UserHasGroup userHasGroup = new UserHasGroup();
                    userHasGroup.setUser(user);
                    userHasGroup.setGroupe(groupe);
                    userHasGroups.add(userHasGroup);
                    groupe.setUserHasGroups(userHasGroups);
                    result = "OK";
                }else {
                    result = "KO";
                    throw new IllegalArgumentException("Utilisateur avec le prénom '" + userName + "' non trouvé.");
                }
            }
        }
        groupRepository.save(groupe);
        return result;

      
    }


    public Groupe saveGroup(Groupe groupe) {
        return groupRepository.save(groupe);
        
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public String updateGroup(Long id, GroupCreationDTO groupCreationDTO) {
        Groupe existingGroup = groupRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Groupe avec l'ID '" + id + "' non trouvé")); 
        existingGroup.setName(groupCreationDTO.getGroupName());
        groupRepository.save(existingGroup);
        return "OK";
    }
    
}
