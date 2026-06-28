package com.meghgroup.codingshuttelartifacts.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPosts {

    private UUID id;

    private String headline;

    private String content;

}
