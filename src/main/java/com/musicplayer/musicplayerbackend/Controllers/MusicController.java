package com.musicplayer.musicplayerbackend.Controllers;

import com.musicplayer.musicplayerbackend.Service.GridFsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/music")
public class MusicController {

    @Autowired
    private GridFsService gridFsService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMusic(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload.", HttpStatus.BAD_REQUEST);
        }

        try {
            gridFsService.uploadMusic(file, "MusicBucket");
            return new ResponseEntity<>("File uploaded successfully.", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> getMusicFile(@PathVariable("fileName") String fileName) throws IOException {
        // Retrieve the music file content as an InputStream
        InputStream inputStream = gridFsService.downloadFile(fileName);

        // Create a Resource object to wrap the InputStream
        InputStreamResource resource = new InputStreamResource(inputStream);

        // Build a ResponseEntity with the resource and content type
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
