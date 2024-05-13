package com.musicplayer.musicplayerbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Songs")
@Data
@NoArgsConstructor
public class Song {
    public Song(ObjectId id, String songUrl, String title) {
        this.id = id;
        this.songUrl = songUrl;
        this.title = title;
    }

    @Id
    private ObjectId id;
    private String songUrl;
    private String title;

}
