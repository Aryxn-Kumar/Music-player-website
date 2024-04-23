package com.musicplayer.musicplayerbackend.controllers;

import com.musicplayer.musicplayerbackend.Service.MusicService;
import com.musicplayer.musicplayerbackend.utilities.CleanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private final MusicService musicService;

    public ArtistController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping("/upload")
    public String uploadMusic(@RequestParam("file") MultipartFile file) {
        musicService.uploadMusicToBlob(file);

        return "musicUpload";
    }

    @GetMapping("/upload")
    public String uploadPage(){
        return "musicUpload";
    }

}
