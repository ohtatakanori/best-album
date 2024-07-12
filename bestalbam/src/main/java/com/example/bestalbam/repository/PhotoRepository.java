package com.example.bestalbam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bestalbam.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{
    
}
