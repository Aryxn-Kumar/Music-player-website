package com.musicplayer.musicplayerbackend.Controllers;

import com.musicplayer.musicplayerbackend.model.RegularUser;
import com.musicplayer.musicplayerbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegularUser());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("user") RegularUser user,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register"; // Show registration form with validation errors
        }
        userRepository.save(user);
        return "redirect:/registrationSuccessful"; // Redirect to registration success page
    }

    @GetMapping("/registrationSuccessful")
    public String registrationSuccess() {
        return "registrationSuccessful";
    }
}
