package com.musicplayer.musicplayerbackend.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class GridFsService {

    @Autowired
    public GridFsService(MongoClient mongoClient, @Value("${spring.data.mongodb.database}") String databaseName, @Value("${mongodb.gridfs.bucket}") String bucketName) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.gridFSBucket = GridFSBuckets.create(database, bucketName);
    }
    private final GridFSBucket gridFSBucket;

    public void uploadMusic(MultipartFile file, String bucketName) throws IOException {
        //upload file using input stream
        InputStream inputStream = file.getInputStream();
        try {
            Document metaData = new Document();
            metaData.append("filename", file.getOriginalFilename());
            metaData.append("contentType", file.getContentType());

            gridFSBucket.uploadFromStream(file.getOriginalFilename(), inputStream, new GridFSUploadOptions().metadata(metaData));
        } finally {
            inputStream.close();
        }
    }

    public InputStream downloadFile(String fileName) throws IOException{
        GridFSFile gridFSFile = gridFSBucket.find(new Document("filename", fileName)).first();

        if (gridFSFile == null){
            throw new FileNotFoundException("File does not exist");
        }

        //get file by ID
        ObjectId objectId = gridFSFile.getObjectId();

        return gridFSBucket.openDownloadStream(objectId);
    }
}
