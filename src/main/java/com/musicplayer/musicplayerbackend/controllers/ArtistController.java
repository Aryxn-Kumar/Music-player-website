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
    MusicService musicService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMusic(@RequestParam("file") MultipartFile file) {
        String convertedFile = musicService.uploadMusicToBlob(file);

        System.out.println(convertedFile);
        return ResponseEntity.ok("Music converted to HLS successfully.");
    }

    @GetMapping("/upload")
    public String uploadPage(){
        return "musicUpload";
    }

}
