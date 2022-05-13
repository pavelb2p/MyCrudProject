package com.example.mycrudproject.controller;

import com.example.mycrudproject.dto.UserDTO;
import com.example.mycrudproject.dto.mapperdto.UserDtoMapper;
import com.example.mycrudproject.entity.User;
import com.example.mycrudproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public User saveUser(@Valid @RequestBody UserDTO userDTO) {
        User user = userDtoMapper.convertToModel(userDTO);
        return userService.createUser(user);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userService.getUsers()
                .orElseGet(Collections::emptyList);
    }

    @PutMapping("/{userId}/update")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long userId) {
        User user = userDtoMapper.convertToModel(userDTO);
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("/{userId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }
}