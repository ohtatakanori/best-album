package com.example.bestalbam.controller.contributor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bestalbam.model.User;
import com.example.bestalbam.service.UserService;

@Controller
@RequestMapping("/contributor/users")
public class ContributorUserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String listUser(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users/user_list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("user", new User());
        return "users/user_add";
    }

    @PostMapping("/add")
    public String add(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    
}
