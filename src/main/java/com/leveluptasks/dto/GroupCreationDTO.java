package com.leveluptasks.dto;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationDTO {
   
    private String groupName;
    private List<String> userNames = new ArrayList<>();
    
    public GroupCreationDTO() {
    }

    public GroupCreationDTO(String groupName, List<String> userNames) {
        this.groupName = groupName;
        this.userNames = userNames;
    }
    


    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getUserNames() {
        return this.userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }
 

   

    
}
