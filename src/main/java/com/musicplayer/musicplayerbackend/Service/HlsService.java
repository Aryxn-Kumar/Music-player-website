package com.musicplayer.musicplayerbackend.Service;

import com.musicplayer.musicplayerbackend.model.Interfaces.CustomLogger;
import com.musicplayer.musicplayerbackend.utilities.CleanName;
import com.musicplayer.musicplayerbackend.utilities.TempFolderCleaner;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class HlsService {

    private final CustomLogger logger;
    private final TempFolderCleaner tempFolderCleaner;

    @Autowired
    public HlsService(CustomLogger logger, TempFolderCleaner tempFolderCleaner) {
        this.logger = logger;
        this.tempFolderCleaner = tempFolderCleaner;
    }

    public String getHLSUrl(String songName){
        String sanitizedFilename = CleanName.sanitizeFilename(songName);
        sanitizedFilename = sanitizedFilename.replace("." ,"");
        return "https://musicplayerdata.blob.core.windows.net/music/" + songName + ".m3u8";
    }

    public void convertToHLS(MultipartFile file) {
        File tempFile = null;

        try {
            // Resolve the temp directory path in the project's root directory
            String projectRoot = System.getProperty("user.dir");
            File tempDirectory = new File(projectRoot, "temp");
            tempFolderCleaner.clearTempFolder();
            logger.info("Temp file cleared");

            if (!tempDirectory.exists()) {
                tempDirectory.mkdirs();
                logger.info("Tempo directory created");
            }

            // Convert the music file to HLS format
            String originalFilename = file.getOriginalFilename();
            String sanitizedFilename = CleanName.sanitizeFilename(originalFilename);
            String extension = FilenameUtils.getExtension(originalFilename); // Get the file extension
            sanitizedFilename = sanitizedFilename.replace("." + extension, "");

            // Create the temporary file in the temp directory
            tempFile = new File(tempDirectory, sanitizedFilename);
            file.transferTo(tempFile);

            // Sanitize the filename for HLS output
            String hlsSanitizedFilename = sanitizedFilename.replaceAll("[^a-zA-Z0-9]", "_");

            // Call FFmpeg to transcode and segment the audio file
            String[] ffmpegCommand = {
                    "/usr/bin/ffmpeg",
                    "-i", tempFile.getAbsolutePath(),
                    "-c:a", "aac",
                    "-b:a", "128k",
                    "-f", "hls",
                    "-hls_time", "10",
                    "-hls_list_size", "0",
                    tempDirectory.getAbsolutePath() + "/" + hlsSanitizedFilename + ".m3u8"
            };

            ProcessBuilder processBuilder = new ProcessBuilder(ffmpegCommand);
            Process process = processBuilder.start();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
