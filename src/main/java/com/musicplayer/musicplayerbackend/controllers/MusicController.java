package com.musicplayer.musicplayerbackend.controllers;
import com.musicplayer.musicplayerbackend.Service.MusicService;
import com.musicplayer.musicplayerbackend.utilities.CleanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadMusic(@RequestParam("file") MultipartFile file) {
        String convertedFile = musicService.uploadMusicToBlob(file);

        System.out.println(convertedFile);
        return ResponseEntity.ok("Music converted to HLS successfully.");
    }

    @GetMapping("/play/{fileName}")
    public ResponseEntity<String> getHLSUrl(@PathVariable String fileName) {
        fileName = CleanName.sanitizeFilename(fileName);
        String hlsUrl = musicService.getHLSUrl(fileName);
        return ResponseEntity.ok(hlsUrl);
    }


}
