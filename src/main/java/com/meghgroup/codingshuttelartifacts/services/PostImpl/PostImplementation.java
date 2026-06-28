package com.meghgroup.codingshuttelartifacts.services.PostImpl;


import com.meghgroup.codingshuttelartifacts.dto.AddPostDto;
import com.meghgroup.codingshuttelartifacts.dto.GetPosts;
import com.meghgroup.codingshuttelartifacts.entities.Posts;
import com.meghgroup.codingshuttelartifacts.entities.User;
import com.meghgroup.codingshuttelartifacts.repositories.PostRepository;
import com.meghgroup.codingshuttelartifacts.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostImplementation implements PostService {

    private final PostRepository postRepository;

    public void addPost(AddPostDto req){
        Posts posts = Posts.builder()
                .headline(req.getHeadline())
                .content(req.getContent())
                .build();
        postRepository.save(posts);
    }

    public List<GetPosts> getPosts(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("User Info -----> "+ user);
        return postRepository.findAll()
                .stream().map(post-> new GetPosts(
                        post.getId(),
                        post.getHeadline(),
                        post.getContent()
                        )).toList();
    }
}
