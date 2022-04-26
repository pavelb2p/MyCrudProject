package com.example.mycrudproject.controller;

import com.example.mycrudproject.entity.User;
import com.example.mycrudproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/list")
    public Optional<List<User>> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/{userId}/update")
    public User updateUser(@RequestBody User user, @PathVariable String userId) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
    }
}
