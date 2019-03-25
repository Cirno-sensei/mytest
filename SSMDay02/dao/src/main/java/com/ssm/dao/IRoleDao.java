package com.ssm.dao;

import com.ssm.damain.Role;
import com.ssm.damain.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IRoleDao {
    @Select("SELECT * FROM sys_role")
    List<Role> findAllRole();

    @Insert("INSERT INTO sys_role VALUES (NULL ,#{roleName},#{roleDesc})")
    void addRole(Role role);

    @Select("SELECT r.* FROM sys_user_role ur INNER JOIN sys_user u on u.`id` = ur.`userId` 	Inner join sys_role r on r.`id` = ur.`roleId`" +
            "WHERE ur.userId = #{userId}")
    @Results({
            @Result(id = true,property = "id" , column = "id"),
            @Result(property = "permission" , column = "id" , javaType = List.class ,
            many = @Many(select = "com.ssm.dao.IPermissionDao.findPermissionsById" , fetchType = FetchType.LAZY))
    })
    List<Role> findRolesById(int userId);

    @Select("SELECT *  FROM sys_role WHERE id = #{id}")
    Role findRoleById(int id);

    @Insert("INSERT INTO sys_role_permission VALUES (#{arg1},#{arg0})")
    void addPermissionToRole(int roleId,int permissionId);

    @Delete("DELETE FROM sys_role_permission WHERE roleId = #{id}")
    void deleteRolePermission(int id);
}
