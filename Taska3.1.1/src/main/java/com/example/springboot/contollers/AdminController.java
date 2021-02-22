package com.example.springboot.contollers;

import com.example.springboot.entities.User;
import com.example.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AdminController {

    UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticated(Principal principal){
        User user = userService.findByUsername(principal.getName());
        return "Hi " + user.getUsername() + " " + user.getEmail();
    }

    @GetMapping("/read_profile")
    public String pageForReadProfile(Principal principal){
        return "Here only profile" + principal.getName();
    }

    @GetMapping("/only_for_admins")
    public String pageForAdmin(Principal principal){
        return "Hi Admin" + principal.getName();
    }
}
