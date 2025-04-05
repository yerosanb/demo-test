package com.awash.project.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.awash.project.models.RoleModel;
import com.awash.project.models.UserModel;
import org.apache.ibatis.annotations.Results;

@Mapper
public interface UserMapper {
@Insert("insert into users(empId,email,userName,firstName,secondName,unit,password) values(#{empId},#{email},#{userName},#{firstName},#{secondName},#{unit},#{password})")
@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void registerUser(UserModel user);

@Insert("insert into user_roles(user_id,role_id) values(#{userId},#{roleId})")
void saveUserRoles(@Param("userId") Long userId, @Param("roleId") Long roleId);

@Delete("delete from user_roles where user_id=#{id}")
void deleteExistingRoles(Long id);

@Select("select * from users")
List<UserModel> getAllUsers();
@Update("update users set empId=#{empId},email=#{email},userName=#{userName},firstName=#{firstName},secondName=#{secondName},unit=#{unit},password=#{password} where id=#{id} ")
void updateUser(UserModel user);
@Select("select * from users where empId like concat('%',#{keys},'%') or email like concat('%',#{keys},'%') or firstName like concat('%',#{keys},'%') ")
@Results(value = {		
		 @Result(property="roles", javaType=List.class, column = "id", 
       	many=@Many(select="getRolesByUserId"))
   })
List<UserModel> searchUsersKeys(String keys);

@Select("select count(*) from users")
int countUsers();

@Select("SELECT * FROM users ORDER BY empId OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY; ")
@Results(value = {		
		 @Result(property="roles", javaType=List.class, column = "id", 
        	many=@Many(select="getRolesByUserId"))
    })
List<UserModel> fetchAllUsers(@Param("limit") int limit, @Param("offset") int offset);

@Select("SELECT r.* FROM roles r " +
        "JOIN user_roles ur ON r.id = ur.role_id " +
        "WHERE ur.user_id = #{userId}")
List<RoleModel> getRolesByUserId(Long userId);

@Select("select * from roles where status=0")
List<RoleModel> roles();









}
