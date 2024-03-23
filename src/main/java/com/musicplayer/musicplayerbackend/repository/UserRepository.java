package com.musicplayer.musicplayerbackend.repository;



import com.musicplayer.musicplayerbackend.model.RegularUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<RegularUser,ObjectId> {
    Optional<RegularUser> findByUsername(String username);
}
