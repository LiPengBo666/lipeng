package com.springboot.lipeng.serviceImpl.userServiceImpl;

import com.springboot.lipeng.dao.userDao.UserMapper;

import com.springboot.lipeng.model.shiroRole.Permit;
import com.springboot.lipeng.model.shiroRole.Role;
import com.springboot.lipeng.model.user.User;
import com.springboot.lipeng.service.userService.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    //查询用户名是否存在
    @Override
    public User selectUsername(String username) {
        User user = userMapper.selectUsername(username);
        return user;
    }
    //查询用户拥有的角色
    @Override
    public List<Role> selectRolesByUserId(Integer userId) {
        return userMapper.selectRolesByUserId(userId);
    }
    //查询用户拥有的权限
    @Override
    public List<Permit> selectPermitByRoleId(Integer roleId) {
        if (roleId == 0){
            return userMapper .selectPermitBy();
        }
        return userMapper.selectPermitByRoleId(roleId);
    }
}
