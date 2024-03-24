package com.musicplayer.musicplayerbackend.Service;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class GridFsService {

    @Autowired
    private GridFSBucket gridFSBucket;

    public void uploadMusic(MultipartFile file) throws IOException {
        // Convert MultipartFile to InputStream
        InputStream inputStream = file.getInputStream();

        // Set metadata for the uploaded file
        Document metaData = new Document();
        metaData.append("filename", file.getOriginalFilename());
        metaData.append("contentType", file.getContentType());

        // Optionally, you can specify additional metadata for the file

        // Upload file to GridFS bucket
        gridFSBucket.uploadFromStream(file.getOriginalFilename(), inputStream, new GridFSUploadOptions().metadata(metaData));

        // Close the input stream
        inputStream.close();
    }
}
