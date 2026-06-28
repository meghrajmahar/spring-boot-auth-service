package com.meghgroup.codingshuttelartifacts.repositories;


import com.meghgroup.codingshuttelartifacts.entities.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface PostRepository extends JpaRepository<Posts, UUID> {
}
