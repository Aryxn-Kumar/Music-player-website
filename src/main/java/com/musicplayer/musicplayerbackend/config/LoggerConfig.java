package com.musicplayer.musicplayerbackend.config;

import com.musicplayer.musicplayerbackend.model.Interfaces.CustomLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {

    @Bean
    public CustomLogger customLogger() {
        return new CustomLogger("AudioVerse");
    }
}
