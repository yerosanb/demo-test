package com.awash.project.controllers;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.awash.project.models.LoginResponse;
import com.awash.project.models.UserModel;
import com.awash.project.security.JwtUtils;
import com.awash.project.services.AuthUserDetailsService;
import com.awash.project.services.LDAPservice;
import com.awash.project.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.security.Key;
import java.security.SecureRandom;


@RestController
@RequestMapping("/auth/")
public class AuthController {

	 @Autowired
	    private AuthenticationManager authenticationManager;
	 @Autowired
	 private AuthUserDetailsService authserv;
	 @Autowired
	    private JwtUtils jwtUtils;
	
	 @Autowired	
	 private LDAPservice ldap;
	 
	    //private final String jwtSecret = generateSecureKey(); 
	    private final long jwtExpirationMs = 86400000L; // 1 day

	    @PostMapping("login")
	    public ResponseEntity<LoginResponse> login(@RequestBody UserModel loginRequest, HttpServletResponse response) {
	        try {
	          String email=null;
	          if(loginRequest.getEmail().contains("@")) {
	        	  email=loginRequest.getEmail().split("@")[0];
	          }
	          else {
	        	  email=loginRequest.getEmail();  
	          }
	          
	          	          
	          UserModel user=authserv.getUserFromLocal(email);
	          
	          if (user != null ) {
	          
	        	  List<Attributes> adUserDetails = ldap.getADUserDetails(email, loginRequest.getPassword());
	        	 
	        	 if (adUserDetails != null && !adUserDetails.isEmpty()) {
		        	  
	 	        	System.out.print(adUserDetails);
	 	        	
	 	        	
	 	          
	 	            String jwt = jwtUtils.generateTokenByEmail(user);
	 	            
	 	            String empId = jwtUtils.getEmpIdFromJwt(jwt);
	 	            String fullName = jwtUtils.getFullNameFromJwt(jwt);
	 	            System.out.print(empId);
	 	         
	 	         // Set JWT token as HttpOnly cookie
	 	            Cookie cookie = new Cookie("jwt", jwt);
	 	            cookie.setHttpOnly(false); // Make the cookie HttpOnly
	 	            cookie.setSecure(false); // Make sure the cookie is sent only over HTTPS
	 	            cookie.setPath("/"); // Available for all paths
	 	            cookie.setMaxAge(8600); // Set cookie expiration (1 day)
//	 	           cookie.setAttribute("SameSite", "Lax");
	 	           cookie.setPath("/");
	 	           
	 	            response.addCookie(cookie); // Add the cookie to the response

	 	            //return ResponseEntity.ok(new LoginResponse("Successful authentication", authentication.getName(), null));
	 	          
	 	            return ResponseEntity.ok(new LoginResponse("Successful authentication",loginRequest.getEmail(), jwt));
	 	        	 }
	 	        	 
	 	        	 else {
	 	                 // If AD authentication failed
	 	                 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid credentials", null, null));
	 	             }
	        	
	          }
	          else {
	        	  return ResponseEntity.status(HttpStatus.NOT_FOUND)
	        		        .body(new LoginResponse("No such user in local", loginRequest.getEmail(), null));

	        	  //return ResponseEntity.ok(new LoginResponse("no such user in local",loginRequest.getEmail(), null));  
	        	 
	          }
	        
	          /* for local authentication
	        	Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
	            );

	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            String jwt = jwtUtils.generateToken(authentication);
	            */
	        
	        } 
	        
	        catch (BadCredentialsException e) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Invalid credentials", null, null));
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponse("Login failed: " + e.getMessage(), null, null));
	        }
	    }

	   

	    
    
}
	

