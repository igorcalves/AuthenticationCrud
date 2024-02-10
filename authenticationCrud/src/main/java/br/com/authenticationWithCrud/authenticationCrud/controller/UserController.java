package br.com.authenticationWithCrud.authenticationCrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.authenticationWithCrud.authenticationCrud.model.LoginDTO;
import br.com.authenticationWithCrud.authenticationCrud.model.User;
import br.com.authenticationWithCrud.authenticationCrud.model.UserDTO;
import br.com.authenticationWithCrud.authenticationCrud.model.UserDTODataBase;
import br.com.authenticationWithCrud.authenticationCrud.repository.UserRepository;
import br.com.authenticationWithCrud.authenticationCrud.service.UserService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    UserService service;

 


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO data){
        User user = service.createUser(data);
        if(user == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
    }

    
    @GetMapping
    public ResponseEntity<List<UserDTODataBase>> findAllUser(){
        List<UserDTODataBase> users = service.findAllUsers();

        return ResponseEntity.ok().body(users);
        
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDTO data){
        User user = service.updateUser(data);

        if(user != null){
            return ResponseEntity.ok().body("User has been updted");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login not Found");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody @Valid LoginDTO data){
        if(service.deleteUser(data)){
            return ResponseEntity.ok().body("User has been deleted");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login not Found");
        }
    }

    
}
