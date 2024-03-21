package com.musicplayer.musicplayerbackend.model;

import com.musicplayer.musicplayerbackend.model.Enum.UserRole;
import com.musicplayer.musicplayerbackend.model.Interfaces.Users;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Users")
@Data
public class RegularUser implements Users {
    @Id
    private ObjectId id; // Use ObjectId for ID field
    private String Username;
    private String name;
    private String email;
    private UserRole role= UserRole.USER;
    private List<LikedSong> likedSongs; // List of songs user has liked
    // Other user-related information such as password, name, email, etc.

    @Override
    public ObjectId getId() {
        return id ; // Convert ObjectId to String
    }

    @Override
    public String getUsername() {
        return Username;
    }

    @Override
    public UserRole getRole() {
        return role;
    }
}
