package br.com.authenticationWithCrud.authenticationCrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.authenticationWithCrud.authenticationCrud.model.AuthenticationDTO;
import br.com.authenticationWithCrud.authenticationCrud.model.User;
import br.com.authenticationWithCrud.authenticationCrud.model.UserDTO;
import br.com.authenticationWithCrud.authenticationCrud.service.UserService;
import jakarta.validation.Valid;


@RestController()
@RequestMapping("auth")
public class AuthorizationController {
	
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService service;
	
	 @PostMapping("/login")
		public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
			var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

			var auth = authenticationManager.authenticate(userNamePassword);
			
			
			return ResponseEntity.ok().body("done");
			
		}		
	 
	   @PostMapping("/register")
	    public ResponseEntity<?> register(@RequestBody @Valid UserDTO data){
	        User user = service.createUser(data);
	        if(user == null){
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
	        }else{
	            return ResponseEntity.status(HttpStatus.CREATED).body(user);
	        }
	    }


}
