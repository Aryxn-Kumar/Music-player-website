package com.musicplayer.musicplayerbackend.repository;

import com.musicplayer.musicplayerbackend.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SongRepository extends MongoRepository<Song,String> {

}
