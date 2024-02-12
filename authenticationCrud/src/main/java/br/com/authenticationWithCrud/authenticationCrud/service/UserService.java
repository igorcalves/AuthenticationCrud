package br.com.authenticationWithCrud.authenticationCrud.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.authenticationWithCrud.authenticationCrud.infra.securityConfiguration.TokenServie;
import br.com.authenticationWithCrud.authenticationCrud.model.LoginDTO;
import br.com.authenticationWithCrud.authenticationCrud.model.User;
import br.com.authenticationWithCrud.authenticationCrud.model.UserDTO;
import br.com.authenticationWithCrud.authenticationCrud.model.UserDTODataBase;
import br.com.authenticationWithCrud.authenticationCrud.model.UserUpdateDTO;
import br.com.authenticationWithCrud.authenticationCrud.model.changepasswordDTO;
import br.com.authenticationWithCrud.authenticationCrud.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenServie service;


     public List<UserDTODataBase> findAllUsers(){
         List<UserDTODataBase> users = repository.findAllUsers();
         return users;
        }
        
    public User createUser(UserDTO data){
        if(!hasUser(data)){
        	String encryptPassword = new BCryptPasswordEncoder().encode(data.password());
            User user =  new User(data.login(),encryptPassword,data.role(),data.name(),data.address(),data.secretQuestion(),LocalDateTime.now());
            repository.save(user);
            return user;
        }
        else{
            return null;
        }

 
    }

  
    public User updateUser(UserUpdateDTO data, String tokenData){
        User recoverUser = recoverUser(tokenData);
        String a = recoverUser.getRole().getRole();
        if(hasUser(data) && recoverUser != null){
            if(recoverUser.getRole().getRole() == "admin"){
                User user = repository.findUserByLogin(data.getLogin());
                user.setPassword(data.getPassword());
                user.setName(data.getName());
                if(data.getRole() != null) user.setRole(data.getRole());
                if(data.getSecretQuestion() != null) user.setSecretQuestion(data.getSecretQuestion());
                user.setAddress(data.getAddress());
                repository.save(user);
                return user;
            }else if(recoverUser.getRole().getRole() == "user"){
                if(recoverUser.getLogin().equals(data.getLogin())){
                    User user = repository.findUserByLogin(data.getLogin());
                    user.setPassword(data.getPassword());
                    if(data.getSecretQuestion() != null) user.setSecretQuestion(data.getSecretQuestion());
                    user.setName(data.getName());
                    user.setAddress(data.getAddress());
                    repository.save(user);
                    return user;
                }else{
                    return null;
                }
            
            }
        
        }
            return null;
    
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

    private Boolean hasUser(UserUpdateDTO data){
        User user = repository.findUserByLogin(data.getLogin());
        if (user == null){
            return false;
        }else{
            return true;
        }
    }



    public Boolean changePassword(changepasswordDTO data){
        User user = repository.findUserByLogin(data.login());

        if(user != null && user.getSecretQuestion().equals(data.secretQuestion())){
            user.setPassword(data.newPassword());
            repository.save(user);
            return true;
        }
        return false;
    }

    private User recoverUser(String token){
        token = token.replace("Bearer ", "");
        String login = service.validateToken(token);
        User user = repository.findUserByLogin(login);
        return user;
    }

        
        
        





    }
    
