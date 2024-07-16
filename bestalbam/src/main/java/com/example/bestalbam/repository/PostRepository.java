package com.example.bestalbam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bestalbam.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    
}
