package com.example.security.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ExceptionResponse {
    private HttpStatus status;
    private String messeage;
}
