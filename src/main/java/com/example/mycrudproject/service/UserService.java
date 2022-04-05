package com.example.mycrudproject.service;

import com.example.mycrudproject.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User createUser(User user);
    List<User> getUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
