package com.awash.project.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.awash.project.models.RoleModel;
import com.awash.project.models.UserModel;

@Mapper
public interface AuthMapper {
	@Select("SELECT * FROM users WHERE email = #{username} or userName=#{username} or empId=#{username}")
	UserModel findByUsername(String username);
	@Select("SELECT r.* FROM roles r " +
            "JOIN user_roles ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
	List<RoleModel> findRolesByUserId(Long userId);
	
	
	@Select("SELECT * FROM users WHERE isActive = 1 AND status = 0 AND email = #{email}")
	UserModel getUserFromLocal(String email);
    List<RoleModel> getRolesByUserId( int userId);
}
