package com.musicplayer.musicplayerbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Songs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    public Song(String fileName) {
        this.fileName = fileName;
    }

    @Id
    private ObjectId Id;
    private String fileName;
    private String title;
    private String artist;
    private String album;
    private String duration;
    private int likes;
}
