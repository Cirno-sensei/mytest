package com.ssm.service;

import com.github.pagehelper.PageInfo;
import com.ssm.damain.Permission;

import java.util.List;


public interface IPermissionService {
    PageInfo<Permission> findPermission(int pageNum,int pageSize);

    void addPermission(Permission permission);

    List<Permission> findPermissionById(int id);
}
