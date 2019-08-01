package com.springboot.lipeng.service.userService;


import com.springboot.lipeng.model.shiroRole.Permit;
import com.springboot.lipeng.model.shiroRole.Role;
import com.springboot.lipeng.model.user.User;

import java.util.List;

public interface UserService {
    //查询用户名是否存在
    User selectUsername(String username);
    //查询用户拥有的角色
    List<Role> selectRolesByUserId(Integer id);
    //查询用户拥有的权限
    List<Permit> selectPermitByRoleId(Integer id);
}
