package com.example.mycrudproject.repository;

import com.example.mycrudproject.entity.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends MongoRepository<Feed, String> {
}
