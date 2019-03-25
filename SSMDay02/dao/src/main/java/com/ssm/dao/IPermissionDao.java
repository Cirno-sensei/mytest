package com.ssm.dao;

import com.ssm.damain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
    @Select("SELECT * FROM sys_permission")
    List<Permission> findPermission();

    @Insert("INSERT INTO sys_permission VALUES (NULL,#{permissionName},#{url},#{pid})")
    void addPermission(Permission permission);

    @Select("SELECT p.* FROM sys_role_permission rp INNER JOIN sys_permission p on p.`id` = rp.`permissionId` Inner join sys_role r on r.`id` = rp.`roleId`" +
            "WHERE rp.roleId = #{roleId}")
    List<Permission> findPermissionsById(int roleId);
}
