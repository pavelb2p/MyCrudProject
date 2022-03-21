package com.example.mycrudproject.controller;

import com.example.mycrudproject.entity.User;
import com.example.mycrudproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/list")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello from CRUD";
    }

    @PutMapping("/update/{user_id}")
    public User updateUser(@RequestBody User user,
                           @PathVariable("user_id") Long id){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{user_id}")
    public String deleteUser(@PathVariable("user_id") Long id){
        userService.deleteUser(id);

        return "delete successfully";
    }
}
