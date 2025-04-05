package com.awash.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.awash.project.mappers.AuthMapper;
import com.awash.project.models.AuthUserDetail;
import com.awash.project.models.RoleModel;
import com.awash.project.models.UserModel;

@Service
public class AuthUserDetailsService implements UserDetailsService{

	@Autowired
    private AuthMapper loginMap;
	
	@Override //UserDetails
	public AuthUserDetail loadUserByUsername(String email) throws UsernameNotFoundException {
		UserModel user = loginMap.findByUsername(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<RoleModel> roles = loginMap.findRolesByUserId(user.getId());
        if (roles != null && !roles.isEmpty()) {
            user.setRoles(roles);  
        }
        return new AuthUserDetail(user);
				
	}
	
	
	public UserModel getUserFromLocal(String email) {
		UserModel user = loginMap.findByUsername(email);
		 if (user != null) {
			 List<RoleModel> roles = loginMap.findRolesByUserId(user.getId()); 
			 user.setRoles(roles);  
		 }
		return user;
		
	}


}
