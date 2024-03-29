package br.com.authenticationWithCrud.authenticationCrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.authenticationWithCrud.authenticationCrud.model.User;
import br.com.authenticationWithCrud.authenticationCrud.model.UserDTODataBase;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	
	
	UserDetails findByLogin(String login);
	User findUserByLogin(String login);
    
   

    @Query(value = "SELECT new br.com.authenticationWithCrud.authenticationCrud.model.UserDTODataBase(u.id, u.login,u.name,u.address,u.role) FROM User u")
    List<UserDTODataBase> findAllUsers();


    
}
