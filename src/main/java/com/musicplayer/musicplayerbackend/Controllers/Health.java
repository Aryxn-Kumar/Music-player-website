package com.musicplayer.musicplayerbackend.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {

    @GetMapping("/health")
    public String healthCheck(){
        return "200 OK";
    }
}
