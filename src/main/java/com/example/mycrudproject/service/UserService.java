package com.example.mycrudproject.service;

import com.example.mycrudproject.entity.User;
import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getUsers();

    User updateUser(Long id, User user);

    void deleteUser(Long id);

}
