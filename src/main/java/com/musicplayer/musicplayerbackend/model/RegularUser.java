package com.musicplayer.musicplayerbackend.model;

import com.musicplayer.musicplayerbackend.model.Enum.UserRole;
import com.musicplayer.musicplayerbackend.model.Interfaces.Users;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Users")
@Data
public class RegularUser implements Users {
    @Id
    private ObjectId id; // Use ObjectId for ID field
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private UserRole role= UserRole.USER;
    @Getter @Setter
    private List<LikedSong> likedSongs; // List of songs user has liked

    @Override
    public String toString() {
        return "RegularUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", likedSongs=" + likedSongs +
                '}';
    }

    @Override
    public ObjectId getId() {
        return null;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public UserRole getRole() {
        return null;
    }
}
