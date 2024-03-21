package com.musicplayer.musicplayerbackend.model;

import com.musicplayer.musicplayerbackend.model.Enum.UserRole;
import com.musicplayer.musicplayerbackend.model.Interfaces.Artist;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Artists")
@Data
public class ArtistUser implements Artist {
    @Id
    private ObjectId id;
    private String username;
    private UserRole role=UserRole.ARTIST;
    private List<Song> uploadedSongs; // List of songs uploaded by the artist
    // Other artist-related information

    // Implement methods from User and Artist interfaces
    @Override
    public ObjectId getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public UserRole getRole() {
        return role;
    }

    @Override
    public void uploadSong(Song song) {
        // Implement upload song logic
    }

    @Override
    public void deleteSong(Song song) {
        // Implement delete song logic
    }
}
