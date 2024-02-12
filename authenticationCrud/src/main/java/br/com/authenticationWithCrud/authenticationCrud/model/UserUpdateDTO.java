package br.com.authenticationWithCrud.authenticationCrud.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

        private String login;
        private String password;
        private String name;
        private String address;
        @JsonProperty(required = false)
        private UserRole role; 


}
