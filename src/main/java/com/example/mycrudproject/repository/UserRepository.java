package com.example.mycrudproject.repository;

import com.example.mycrudproject.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
}
