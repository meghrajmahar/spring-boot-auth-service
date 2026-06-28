package com.meghgroup.codingshuttelartifacts.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiError {

    private LocalDateTime timestamp;
    private String message;
    private int status;
    private String error;

    public ApiError(String message, HttpStatus status) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.status = status.value();
        this.error = status.getReasonPhrase();
    }
}
