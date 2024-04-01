package com.musicplayer.musicplayerbackend.Controllers;

import com.musicplayer.musicplayerbackend.Service.UserService;
import com.musicplayer.musicplayerbackend.model.RegularUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String adminHome() {
        return "index"; // Assuming "index.html" is the homepage template
    }

    @GetMapping("/get_all_users")
    public String getAllUsers(Model model) {
        List<RegularUser> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "allUsers"; // Assuming there's an allUsers.html Thymeleaf template
    }

    @GetMapping("/createUser")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new RegularUser());
        return "createUser"; // Assuming there's a createUser.html Thymeleaf template
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") RegularUser user) {
        userService.createUser(user);
        return "redirect:/admin/get_all_users"; // Redirect to view all users after creating user
    }

    @GetMapping("/get_user")
    public String getUserByUsername(@RequestParam("username") String username, Model model) {
        Optional<RegularUser> userOptional = userService.getUserByUsername(username);
        if (userOptional.isPresent()) {
            RegularUser user = userOptional.get();
            model.addAttribute("user", user);
            model.addAttribute("userFound", true);
        } else {
            model.addAttribute("userFound", false);
        }
        return "index"; // Return to homepage to display search results
    }

    @PostMapping("/deleteUser")
    public String deleteUserByUsername(@RequestParam("username") String username) {
        userService.deleteUserByUsername(username);
        return "redirect:/admin/get_all_users"; // Redirect to view all users after deleting user
    }
}
