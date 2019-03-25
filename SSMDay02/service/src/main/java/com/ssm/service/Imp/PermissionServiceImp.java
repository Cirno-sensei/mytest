package com.ssm.service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.damain.Permission;
import com.ssm.dao.IPermissionDao;
import com.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImp implements IPermissionService {
    @Autowired
    private IPermissionDao iPermissionDao;
    @Override
    public PageInfo<Permission> findPermission(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Permission> permission = iPermissionDao.findPermission();
        return new PageInfo<Permission>(permission);
    }

    @Override
    public void addPermission(Permission permission) {
        iPermissionDao.addPermission(permission);
    }

    @Override
    public List<Permission> findPermissionById(int id) {
        return iPermissionDao.findPermissionsById(id);
    }
}
