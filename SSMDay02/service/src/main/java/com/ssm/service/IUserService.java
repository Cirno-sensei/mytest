package com.ssm.service;

import com.github.pagehelper.PageInfo;
import com.ssm.damain.Role;
import com.ssm.damain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;
//继承UserDetailsService(身份认证)
public interface IUserService extends UserDetailsService {

    PageInfo<SysUser> findUserByPage(int pageNum , int pageSize);

    void addUser(SysUser sysUser);

    PageInfo<SysUser> findUserById(int id,int pageNum, int pageSize);

    SysUser findUser(int id);

    void addRoleToUser(int userId,int roleId);

    void deleteUserRole(int id);
}
