package com.musicplayer.musicplayerbackend.model;

import com.musicplayer.musicplayerbackend.model.Enum.InteractionType;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Interaction {
    @Id
    private ObjectId id;
    private String userId;
    private String songId;
    private InteractionType type;
    // Other interaction-related information

    // Getters and setters
}
