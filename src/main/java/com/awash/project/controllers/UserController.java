package com.awash.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.awash.project.models.RoleModel;
import com.awash.project.models.UserModel;
import com.awash.project.security.JwtUtils;
import com.awash.project.services.UserService;

@RestController
@RequestMapping("/users/")
public class UserController {


	@Autowired
	private UserService serv;
	
	@PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserModel user) {
		
	
		 try {
			
			 //System.out.print(user);
		serv.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
		 } 
		 catch (DataIntegrityViolationException e) {
	          
	            return ResponseEntity.status(HttpStatus.CONFLICT)
	                                 .body("Record already exists.");
	        }
		 catch (Exception e) {
		        
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                             .body("Error in registration: " + e.getMessage());
		    }
    }
	
	 @GetMapping("getAllUsers") 
	    public ResponseEntity<List<UserModel>> getAllUsers() {
	        try {
	        	//Pageable pageable = PageRequest.of(page - 1, size); 
	            List<UserModel> users = serv.getAllUsers(); // Fetch the users
	            return ResponseEntity.ok(users); // Return the users in the response
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body(null); // Handle the error accordingly
	        }
	    }
	 
	 @GetMapping("fetchAllUsers")
	    public ResponseEntity<Page<UserModel>> fetchAllUsers(
	        @RequestParam("page") int page,
	        @RequestParam("size") int size) {
	        
	        try {
	            Pageable pageable = PageRequest.of(page - 1, size); // Adjust for zero-indexing
	            Page<UserModel> usersPage = serv.fetchAllUsers(pageable);
	            return ResponseEntity.ok(usersPage);  
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	 
	 @GetMapping("searchUsers") 
	    public ResponseEntity<List<UserModel>> searchUsers(@RequestParam String query) {
	        try {
	            List<UserModel> users = serv.searchUsersKeys(query); 
	            return ResponseEntity.ok(users); 
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body(null); // Handle the error accordingly
	        }
	    }
	
	 @GetMapping("roles") 
	    public ResponseEntity<List<RoleModel>> roles() {
	        try {
	        	
	            List<RoleModel> roles = serv.roles(); // Fetch the users
	            return ResponseEntity.ok(roles); // Return the users in the response
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body(null); // Handle the error accordingly
	        }
	    }
	 

}
