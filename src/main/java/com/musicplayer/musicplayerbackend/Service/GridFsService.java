package com.musicplayer.musicplayerbackend.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.musicplayer.musicplayerbackend.model.DownloadFileResponse;
import com.musicplayer.musicplayerbackend.utilities.HashUtility;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class GridFsService {

    private final GridFSBucket gridFSBucket;


    @Autowired
    public GridFsService(MongoClient mongoClient, @Value("${spring.data.mongodb.database}") String databaseName, @Value("${mongodb.gridfs.bucket}") String bucketName) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.gridFSBucket = GridFSBuckets.create(database, bucketName);
    }

    public void uploadMusic(MultipartFile file, String bucketName) throws IOException {
        //upload file using input stream
        InputStream inputStream = file.getInputStream();
        try {
            Document metaData = new Document();
            String filename = file.getOriginalFilename();
            metaData.append("filename", filename);
            metaData.append("contentType", file.getContentType());
            BsonObjectId customId = HashUtility.generateBsonId(filename);
            System.out.println("Id of the file being uploaded is "+ customId);
            gridFSBucket.uploadFromStream(customId, filename, inputStream, new GridFSUploadOptions().metadata(metaData));
        } finally {
            inputStream.close();
        }
    }

    public DownloadFileResponse downloadFile(String fileName) throws IOException{
        BsonObjectId bsonObjectId = HashUtility.generateBsonId(fileName);
        System.out.println(bsonObjectId);
        DownloadFileResponse downloadFileResponse = null;
        try  {
            GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(bsonObjectId);
            GridFSFile gridFSFile = downloadStream.getGridFSFile();
            String contentType = gridFSFile.getMetadata().getString("contentType");

            downloadFileResponse = new DownloadFileResponse(downloadStream, contentType);
        }finally {

        }
        return downloadFileResponse;
    }


}
