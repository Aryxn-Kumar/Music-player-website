package com.musicplayer.musicplayerbackend.Service;

import com.musicplayer.musicplayerbackend.model.Interfaces.CustomLogger;
import com.musicplayer.musicplayerbackend.model.Interfaces.SongFactory;
import com.musicplayer.musicplayerbackend.model.Song;
import com.musicplayer.musicplayerbackend.repository.SongRepository;
import com.musicplayer.musicplayerbackend.utilities.CleanName;
import com.musicplayer.musicplayerbackend.utilities.TempFolderCleaner;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class MusicService {

    private final TempFolderCleaner tempFolderCleaner;

    private final SongRepository songRepository;

    private final AzureBlobService azureBlobService;

    private final SongFactory songFactory;

    private final CustomLogger logger;

    private final HlsService hlsService;

    @Autowired
    public MusicService(TempFolderCleaner tempFolderCleaner, SongRepository songRepository, AzureBlobService azureBlobService, SongFactory songFactory, CustomLogger logger, HlsService hlsService) {
        this.tempFolderCleaner = tempFolderCleaner;
        this.songRepository = songRepository;
        this.azureBlobService = azureBlobService;
        this.songFactory = songFactory;
        this.logger = logger;
        this.hlsService = hlsService;
    }


    public void createNewSong(String songName){
        songName = CleanName.sanitizeFilename(songName);
        String songUrl = hlsService.getHLSUrl(songName);
        Song song = songFactory.createSong(songUrl, songName);
        songRepository.save(song);
        logger.info("Song persisted to DB");
    }



    public void uploadMusicToBlob(MultipartFile file) {
        String projectRoot = System.getProperty("user.dir");
        File tempDirectory = new File(projectRoot, "temp");

        String songName = file.getOriginalFilename();

        //call the function

        hlsService.convertToHLS(file);
        logger.info("File converted succesfully");

        if (tempDirectory.exists() && tempDirectory.isDirectory()) { // Check if tempDirectory is a directory
            try {
                File[] files = tempDirectory.listFiles(); // Get the list of files in the temp directory
                if (files != null) {
                    for (File doc : files) {
                        try {
                            if (azureBlobService.upload(doc)) {
                                System.out.println("Uploaded file: " + doc.getAbsolutePath());
                            } else {
                                System.out.println("Failed to upload file: " + doc.getAbsolutePath());
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                //add log

                createNewSong(songName);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.info("Song uploaded successfully");
    }






}
