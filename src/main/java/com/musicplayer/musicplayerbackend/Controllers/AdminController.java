package com.musicplayer.musicplayerbackend.Controllers;

import com.musicplayer.musicplayerbackend.Service.UserService;
import com.musicplayer.musicplayerbackend.model.RegularUser;
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
    public ResponseEntity<List<RegularUser>> getAllUsers() {
        return new ResponseEntity<List<RegularUser>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{Username}")
    public ResponseEntity<Optional<RegularUser>> getSingleUser(@PathVariable String Username)
    {
        return new ResponseEntity<Optional<RegularUser>>(userService.singleUser(Username),HttpStatus.OK);
    }

}
