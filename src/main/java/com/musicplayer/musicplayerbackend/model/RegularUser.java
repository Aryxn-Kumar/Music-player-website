package com.musicplayer.musicplayerbackend.model;

import com.musicplayer.musicplayerbackend.model.Enum.UserRole;
import com.musicplayer.musicplayerbackend.model.Interfaces.Users;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegularUser implements Users {
    @Id
    private ObjectId id; // Use ObjectId for ID field

    private String username;

    private String name;

    private String email;

    private String password;

    private UserRole role= UserRole.USER;

    private List<LikedSong> likedSongs; // List of songs user has liked

}
