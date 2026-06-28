package com.meghgroup.codingshuttelartifacts.services;

import com.meghgroup.codingshuttelartifacts.dto.AddPostDto;
import com.meghgroup.codingshuttelartifacts.dto.GetPosts;

import java.util.List;


public interface PostService {
    public void addPost(AddPostDto addPost);
    public List<GetPosts> getPosts();
}
