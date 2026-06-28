package com.meghgroup.codingshuttelartifacts.repositories;


import com.meghgroup.codingshuttelartifacts.entities.Session;
import com.meghgroup.codingshuttelartifacts.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    List<Session> findByUser(User user);
    Optional<Session> findByRefreshToken(String refreshToken);
}
