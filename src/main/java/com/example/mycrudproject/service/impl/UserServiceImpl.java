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

    public User updateUser(User user) {
        userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User with " + user.getId() + "not found"));
        return userRepository.save(user);
    }
//        Optional<User> findById = userRepository.findById(id)   ;
//        if (findById.isPresent()) {
//            User userEntity = findById.get();
//            userEntity.setName(user.getName());
//            userEntity.setAge(user.getAge());
//            return userRepository.save(userEntity);
//        }else {
//            throw new UserNotFoundException("User with " + id + "not found");
//        }

    public void deleteUser(Long id) throws UserNotFoundException {
        User userFound = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(userFound);
//        boolean present = userRepository.findById(id).isPresent();
//        if(present){
//            userRepository.deleteById(id);
//        }
//        else System.out.println("User with " + id + " not found! Can't delete");
    }
}
