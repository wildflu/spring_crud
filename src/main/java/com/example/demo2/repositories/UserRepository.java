package com.example.demo2.repositories;

import com.example.demo2.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {
    List<User> findByUsername(String username);
    void deleteById(ObjectId id);
    boolean existsById(ObjectId id);
    User findById(ObjectId id);

    List<User> findAll();
}
