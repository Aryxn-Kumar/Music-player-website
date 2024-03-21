package com.musicplayer.musicplayerbackend.model.Interfaces;

import com.musicplayer.musicplayerbackend.model.Song;

public interface Artist extends Users {
    void uploadSong(Song song);
    void deleteSong(Song song);
    // Other artist-specific methods
}
