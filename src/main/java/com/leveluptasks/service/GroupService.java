package com.leveluptasks.service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
    
    public String saveGroup(GroupCreationDTO groupCreationDTO) {
        Groupe groupe = new Groupe();
        groupe.setName(groupCreationDTO.getGroupName());

        String userEmail = groupCreationDTO.getUserEmail();
        if (userEmail != null && !userEmail.isEmpty()) {
            Optional<User> userOptional = userRepository.findByEmail(userEmail);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                UserHasGroup userHasGroup = new UserHasGroup();
                userHasGroup.setUser(user);
                userHasGroup.setGroupe(groupe);

                Set<UserHasGroup> userHasGroups = new HashSet<>();
                userHasGroups.add(userHasGroup);

                groupe.setUserHasGroups(userHasGroups);
            } else {
                throw new IllegalArgumentException("User not found with email: " + userEmail);
            }
        }

        groupRepository.save(groupe);
        return "OK";
    }




    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public Groupe  updateGroup( GroupCreationDTO groupCreationDTO) {
        return groupRepository.save(new Groupe(groupCreationDTO.getGroupName()));
    }
}
