package com.musicplayer.musicplayerbackend.model.Interfaces;

import com.musicplayer.musicplayerbackend.model.Song;

public interface Admin extends Users {
    void approveSong(Song song);
    void deleteArtist(Artist artist);
    // Other admin-specific methods
}
