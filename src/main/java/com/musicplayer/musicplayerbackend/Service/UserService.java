package com.musicplayer.musicplayerbackend.Service;

import com.musicplayer.musicplayerbackend.model.RegularUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<RegularUser> getAllUsers();

    Optional<RegularUser> singleUser(String Username);
}
