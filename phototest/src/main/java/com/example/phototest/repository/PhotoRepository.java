package com.example.phototest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.phototest.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{
    // List<Photo> findByFilepath(String filepath);
    // List<Photo> findByUser(User user);

    // Photo findByid(Long id);
}
