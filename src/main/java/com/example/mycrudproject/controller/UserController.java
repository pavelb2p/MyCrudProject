package com.example.mycrudproject.controller;

import com.example.mycrudproject.entity.User;
import com.example.mycrudproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@RequestBody User user,
                           @PathVariable("userId") Long id) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);

        return "delete successfully";
    }
}
