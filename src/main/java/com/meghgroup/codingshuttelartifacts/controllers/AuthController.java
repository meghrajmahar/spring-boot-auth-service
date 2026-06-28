package com.meghgroup.codingshuttelartifacts.controllers;


import com.meghgroup.codingshuttelartifacts.dto.LoginDto;
import com.meghgroup.codingshuttelartifacts.dto.LoginResponseDto;
import com.meghgroup.codingshuttelartifacts.dto.SignUpDto;
import com.meghgroup.codingshuttelartifacts.dto.UserDto;
import com.meghgroup.codingshuttelartifacts.services.AuthService;
import com.meghgroup.codingshuttelartifacts.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Value("${deploy.env}")
    private String deployEnv;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signupUser(@RequestBody SignUpDto request){
        UserDto userDto = userService.signup(request);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto req,
                                                  HttpServletRequest servletReq,
                                                  HttpServletResponse servletRes){
        LoginResponseDto loginResponseDto = authService.login(req);
        String refreshToken = loginResponseDto.getRefreshToken();
        Cookie cookie = new Cookie("refreshToken",refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv)); // this is work only if https secure request comes
        servletRes.addCookie(cookie);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest req){

        String refreshToken = String.valueOf(Arrays.stream(req.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()->new AuthenticationServiceException("Refresh token not found inside cookie"))
        );

        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(loginResponseDto);
    }
}
