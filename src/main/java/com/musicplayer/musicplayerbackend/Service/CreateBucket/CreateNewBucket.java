package com.musicplayer.musicplayerbackend.Service.CreateBucket;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.FileInputStream;
import java.io.*;

public class CreateNewBucket {
    public static void main(String[] args) throws FileNotFoundException {
        // MongoDB connection URI
        String connectionString = "mongodb+srv://root:root@musicplayer.vwpsoee.mongodb.net";

        // Name of the database where GridFS bucket will be created
        String databaseName = "Music-player-website";
        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("myMongoDb");


        GridFSBucket gridFSBucket = GridFSBuckets.create(database, "MusicBucket");

        String filePath = "src/main/java/com/musicplayer/musicplayerbackend/Service/CreateBucket/test.zip";
        try (InputStream streamToUploadFrom = new FileInputStream(filePath) ) {
            GridFSUploadOptions options = new GridFSUploadOptions()
                    .chunkSizeBytes(1048576)
                    .metadata(new Document("type", "zip archive"));

            ObjectId fileId = gridFSBucket.uploadFromStream("myProject.zip", streamToUploadFrom, options);

            System.out.println("The file id of the uploaded file is: " + fileId.toHexString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
