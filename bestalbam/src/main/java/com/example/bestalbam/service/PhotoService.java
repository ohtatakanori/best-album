package com.example.bestalbam.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.bestalbam.model.Photo;
import com.example.bestalbam.model.User;
import com.example.bestalbam.repository.PhotoRepository;
import com.example.bestalbam.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    // 一覧
    public List<Photo> findAllPhotos(){
        return photoRepository.findAll();
    }

    @Transactional
    public Photo save(Photo photo) {
            // セキュリティコンテキストからユーザー名を取得
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    // ユーザー名から User オブジェクトを取得
    User user = userRepository.findByUsername(username);
        photo.setCreatedAt(LocalDateTime.now());
        photo.setUser(user);
        return photoRepository.save(photo);
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
    
    public Photo findPhotoById(Long id) {
        return photoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Course Not Found With id = " + id));
    }


    @Transactional
    public Photo update(Photo updatePhoto) {

        //最初にユニーク性違反のチェックを行う
        // Photo overNumber = photoRepository.findByid(updatePhoto.getId());
        // 既に存在する場合は自作Exceptionをスロー
        // if (overNumber != null) {
        //     throw new MyException1("存在しないコースIDです。");
        // }
        
        // photo.set(SecurityContextHolder.getContext().getAuthentication().getName());
        
        Photo photo = findPhotoById(updatePhoto.getId());
        photo.setFilepath(updatePhoto.getFilepath());
        photo.setPlace(updatePhoto.getPlace());
        photo.setDescription(updatePhoto.getDescription());
        photo.setUpdatedAt(LocalDateTime.now());
        return photoRepository.save(photo);
    }

}