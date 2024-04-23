package com.musicplayer.musicplayerbackend.model.Interfaces;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLogger implements Logger {


    private final String loggerName;

    public CustomLogger(String loggerName) {
        this.loggerName = loggerName;
    }

    @Override
    public void info(String message) {
        log("INFO", message);
    }

    @Override
    public void warning(String message) {
        log("WARNING", message);
    }

    @Override
    public void error(String message) {
        log("ERROR", message);
    }

    private void log(String level, String message) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        System.out.println(loggerName + " : " + formattedTime + " : " + level + " : " + message);
    }
}
