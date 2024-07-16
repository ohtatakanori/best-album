package com.example.bestalbam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bestalbam.model.Post;
import com.example.bestalbam.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> findAllPosts(){
        return postRepository.findAll();
    }
}
