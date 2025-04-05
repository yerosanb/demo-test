package com.awash.project.models;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthUserDetail implements UserDetails {
	private final UserModel user;
	public AuthUserDetail(UserModel user) {
        this.user = user;
    }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		 return user.getRoles().stream()
	               .map(role -> new SimpleGrantedAuthority(role.getName()))
	               .collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return  user.getPassword(); 
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}
	@Override
    public boolean isEnabled() {
        return user.getIsActive();
    }
	
	public String getEmpId() {
        return user.getEmpId();  // Assuming getEmpId() exists in your UserModel
    }

    public String getFullName() {
        return user.getFirstName();  // Assuming getFullName() exists in your UserModel
    }
    
    public List<RoleModel> getRoles() {
        return user.getRoles();
    }
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString(); // Fallback to default toString() if JSON fails
        }
    }

}
