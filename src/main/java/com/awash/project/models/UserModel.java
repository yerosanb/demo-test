package com.awash.project.models;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

	private Long Id;
	private String empId;
	private String email;
	private String userName;
	
	private String firstName;
	private String secondName;
	private String unit;
	private String name;
	

	private String password;
	private boolean isActive;
	private Long status;
	
	private List<RoleModel> roles;
	
	
	public List<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}
	public UserModel() {
		super();
	}
	
	public UserModel(Long id, String empId, String email, String userName, List<RoleModel> roles, String firstName,
			String secondName, String unit, String password, boolean isActive, Long status,String name) {
		super();
		Id = id;
		this.empId = empId;
		this.email = email;
		this.userName = userName;
		this.roles = roles;
		this.firstName = firstName;
		this.secondName = secondName;
		this.unit = unit;
		this.password = password;
		this.isActive = isActive;
		this.status = status;
		this.name=name;
	}
	
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
