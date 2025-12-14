package com.nikhil.usermanagement.repository;

import com.nikhil.usermanagement.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // CRUD methods are ready:
    // findAll(), findById(), save(), deleteById()
}
