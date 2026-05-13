package com.example.PasswordManager.service.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.PasswordManager.service.apiResponse.ApiResponseDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDTO> handleResourceNotFound(ResourceNotFoundException ex) {

        return new ResponseEntity<>(
                new ApiResponseDTO(ex.getMessage(), null),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO> handleGeneralException(Exception ex) {

        return new ResponseEntity<>(
                new ApiResponseDTO("Internal server error", null),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}