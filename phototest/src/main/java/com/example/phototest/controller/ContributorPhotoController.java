package com.example.phototest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.phototest.model.Photo;
import com.example.phototest.service.PhotoService;

@Controller
@RequestMapping("/contributor/photos")

public class ContributorPhotoController {
    @Autowired
    private PhotoService photoService;
    // @Autowired
    // private UserRepository userRepository;
    // @Autowired
    // private PhotoRepository photoRepository;

    @GetMapping
    public String listPhoto(Model model) {
        List<Photo> photos = photoService.findAllPhotos();
        model.addAttribute("photos", photos);
        return "photos/photo_list";
    }

    @GetMapping("/add")
    public String addPhoto(Model model) {
        model.addAttribute("photo",new Photo());
        return "photos/photo_add";
    }

    @PostMapping("/add")
    public String addPhoto(Photo photo) {
        return "redirect:/contributor/photos";
    }


}
