package com.musicplayer.musicplayerbackend.utilities;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class TempFolderCleaner {
    public void clearTempFolder(){
        String projectRoot = System.getProperty("user.dir");
        File tempDirectory = new File(projectRoot, "temp");
        if (tempDirectory.exists()) {
            try {
                Files.walk(tempDirectory.toPath())
                        .map(Path::toFile)
                        .forEach(file -> {
                            if (file.delete()) {
                                System.out.println("Deleted file: " + file.getAbsolutePath());
                            } else {
                                System.out.println("Failed to delete file: " + file.getAbsolutePath());
                            }
                        });
                System.out.println("Temp directory cleared");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
