package com.ssm.web;

import com.github.pagehelper.PageInfo;
import com.ssm.damain.Permission;
import com.ssm.damain.Role;
import com.ssm.damain.SysUser;
import com.ssm.service.IPermissionService;
import com.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RolesAllowed("ROLE_Admin")
@RequestMapping("/role")
public class RoleWeb {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findRole")
    public ModelAndView findRoleByPage(@RequestParam(defaultValue = "1") int pageNum,
                                       @RequestParam(defaultValue = "2") int pageSize){
        PageInfo<Role> pageInfo = roleService.findAllRole(pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("/addRole")
    public String addUser(Role role){
        roleService.addRole(role);
        return "redirect:/role/findRole";
    }

    @RequestMapping("/showPermissionList")
    public ModelAndView showPermissionByPage(@RequestParam (defaultValue = "1") int pageNum,
                                             @RequestParam(defaultValue = "2") int pageSize,
                                             int id){
        Role role = roleService.findRoleById(id);
        List<Permission> permissions = permissionService.findPermissionById(id);
        StringBuilder sb = new StringBuilder();
        String permissionStr = "";
        if (permissions != null && permissions.size() > 0){
            for (Permission permission : permissions) {
                sb.append(permission.getPermissionName()+",");
            }
            permissionStr = sb.substring(0,sb.length() - 1);
        }
        PageInfo<Permission> pageInfo = permissionService.findPermission(pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("role",role);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.addObject("permissionStr",permissionStr);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    @RequestMapping("/updateRolePermission")
    public String updateRolePermission(int id,String ids){
        roleService.deleteRolePermission(id);
        System.out.println(ids);
        if (ids != null && !"".equals(ids)){
            String[] idList = ids.split(",");
            for (String s : idList) {
                roleService.addPermissionToRole(id, Integer.parseInt(s));
            }
        }
        return "redirect:/role/showPermissionList?id="+id;
    }
}
