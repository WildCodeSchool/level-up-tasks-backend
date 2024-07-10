package com.leveluptasks.service;

import com.leveluptasks.entity.Expedition;
import com.leveluptasks.entity.User;
import com.leveluptasks.repository.UserRepository;
import com.leveluptasks.tools.HashPassword;
import com.leveluptasks.tools.JwtToken;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtToken jwtToken;

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }else {
            return null;
        }
    }
    public User saveUser(User user) throws NoSuchAlgorithmException {
        String hashedPassword = HashPassword.hashSHA512(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User updateUser(User user) throws NoSuchAlgorithmException {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User getByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }   
    }
    public String login(String  email,String password) throws NoSuchAlgorithmException {
        Optional<User> foundedUser = userRepository.findByEmail(email);
        if (foundedUser.isPresent()) {
            User user = foundedUser.get();
            if (user.getPassword() != null && user.getPassword().equals(HashPassword.hashSHA512(password))) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("user_id", user.getId());
                claims.put("user_fullName", user.getFirstname() + " " + user.getLastname());
                return jwtToken.doGenerateToken(claims, email);
            } else {
                return null;
            }
        }
        return null;
    }

    @Transactional
    public List<Expedition> getUserExpeditions(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User foundedUser = user.get();
            return foundedUser.getExpeditions();

        }else {
            return null;
        }

    }
}
