package com.example.demo2.controllers;


import com.example.demo2.domain.User;
import com.example.demo2.repositories.UserRepository;
import com.example.demo2.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaTray;
import java.awt.*;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {
    private  Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/AddUser")
    public @ResponseBody User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>>  getAllUsers() {
        List<User> users = userService.readAll();
        if (users.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") ObjectId id, @RequestBody User user){
        if(userService.updateUser(id, user)) {
            return ResponseEntity.ok("Updated successfully");
        }else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Update failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable ObjectId id){
        if(userService.deleteUser(id)){
            return ResponseEntity.ok("Deleted successfully");
        }else return ResponseEntity.ok("Deleted successfully");
    }
}
