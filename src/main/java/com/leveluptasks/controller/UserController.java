package com.leveluptasks.controller;


import com.leveluptasks.dto.UserDto;
import com.leveluptasks.entity.User;
import com.leveluptasks.service.UserService;
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

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("")
    public User save(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.saveOrUpdate(user);
    }
    @PutMapping("/{id}")
    public User update(@RequestBody User user , @PathVariable Long id) throws NoSuchAlgorithmException {
        return userService.saveOrUpdate(user);
    }

    @GetMapping(value="/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserDto userDto) throws NoSuchAlgorithmException {
        User user = userService.login(userDto.getEmail(), userDto.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
