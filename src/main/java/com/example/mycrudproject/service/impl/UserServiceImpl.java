package com.example.mycrudproject.service.impl;

import com.example.mycrudproject.entity.User;
import com.example.mycrudproject.repository.UserRepository;
import com.example.mycrudproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> findById = userRepository.findById(id);
        if(findById.isPresent()){
            User userEntity = findById.get();
            if(user.getName() != null && !user.getName().isEmpty()){
                userEntity.setName(user.getName());
                if(user.getAge() != null){
                    userEntity.setAge(user.getAge());
                 return userRepository.save(userEntity);
                }
            }
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
