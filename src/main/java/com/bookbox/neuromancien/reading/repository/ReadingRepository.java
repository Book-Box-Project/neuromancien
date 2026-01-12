package com.bookbox.neuromancien.reading.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bookbox.neuromancien.reading.model.Reading;
import com.bookbox.neuromancien.reading.model.ReadingStatus;

@Repository
public interface ReadingRepository extends MongoRepository<Reading, String> {

    List<Reading> findByUserId(String userId);

    List<Reading> findByUserIdAndStatus(String userId, ReadingStatus status);

    Optional<Reading> findByUserIdAndBookId(String userId, String bookId);
}