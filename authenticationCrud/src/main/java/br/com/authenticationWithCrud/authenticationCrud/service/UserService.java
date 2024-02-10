package br.com.authenticationWithCrud.authenticationCrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
            User user =  new User(data.login(),data.password());
            repository.save(user);
            return user;
        }
        else{
            return null;
        }

 
    }

    public User updateUser(UserDTO data){
        if(hasUser(data)){
            User user = repository.findByLogin(data.login());
            user.setLogin(data.login());
            user.setPassword(data.password());
            repository.save(user);
            return user;
        }{
            return null;
        }
    
    }

    public Boolean deleteUser(LoginDTO data){
        
        if(hasUser(data)){
            User user = repository.findByLogin(data.login());
            repository.delete(user);
            return true;
        }
        return false;
    }

    private Boolean hasUser(UserDTO data){
        User user = repository.findByLogin(data.login());
        if (user == null){
            return false;
        }else{
            return true;
        }
    }

    private Boolean hasUser(LoginDTO data){
        User user = repository.findByLogin(data.login());
        if (user == null){
            return false;
        }else{
            return true;
        }
    }
        
        
        





    }
    
