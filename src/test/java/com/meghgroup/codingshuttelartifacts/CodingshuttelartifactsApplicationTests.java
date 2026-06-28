package com.meghgroup.codingshuttelartifacts;

import com.meghgroup.codingshuttelartifacts.entities.User;
import com.meghgroup.codingshuttelartifacts.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CodingshuttelartifactsApplicationTests {

    @Autowired
    private JwtService jwtService;

	@Test
	void contextLoads() {

        User user = new User(
                UUID.fromString("58f0bfc0-2fc7-4b1a-ab2e-d70229d645b2"),
                "megh@gmail.com",
                "12345",
                "meghraj"
        );

        String token = jwtService.generateAccessToken(user);

        System.out.println("Token ---> "+token);

        UUID id = jwtService.getUserIdFromToken(token);
        System.out.println("UUID---> "+id);
	}

}
