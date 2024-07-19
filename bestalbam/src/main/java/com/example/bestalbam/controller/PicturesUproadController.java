package com.example.bestalbam.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.bestalbam.model.Photo;
import com.example.bestalbam.service.PhotoService;

@Controller
public class PicturesUproadController {

    @Autowired
    private PhotoService photoService;

    // ファイルの保存先
    //★この場合、プロジェクト直下のuploads/に保存される。
    private static String UPLOADED_FOLDER = "subuploads/";

    // @GetMapping("/upload")
    // public String singleFileUpload(Model model){
    //     model.addAttribute("photo",new Photo)
    // }


    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,Photo photo,Model model) {
        
        try {
            // 1. ファイルの保存先(ファイル名を含む)を決定
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            //
            // 2. 書き出す事で保存
            //★既存のファイル(uploads)に保存するメソッド。
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        
        } catch (IOException e) {
            e.printStackTrace();
        }

        photo.setFilepath(file.getOriginalFilename());
        photoService.save(photo);
        model.addAttribute("photo",photo);
        return "redirect:/contributor/photos/add";
    }
}
