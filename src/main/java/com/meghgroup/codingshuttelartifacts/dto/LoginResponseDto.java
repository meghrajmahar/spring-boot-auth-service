package com.meghgroup.codingshuttelartifacts.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {

    private UUID id;
    private String accessToken;
    private String refreshToken;
}
