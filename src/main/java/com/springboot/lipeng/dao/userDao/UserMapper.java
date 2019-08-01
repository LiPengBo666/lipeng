package com.springboot.lipeng.dao.userDao;

import com.springboot.lipeng.model.shiroRole.Permit;
import com.springboot.lipeng.model.shiroRole.Role;
import com.springboot.lipeng.model.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    //查询用户名是否存在
    User selectUsername(@Param("username") String username);

    //查询用户拥有的角色
    @Select("SELECT * FROM park_role r " +
            "INNER JOIN park_user_role ur   " +
            "on r.id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    List<Role> selectRolesByUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM park_permit p " +
            "INNER JOIN park_role_permit rp " +
            "on p.id = rp.permit_id " +
            "WHERE rp.role_id = #{roleId}")
    List<Permit> selectPermitByRoleId(@Param("roleId") Integer roleId);

    @Select("SELECT * FROM park_permit p " +
            "INNER JOIN park_role_permit rp " +
            "on p.id = rp.permit_id ")
    List<Permit> selectPermitBy();
}
