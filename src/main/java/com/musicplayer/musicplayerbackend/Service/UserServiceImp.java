package com.musicplayer.musicplayerbackend.Service;

import com.musicplayer.musicplayerbackend.model.RegularUser;
import com.musicplayer.musicplayerbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegularUser createUser(RegularUser user) {
        System.out.println("Created User: \n" + user.toString());
        return userRepository.save(user);
    }

    @Override
    public List<RegularUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserByUsername(String username) {
        Optional<RegularUser> existingUserOptional = userRepository.findByUsername(username);
        existingUserOptional.ifPresent(userRepository::delete);
        System.out.println("Deleted user: "+username);
    }

    @Override
    public Optional<RegularUser> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
