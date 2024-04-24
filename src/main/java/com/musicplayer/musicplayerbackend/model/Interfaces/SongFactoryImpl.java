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
        String cleanedTitle = CleanName.sanitizeFilename(title);

        ObjectId songId = new ObjectId(HashUtility.generateHash(cleanedTitle));
        return new Song(songId, songUrl, title);
    }
}
