package com.example.bestalbam.controller.contributor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        userService.addEnableStudentAndHashPassword(user);
        return "redirect:/contributor/users";
    }

    // @PostMapping("/add")
    // public String add(@Valid User user, BindingResult result, Model model) {
    //     if (result.hasErrors()) {
    //         model.addAttribute("errors", result.getAllErrors());
    //         return "users/user-add";
    //     }
    //     userService.saveUser(user);
    //     return "redirect:/contributor/users";
    // }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "users/user_edit";
    }

    @PostMapping("/edit")
    public String edit(User user) {
        userService.update(user);
        return "redirect:/contributor/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "/users/user_delete";
    }

    @PostMapping("/delete")
    public String delete(User user) {
        userService.delete(user);
        return "redirect:/contributor/users";
    }
}
