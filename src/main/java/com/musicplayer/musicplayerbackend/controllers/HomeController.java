package com.musicplayer.musicplayerbackend.controllers;

import org.springframework.stereotype.Controller;

@Controller("/")
public class HomeController {
    public String getLandingPage(){
        return "index";
    }
}
