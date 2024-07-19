package com.example.bestalbam.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.bestalbam.model.Photo;
import com.example.bestalbam.repository.PhotoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    // 一覧
    public List<Photo> findAllPhotos(){
        return photoRepository.findAll();
    }

    // 追加
    @Transactional
    public Photo save(Photo photo) {
        if (photo != null) {
            photo.setCreatedAt(LocalDateTime.now());
            photoRepository.save(photo);
        }
        return photo;
    }

    // 写真の保存先
    private static String UPLOADED_FOLDER = "uploads/pictures/photo";

    // 写真の追加
    @Transactional
    public Photo pictureUp(MultipartFile file, Long id) {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase() : "";
        
        String photoFilename = id + "." + fileExtension;
        try {
            Path path = Paths.get(UPLOADED_FOLDER + photoFilename);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Photo photo = findPictureById(id);

        photo.setFilepath(UPLOADED_FOLDER + photoFilename);

        return photoRepository.save(photo);

    }

    public Photo findPictureById(Long id) {
        return photoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Course Not Found With id = " + id));
    }

    
    // 論理削除
    public void deletePhotoById(Long id) {
        // idしか受け取っていないのでidでDBから取得する
        Optional<Photo> optionalPhoto = photoRepository.findById(id);
        if (optionalPhoto.isPresent()) {
            // photo(Id=1)が見つかった場合
            Photo photo = optionalPhoto.get();
            // 削除フラグを立てる
            photo.setDeleted(true);
            // 削除(delete)ではなく保存(update)する
            photoRepository.save(photo);
        }
    }
    
    // データ1件を取得する
    // public Optional<Photo> findPhotoById(Long id) {
    //     return photoRepository.findById(id);
    // }

    public Photo findPhotoById(Long id) {
        return photoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Course Not Found With id = " + id));
    }

    public List<Photo> findByFilepath(String Filepath) {
        return photoRepository.findByFilepath(Filepath);
    }

    public List<Photo> findByStatus(int status){
        return photoRepository.findByStatus(status);
    }

}