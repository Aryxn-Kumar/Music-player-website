package com.musicplayer.musicplayerbackend.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.musicplayer.musicplayerbackend.model.Interfaces.CustomLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;

@Component
public class AzureBlobService {

    private final BlobServiceClient blobServiceClient;

    private final BlobContainerClient blobContainerClient;

    private final CustomLogger logger;

    @Autowired
    public AzureBlobService(BlobServiceClient blobServiceClient, BlobContainerClient blobContainerClient, CustomLogger logger) {
        this.blobServiceClient = blobServiceClient;
        this.blobContainerClient = blobContainerClient;
        this.logger = logger;
    }

    public boolean upload(File file) throws IOException {

        BlobClient blob = blobContainerClient.getBlobClient(file.getName());
        blob.upload(new FileInputStream(file), file.length(), true);

        logger.info("File Uploaded to Azure Blob");

        return true;
    }

    public byte[] getFile(String fileName) throws URISyntaxException {

        BlobClient blob = blobContainerClient.getBlobClient(fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blob.download(outputStream);
        final byte[] bytes = outputStream.toByteArray();
        return bytes;

    }

    public List<String> listBlobs() {

        PagedIterable<BlobItem> items = blobContainerClient.listBlobs();
        List<String> names = new ArrayList<String>();
        for (BlobItem item : items) {
            names.add(item.getName());
        }
        return names;

    }

    public Boolean deleteBlob(String blobName) {

        BlobClient blob = blobContainerClient.getBlobClient(blobName);
        blob.delete();
        return true;
    }

}