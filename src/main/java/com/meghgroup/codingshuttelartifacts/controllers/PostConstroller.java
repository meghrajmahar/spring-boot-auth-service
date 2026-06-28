package com.meghgroup.codingshuttelartifacts.controllers;


import com.meghgroup.codingshuttelartifacts.dto.AddPostDto;
import com.meghgroup.codingshuttelartifacts.dto.GetPosts;
import com.meghgroup.codingshuttelartifacts.services.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostConstroller {

    private final PostService postService;

    @PostMapping("/add")
    public void addPost(@RequestBody AddPostDto req){
        postService.addPost(req);
    }

    @GetMapping("/get")
    public ResponseEntity<List<GetPosts>> getPosts(){
        return ResponseEntity.ok(postService.getPosts());
    }
}
