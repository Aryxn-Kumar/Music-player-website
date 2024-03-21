package com.musicplayer.musicplayerbackend.Service;

import com.musicplayer.musicplayerbackend.model.RegularUser;
import com.musicplayer.musicplayerbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<RegularUser> getAllUsers() {
        //System.out.println(userRepository.findAll().toString());
        return userRepository.findAll();
    }
    @Override
    public Optional<RegularUser> singleUser(String Username){
        return userRepository.findUserByUsername(Username);
    }
}
