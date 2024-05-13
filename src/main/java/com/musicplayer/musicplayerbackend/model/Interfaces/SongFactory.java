package com.musicplayer.musicplayerbackend.model.Interfaces;

import com.musicplayer.musicplayerbackend.model.Song;

public interface SongFactory {
    Song createSong(String songUrl, String title);
}
