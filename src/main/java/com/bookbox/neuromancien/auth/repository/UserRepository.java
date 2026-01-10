package com.bookbox.neuromancien.auth.repository;

import com.bookbox.neuromancien.auth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
