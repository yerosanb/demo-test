package com.awash.project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


import com.awash.project.models.AuthUserDetail;
import com.awash.project.models.RoleModel;
import com.awash.project.models.UserModel;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.spec.SecretKeySpec;

import java.security.Key;

@Component
public class JwtUtils {
	
	
	
	/*private final SecretKey secretKey; // Declare the secret key at the class level
	public JwtUtils() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Initialize the secret key
    }*/
	
	   @Value("${jwt.secret}")
	    private String secretKey;
	   @Value("${jwt.jwtExpiration}")
    private  String jwtExpirationMs; //= 86400000; // 1 day in milliseconds

    //Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());

    public String generateToken(Authentication authentication) {
    	AuthUserDetail userDetails = (AuthUserDetail) authentication.getPrincipal();
        String username = authentication.getName();
        String empId = userDetails.getEmpId();
        String fullName = userDetails.getFullName();
       // String username = userDetails.getUsername();
        
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
       
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
                        
        List<RoleModel> rolesx = userDetails.getRoles(); 
      
        Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
        
        return Jwts.builder()
                .setSubject(username)
                .claim("empId", empId)
                .claim("fullName", fullName)
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key) // Sign with HS512
                .compact();
    }
    
public String generateTokenByEmail(UserModel usr) {
	 Date now = new Date();
     Date expiryDate = new Date(now.getTime() + Long.parseLong(jwtExpirationMs));
     
	//UserModel user = loginMap.findByUsername(email);
		 //List<RoleModel> roles = loginMap.findRolesByUserId(user.getId());
     
     /*List<SimpleGrantedAuthority> authorities = roles.stream()
	    .map(role -> new SimpleGrantedAuthority(role.getName()))
	    .collect(Collectors.toList());*/
		 
     List<SimpleGrantedAuthority> authorities = usr.getRoles().stream()
	    .map(role -> new SimpleGrantedAuthority(role.getName()))
	    .collect(Collectors.toList());
     
	
	 
	 List<String> roless = authorities.stream()
             .map(GrantedAuthority::getAuthority)
             .collect(Collectors.toList());
	 
	 System.out.print("dddddd-"+authorities);
	 System.out.print(roless);
	 
	 Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
     
     return Jwts.builder()
             .setSubject(usr.getEmail())
             .claim("empId", usr.getEmpId())
             .claim("user",usr.getEmail())
             .claim("fullName", usr.getFirstName())
             .claim("roles", roless)
             .setIssuedAt(now)
             .setExpiration(expiryDate)
             .signWith(key) // Sign with HS512
             .compact();
	 
		
	}

    public boolean validateToken(String token) {
        try {
            Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());

            // Create a JwtParser using JwtParserBuilder
            Jwts.parserBuilder() // Use parserBuilder for 0.11.x
                    .setSigningKey(key) // Set the signing key
                    .build()
                    .parseClaimsJws(token); // This will throw an exception if the token is invalid

            return true; // If no exception is thrown, the token is valid
        } catch (Exception e) {
            // Log the error message here (you might want to use a logging framework)
            System.err.println("Invalid JWT token: " + e.getMessage());
            return false;
        }
    }

    public String getUsernameFromJwt(String token) {
        Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());

        Claims claims = Jwts.parserBuilder() // Use parserBuilder for 0.11.x
                .setSigningKey(key) // Ensure the signing key is used
                .build()
                .parseClaimsJws(token) // Parse the token
                .getBody(); // Get the claims body

        return claims.getSubject(); // Return the username (subject)
    }
    
    public String getEmpIdFromJwt(String token) {
        Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("empId", String.class);  // Extract empId from custom claim
    }

    public String getFullNameFromJwt(String token) {
        Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("fullName", String.class);  // Extract fullName from custom claim
    }
    
    private boolean isTokenExpired(String token) {
        Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());

        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

	
    
}