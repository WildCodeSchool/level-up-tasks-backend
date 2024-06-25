package com.leveluptasks.service;

import com.leveluptasks.entity.User;
import com.leveluptasks.repository.UserRepository;
import com.leveluptasks.tools.HashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }else {
            return null;
        }
    }
    public User saveOrUpdate(User user) throws NoSuchAlgorithmException {
        String hashedPassword = HashPassword.hashSHA512(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User login(String email, String password) throws NoSuchAlgorithmException {
        User user = new User();
        Optional<User> foundedUser = userRepository.findByEmail(email);
        if(foundedUser.isPresent()) {
            user = foundedUser.get();
            System.out.println(user.getPassword());
        }

        if (user.getPassword().equals(HashPassword.hashSHA512(password))) {
            return user;
        } else {
            return null;
        }
    }

}
