package com.example.bestalbam.controller.contributor;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.bestalbam.model.Photo;
import com.example.bestalbam.service.PhotoService;

@Controller
@RequestMapping("/contributor/photos")
public class ContributorPhotoController {
    @Autowired
    private PhotoService photoService;

    @GetMapping
    public String listPhoto(Model model){
        List<Photo> photos = photoService.findAllPhotos();
        model.addAttribute("photos",photos);
        return "photos/photo_list";
    }

    @GetMapping("/add")
    public String addPhoto(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        model.addAttribute("photo", new Photo());
        return "photos/photo_add";
    }

    @PostMapping("/add")
    public String add(Photo photo) {
        photoService.save(photo);
        return "redirect:/photos";
    }
    
    @GetMapping("add/pictures")
    public String pictureForm(@PathVariable("id") Long id, Model model) {
        Photo photo = photoService.findPhotoById(id);
        model.addAttribute("photo", photo);
        return "/admin/course-image-add";
    }

    @PostMapping("add/pictures")
    public String pictureUp(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase() : "";
        if (!fileExtension.equals("png")) {
            return "redirect:/admin/courses";
        }
        photoService.pictureUp(file, id);
        return "redirect:/admin/courses";
    }
}
