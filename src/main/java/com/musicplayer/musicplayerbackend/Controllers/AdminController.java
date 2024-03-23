package com.musicplayer.musicplayerbackend.Controllers;

import com.musicplayer.musicplayerbackend.Service.UserService;
import com.musicplayer.musicplayerbackend.model.RegularUser;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/get_all_users")
    public ResponseEntity<List<RegularUser>> getAllUsers() {// get all the users
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/delete_user/{username}") // Delete user by username
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get_user/{username}") // Get user by username
    public ResponseEntity<Optional<RegularUser>> getSingleUserByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/user/create") // Create user
    public ResponseEntity<RegularUser> createUser(@RequestBody RegularUser user) {
        RegularUser createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
