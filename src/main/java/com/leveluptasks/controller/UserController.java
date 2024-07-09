package com.leveluptasks.controller;


import com.leveluptasks.dto.UserDto;
import com.leveluptasks.entity.Expedition;
import com.leveluptasks.entity.User;
import com.leveluptasks.service.UserService;
import com.leveluptasks.tools.JwtToken;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    JwtToken tokenService;

    @Operation(summary = "Get all Users", description = "Get all Users")
    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @Operation(summary = "Create User", description = "Create User")
    @PostMapping("")
    public User save(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.saveUser(user);
    }

    @Operation(summary = "Update User", description = "Update User")
    @PutMapping("/{id}")
    public User update(@RequestBody User user , @PathVariable Long id) throws NoSuchAlgorithmException {
        return userService.updateUser(user);
    }

    @Operation(summary = "Get User by here id", description = "Get User by here id")
    @GetMapping(value="/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Operation(summary = "Delete User", description = "Delete User")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @Operation(summary = "Log a user with email password", description = "Log a user with email password")
    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto) throws NoSuchAlgorithmException {
        return userService.login(userDto.getEmail(), userDto.getPassword());
    }

    @Operation(summary = "Get all user expeditions", description = "Get User expeditions by her id")
    @GetMapping("/{userId}/expeditions")
    public List<Expedition> getExpeditionsByUserId(@PathVariable Long userId) {
        return userService.getUserExpeditions(userId);
    }

}
