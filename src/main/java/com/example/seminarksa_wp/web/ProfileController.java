package com.example.seminarksa_wp.web;

import com.example.seminarksa_wp.model.Rating;
import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.exceptions.UserIdNotFoundException;
import com.example.seminarksa_wp.service.RatingService;
import com.example.seminarksa_wp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;
    private final RatingService ratingService;

    public ProfileController(UserService userService, RatingService ratingService) {
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @GetMapping
    public String getMyProfile(Authentication authentication, Model model)
    {
        User user = (User) authentication.getPrincipal();
        List<Rating> ratings = this.ratingService.listAllByUser(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("ratings",ratings);
        model.addAttribute("bodyContent","profile");
        return "master-template";
    }
    @GetMapping ("/myRatings")
    public String getMyRatings(Model model){
        return "master-template";
    }
}
