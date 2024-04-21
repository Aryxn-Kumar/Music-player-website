package com.musicplayer.musicplayerbackend.Service;

import com.musicplayer.musicplayerbackend.model.RegularUser;
import com.musicplayer.musicplayerbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;
    public List<RegularUser> allUsers(){
        System.out.println("Returning all users from admin service");
        return userRepository.findAll();
    }
}
