package com.awash.project.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.awash.project.mappers.UserMapper;
import com.awash.project.models.RoleModel;
import com.awash.project.models.UserModel;

@Service
public class UserService {

	@Autowired
	private UserMapper map;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void registerUser(UserModel user) {
		if (user.getId() == 0) {
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			map.registerUser(user);
			for (RoleModel r : user.getRoles()) {
				map.saveUserRoles(user.getId(), r.getId());
			}

		} else {
			map.updateUser(user);
			map.deleteExistingRoles(user.getId());
			for (RoleModel r : user.getRoles()) {

				map.saveUserRoles(user.getId(), r.getId());
			}
		}

	}

	public List<UserModel> getAllUsers() {

		return map.getAllUsers();
	}

	public List<UserModel> searchUsers(String query) {

		return null;
	}

	public List<UserModel> searchUsersKeys(String keys) {

		return map.searchUsersKeys(keys);
	}

	public Page<UserModel> fetchAllUsers(Pageable pageable) {
		int limit = pageable.getPageSize();
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		List<UserModel> u = map.fetchAllUsers(limit, offset);
		int totalUsers = map.countUsers();
		return new PageImpl<>(u, pageable, totalUsers);
	}

	public List<RoleModel> roles() {

		return map.roles();
	}

	
}
