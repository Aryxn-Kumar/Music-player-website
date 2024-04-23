package com.musicplayer.musicplayerbackend.model.Interfaces;

import com.musicplayer.musicplayerbackend.model.Song;
import com.musicplayer.musicplayerbackend.utilities.CleanName;
import com.musicplayer.musicplayerbackend.utilities.HashUtility;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class SongFactoryImpl implements SongFactory{

    @Override
    public Song createSong(String songUrl, String title) {
        title = CleanName.sanitizeFilename(title);

        ObjectId songId = new ObjectId(HashUtility.generateHash(title));
        return new Song(songId, songUrl, title);
    }
}
