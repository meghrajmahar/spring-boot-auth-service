package com.meghgroup.codingshuttelartifacts.services;

import com.meghgroup.codingshuttelartifacts.dto.LoginDto;
import com.meghgroup.codingshuttelartifacts.dto.LoginResponseDto;
import com.meghgroup.codingshuttelartifacts.dto.SignUpDto;
import com.meghgroup.codingshuttelartifacts.dto.UserDto;
import com.meghgroup.codingshuttelartifacts.entities.User;
import com.meghgroup.codingshuttelartifacts.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new RuntimeException("User not found "+username));
    }

    public User getUserById(UUID id){
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found by id "+id));
    }

    public UserDto signup(SignUpDto signUpDto) {
        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());

        if (user.isPresent()) {
            throw new BadCredentialsException("User with email " + signUpDto.getEmail() + " is already exists");
        }

        User toBeCreatedUser = modelMapper.map(signUpDto, User.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));
        User savedUser = userRepository.save(toBeCreatedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

}
