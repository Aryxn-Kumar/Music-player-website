package com.musicplayer.musicplayerbackend.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Playlist {
    @Id
    private ObjectId id;
    private String name;
    private List<Song> songs;
    private String ownerId; //User who owns the playlist
}
