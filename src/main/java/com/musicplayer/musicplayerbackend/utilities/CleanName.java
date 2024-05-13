package com.musicplayer.musicplayerbackend.utilities;

public class CleanName {
    public static String sanitizeFilename(String filename) {
        // Remove special characters and spaces, convert to lowercase
        return filename.replaceAll("[^a-zA-Z0-9.-]", "").toLowerCase();
    }
}
