package com.example.demo2.services;

import com.example.demo2.domain.User;
import com.example.demo2.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.json.JsonObject;
import org.bson.json.JsonParseException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class UserService {
    private UserRepository userRepository;
    private User user;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserService(){}
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }
    public List<User> readAll() {
        return userRepository.findAll();
    }
    @Transactional
    public boolean updateUser(ObjectId id, User user) {
        boolean userExists = userRepository.existsById(id);
        if(!userExists) {
            return false;
        }
        User existingUser = userRepository.findById(id);
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());
        userRepository.save(existingUser);
        return true;
    }

    public boolean deleteUser(ObjectId id) {
        boolean userExists = userRepository.existsById(id);
        if(!userExists) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

}
