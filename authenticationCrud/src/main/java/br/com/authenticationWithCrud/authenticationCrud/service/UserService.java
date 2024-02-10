package br.com.authenticationWithCrud.authenticationCrud.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.authenticationWithCrud.authenticationCrud.model.LoginDTO;
import br.com.authenticationWithCrud.authenticationCrud.model.User;
import br.com.authenticationWithCrud.authenticationCrud.model.UserDTO;
import br.com.authenticationWithCrud.authenticationCrud.model.UserDTODataBase;
import br.com.authenticationWithCrud.authenticationCrud.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;


     public List<UserDTODataBase> findAllUsers(){
         List<UserDTODataBase> users = repository.findAllUsers();
         return users;
        }
        
    public User createUser(UserDTO data){
        if(!hasUser(data)){
        	String encryptPassword = new BCryptPasswordEncoder().encode(data.password());
            User user =  new User(data.login(),encryptPassword,data.role(),data.name(),data.address(),LocalDateTime.now());
            repository.save(user);
            return user;
        }
        else{
            return null;
        }

 
    }

  
    public User updateUser(UserDTO data){
        if(hasUser(data)){
            User user = repository.findUserByLogin(data.login());
            user.setPassword(data.password());
            user.setName(data.name());
            user.setAddress(data.address());
            repository.save(user);
            return user;
        }{
            return null;
        }
    
    }

    public Boolean deleteUser(LoginDTO data){
        
        if(hasUser(data)){
            User user = repository.findUserByLogin(data.login());
            repository.delete(user);
            return true;
        }
        return false;
    }

    private Boolean hasUser(UserDTO data){
        User user = repository.findUserByLogin(data.login());
        if (user == null){
            return false;
        }else{
            return true;
        }
    }

    private Boolean hasUser(LoginDTO data){
        User user = repository.findUserByLogin(data.login());
        if (user == null){
            return false;
        }else{
            return true;
        }
    }
        
        
        





    }
    
