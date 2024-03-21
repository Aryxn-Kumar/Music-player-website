package com.musicplayer.musicplayerbackend.Controllers;

import com.musicplayer.musicplayerbackend.Service.UserService;
import com.musicplayer.musicplayerbackend.model.RegularUser;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<RegularUser>> getAllUsers() {// get all the users
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")//gets user based on their object id
    public ResponseEntity<Optional<RegularUser>> getSingleUser(@PathVariable ObjectId id)
    {
        return new ResponseEntity<>(userService.singleUser(id), HttpStatus.OK);
    }

}
