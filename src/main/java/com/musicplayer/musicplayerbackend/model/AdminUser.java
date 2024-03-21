package com.musicplayer.musicplayerbackend.model;

import com.musicplayer.musicplayerbackend.model.Enum.UserRole;
import com.musicplayer.musicplayerbackend.model.Interfaces.Admin;
import com.musicplayer.musicplayerbackend.model.Interfaces.Artist;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Admin")
@Data
public class AdminUser implements Admin {
    @Id
    private ObjectId id;
    private String username;
    private UserRole role=UserRole.ADMIN;
    // Other admin-related information

    // Implement methods from User and Admin interfaces
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
    public void approveSong(Song song) {
        // Implement approve song logic
    }

    @Override
    public void deleteArtist(Artist artist) {
        // Implement delete artist logic
    }
}
