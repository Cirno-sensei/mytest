package com.ssm.web;

import com.github.pagehelper.PageInfo;
import com.ssm.damain.Permission;
import com.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;

@Controller
@RolesAllowed("ROLE_Admin")
@RequestMapping("/permission")
public class PermissionWeb {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findPermission")
    public ModelAndView findPermissionByPage(@RequestParam (defaultValue = "1") int pageNum,
                                             @RequestParam(defaultValue = "2") int pageSize){
        PageInfo<Permission> pageInfo = permissionService.findPermission(pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    @RequestMapping("/addPermission")
    public String addPermission(Permission permission){
        permissionService.addPermission(permission);
        return "redirect:/permission/findPermission";
    }
}
