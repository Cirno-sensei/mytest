package com.ssm.service;

import com.github.pagehelper.PageInfo;
import com.ssm.damain.Permission;
import com.ssm.damain.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoleService {

    PageInfo<Role> findAllRole(int pageNum, int pageSize);

    void addRole(Role role);

    Role findRoleById(int id);

    void addPermissionToRole(int roleId,int permissionId);

    void deleteRolePermission(int id);
}
