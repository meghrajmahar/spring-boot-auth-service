package com.meghgroup.codingshuttelartifacts.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPostDto {

    private String headline;

    private String content;

}
