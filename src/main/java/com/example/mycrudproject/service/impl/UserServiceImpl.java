package com.example.mycrudproject.service.impl;

import com.example.mycrudproject.entity.User;
import com.example.mycrudproject.exception.UserNotFoundException;
import com.example.mycrudproject.repository.UserRepository;
import com.example.mycrudproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<List<User>> getUsers() {
        return Optional.of(userRepository.findAll());
    }

    public User updateUser(User user, Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            return userRepository.save(user);
        } else {
            throw new UserNotFoundException("Can't update! User with".concat(String.valueOf(userId)).concat("not found"));
        }
    }

    public void deleteUser(Long userId) {
        User userFound = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Can't delete! User with ".concat(String.valueOf(userId)).concat("not found")));
        userRepository.delete(userFound);
    }
}
