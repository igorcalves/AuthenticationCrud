package br.com.authenticationWithCrud.authenticationCrud.model;

import java.time.LocalDateTime;

public record UserDTO(String login, String password,UserRole role,String name, String address,String secretQuestion,LocalDateTime timestamp) {
    
}