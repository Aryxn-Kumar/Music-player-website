package com.musicplayer.musicplayerbackend.model.Interfaces;

import com.musicplayer.musicplayerbackend.model.Enum.UserRole;
import org.bson.types.ObjectId;

public interface Users {
    ObjectId getId();
    String getUsername();
    UserRole getRole();
    // Other common user methods or properties
}
