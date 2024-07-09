package com.leveluptasks.dto;


import java.util.List;

import jakarta.validation.constraints.NotNull;

public class GroupCreationDTO {
    @NotNull(message = "Group name cannot be null")
    private String groupName;
   
    private List<String> userEmail;
    
    public GroupCreationDTO() {
    }

    public GroupCreationDTO(String groupName, List<String> userEmail) {
        this.groupName = groupName;
        this.userEmail = userEmail;
    }
    
    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(List<String> userEmail) {
        this.userEmail = userEmail;
    }
 

   

    
}
