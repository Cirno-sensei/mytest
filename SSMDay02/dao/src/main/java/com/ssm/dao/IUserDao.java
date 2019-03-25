package com.ssm.dao;

import com.ssm.damain.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


public interface IUserDao {
    @Select("select * from sys_user where username=#{username}")
    @Results({
            @Result(id = true,property = "id", column = "id"),
            @Result(property = "roles" , column = "id" ,javaType = List.class,
                    many = @Many(select = "com.ssm.dao.IRoleDao.findRolesById",fetchType = FetchType.LAZY))
    })
    SysUser findUserByUsername(String username);

    @Select("SELECT  * FROM sys_user")
    List<SysUser> findAllUser();

    @Insert("INSERT INTO sys_user VALUES (NULL ,#{username},#{email},#{password},#{phoneNum},#{status})")
    void addUser(SysUser sysUser);

    @Select("SELECT * FROM sys_user WHERE id = #{id}")
    @Results({
            @Result(id = true,property = "id", column = "id"),
            @Result(property = "roles" , column = "id" ,javaType = List.class,
            many = @Many(select = "com.ssm.dao.IRoleDao.findRolesById",fetchType = FetchType.LAZY))
    })
    List<SysUser> findUserById(int id);

    @Insert("INSERT INTO sys_user_role VALUES (#{arg0},#{arg1})")
    void addRoleToUser(int userId,int roleId);

    @Delete("DELETE FROM sys_user_role WHERE userId = #{id}")
    void deleteUserRole(int id);

}
