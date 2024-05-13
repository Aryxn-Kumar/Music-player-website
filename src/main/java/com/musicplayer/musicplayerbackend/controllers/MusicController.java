package com.musicplayer.musicplayerbackend.controllers;
import com.musicplayer.musicplayerbackend.Service.HlsService;
import com.musicplayer.musicplayerbackend.utilities.CleanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private HlsService hlsService;


    @GetMapping("/play")
    public String showPlayer(){
        return "musicPlayer";
    }


    @GetMapping("/play/{fileName}")
    public ResponseEntity<String> getHLSUrl(@PathVariable String fileName) {
        fileName = CleanName.sanitizeFilename(fileName);
        String hlsUrl = hlsService.getHLSUrl(fileName);
        return ResponseEntity.ok(hlsUrl);
    }


}
