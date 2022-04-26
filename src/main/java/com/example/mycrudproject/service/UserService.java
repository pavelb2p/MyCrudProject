package com.example.mycrudproject.service;

import com.example.mycrudproject.entity.User;
import com.example.mycrudproject.exception.UserNotFoundException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<List<User>> getUsers();
    User updateUser(User user);
    void deleteUser(Long id) throws UserNotFoundException;
}
