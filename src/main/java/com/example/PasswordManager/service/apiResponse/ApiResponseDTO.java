package com.example.PasswordManager.service.apiResponse;


    public class ApiResponseDTO {
    private String message;
    private Object data;
        public ApiResponseDTO(String message){
            this.message=message;
        }
         public ApiResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
    public String getMessage() {
        return message;
    }

        public Object getData() {
            return data;
        }
    }
