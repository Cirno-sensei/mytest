package com.ssm.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.damain.Role;
import com.ssm.damain.SysUser;
import com.ssm.dao.IUserDao;
import com.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//实现UserDetailsService接口,运行时,登录时会有SpringSecurity自动调用
@Service("userService")//要与SpringSecurity配置引用名一样
public class UserServiceImp implements IUserService {
    @Autowired
    private IUserDao iUserDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = iUserDao.findUserByUsername(username);
        List<Role> roles = sysUser.getRoles();
        /**
         *   GrantedAuthority为该User所被赋予的权限
         *   Granted 授予
         *   Authority 权限 权利
         *    List<Role> roles 角色集合
         */
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (roles != null && roles.size() > 0){
            for (Role role : roles) {
                //给该用户赋予role角色,即权限的集合
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }
        UserDetails user = new User(username,sysUser.getPassword(),authorities);
        return user;
    }

    @Override
    public PageInfo<SysUser> findUserByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> Users = iUserDao.findAllUser();
        return new PageInfo<SysUser>(Users);
    }

    @Override
    public void addUser(SysUser sysUser) {
        String encode = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(encode);
        iUserDao.addUser(sysUser);
    }

    @Override
    public PageInfo<SysUser> findUserById(int id,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> userById = iUserDao.findUserById(id);
        return new PageInfo<SysUser>(userById);
    }

    @Override
    public SysUser findUser(int id) {
        List<SysUser> userById = iUserDao.findUserById(id);
        SysUser user = null;
        for (SysUser sysUser : userById) {
            user = sysUser;
        }
        return user;
    }

    @Override
    public void addRoleToUser(int userId, int roleId) {
        iUserDao.addRoleToUser(userId,roleId);
    }

    @Override
    public void deleteUserRole(int id) {
        iUserDao.deleteUserRole(id);
    }


}
