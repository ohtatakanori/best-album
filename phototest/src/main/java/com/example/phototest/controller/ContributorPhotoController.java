package com.example.phototest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.phototest.model.Photo;
import com.example.phototest.repository.PhotoRepository;
import com.example.phototest.service.PhotoService;

@Controller
@RequestMapping("/contributor/photos")

public class ContributorPhotoController {
    @Autowired
    private PhotoService photoService;
    @Autowired
    private PhotoRepository photoRepository;
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
    public String addPhoto(@RequestParam("file") MultipartFile file,Photo photo) {
        // photoRepository.save(photo);
                String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null
                ? originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase()
                : "";
        try {
            Path path = Paths.get("/src/main/resources/static/uploads/" + originalFilename);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            // photo.setFilepath(path.toString());
            photo.setFilepath(path.toString());
            photoService.save(photo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/contributor/photos";
    }
}
