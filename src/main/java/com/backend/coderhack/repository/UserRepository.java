package com.backend.coderhack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.coderhack.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    
}
