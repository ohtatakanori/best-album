package com.example.bestalbam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bestalbam.model.Photo;
import com.example.bestalbam.model.User;



@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{
    List<Photo> findByFilepath(String filepath);
    List<Photo> findByUser(User user);

    Photo findByid(Long id);
}
