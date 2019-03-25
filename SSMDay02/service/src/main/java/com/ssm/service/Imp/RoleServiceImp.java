package com.ssm.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.damain.Permission;
import com.ssm.damain.Role;
import com.ssm.dao.IPermissionDao;
import com.ssm.dao.IRoleDao;
import com.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements IRoleService {
    @Autowired
    private IPermissionDao iPermissionDao;
    @Autowired
    private IRoleDao iRoleDao;
    @Override
    public PageInfo<Role> findAllRole(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> roleList = iRoleDao.findAllRole();
        System.out.println(roleList);
        return new PageInfo<Role>(roleList);
    }

    @Override
    public void addRole(Role role) {
        iRoleDao.addRole(role);
    }

    @Override
    public Role findRoleById(int id) {
        return iRoleDao.findRoleById(id);
    }

    @Override
    public void addPermissionToRole(int roleId, int permissionId) {
        iRoleDao.addPermissionToRole(roleId,permissionId);
    }

    @Override
    public void deleteRolePermission(int id) {
        iRoleDao.deleteRolePermission(id);
    }


}
