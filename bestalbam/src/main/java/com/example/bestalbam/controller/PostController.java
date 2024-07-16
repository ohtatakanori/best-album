package com.example.bestalbam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bestalbam.model.Post;
import com.example.bestalbam.service.PostService;

@Controller
@RequestMapping("")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("list")
    public String show(Model model){
        List<Post> posts = postService.findAllPosts();
        model.addAttribute("posts",posts);
        return "post/post_list";
    }
}