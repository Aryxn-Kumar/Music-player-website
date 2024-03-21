package com.musicplayer.musicplayerbackend.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class LikedSong {
    @Id
    private ObjectId id;
    private String userId;
    private  String songId;
}
