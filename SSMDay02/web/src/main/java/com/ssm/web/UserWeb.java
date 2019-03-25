package com.ssm.web;

import com.github.pagehelper.PageInfo;
import com.ssm.damain.Role;
import com.ssm.damain.SysUser;
import com.ssm.service.IRoleService;
import com.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RolesAllowed("ROLE_Admin")
@RequestMapping("/user")
public class UserWeb {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findUser")
    public ModelAndView findUserByPage(@RequestParam(defaultValue = "1") int pageNum,
                                       @RequestParam(defaultValue = "2") int pageSize){
        PageInfo<SysUser> userByPage = userService.findUserByPage(pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",userByPage);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/addUser")
    public String addUser(SysUser sysUser){
        userService.addUser(sysUser);
        return "redirect:/user/findUser";
    }

    @RequestMapping("/UserInfo")
    public ModelAndView findUserInfo(@RequestParam(defaultValue = "1") int pageNum,
                                     @RequestParam(defaultValue = "2") int pageSize,
                                     int userId){
        PageInfo<SysUser> user = userService.findUserById(userId, pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo" , user);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    @RequestMapping("/showRoleList")
    public ModelAndView showRoleList(@RequestParam(defaultValue = "1") int pageNum,
                                     @RequestParam(defaultValue = "2") int pageSize,
                                     int id){
        SysUser user = userService.findUser(id);
        List<Role> roles = user.getRoles();
        System.out.println(roles);
        StringBuilder stringBuilder = new StringBuilder();
        String rolesStr = "";
       if (roles != null && roles.size() > 0){
           for (Role role : roles) {
                 stringBuilder.append(role.getRoleName() + ",");
           }
           rolesStr = stringBuilder.substring(0,stringBuilder.length() - 1);
       }
        System.out.println(rolesStr);
        PageInfo<Role> pageInfo = roleService.findAllRole(pageNum, pageSize);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.addObject("user",user);
        modelAndView.addObject("rolesStr",rolesStr);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    @RequestMapping("/updateUserRole")
    public String updateUserRole(int id,String ids){
        userService.deleteUserRole(id);
        System.out.println(ids);
        if (ids != null && !"".equals(ids)){
            String[] idList = ids.split(",");
            for (String s : idList) {
                userService.addRoleToUser(id, Integer.parseInt(s));
            }
        }
        return "redirect:/user/showRoleList?id="+id;
    }


}
