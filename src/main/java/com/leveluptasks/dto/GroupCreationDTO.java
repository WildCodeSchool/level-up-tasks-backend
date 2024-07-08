package com.leveluptasks.dto;


import jakarta.validation.constraints.NotNull;

public class GroupCreationDTO {
    @NotNull(message = "Group name cannot be null")
    private String groupName;
   
    private String userEmail;
    
    public GroupCreationDTO() {
    }

    public GroupCreationDTO(String groupName, String userEmail) {
        this.groupName = groupName;
        this.userEmail = userEmail;
    }
    


    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail( String userEmail) {
        this.userEmail = userEmail;
    }
 

   

    
}
