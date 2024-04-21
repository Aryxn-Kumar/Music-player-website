package com.musicplayer.musicplayerbackend.model;

import com.mongodb.client.gridfs.GridFSDownloadStream;



public class DownloadFileResponse {

    private GridFSDownloadStream downloadStream;
    private String contentType;


    public DownloadFileResponse(GridFSDownloadStream downloadStream, String contentType) {
        this.downloadStream = downloadStream;
        this.contentType = contentType;
    }

    public GridFSDownloadStream getDownloadStream() {
        return downloadStream;
    }

    public void setDownloadStream(GridFSDownloadStream downloadStream) {
        this.downloadStream = downloadStream;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }



}
