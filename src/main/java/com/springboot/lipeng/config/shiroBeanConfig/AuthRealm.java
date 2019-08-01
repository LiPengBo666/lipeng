package com.springboot.lipeng.config.shiroBeanConfig;

import com.springboot.lipeng.model.shiroRole.Permit;
import com.springboot.lipeng.model.shiroRole.Role;
import com.springboot.lipeng.model.user.User;
import com.springboot.lipeng.service.userService.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
* @Description:    Shiro :Reaml 用户认证和权限认证
* @Author:         LiPeng
* @CreateDate:     2019/8/1 15:19
* @UpdateUser:     LiPeng
* @UpdateDate:     2019/8/1 15:19
* @UpdateRemark:   暂无
* @Version:        1.0
*/
public class AuthRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(AuthRealm.class);

    @Autowired
    private UserService userService;
    /**
     * 用户信息认证是在用户进行登录的时候进行验证(不存redis)
     *
     * @param token 用户登录的账号密码信息
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("===============Shiro用户认证开始");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //获取登录用户名
        String username = String.valueOf(usernamePasswordToken.getUsername());
        User user = userService.selectUsername(username);
        AuthenticationInfo authenticationInfo = null;
        //如果有此用户名的用户,则判断输入的密码和数据库中的密码是否一致
        if (user != null) {
            String password = new String(usernamePasswordToken.getPassword());
            if (password.equals(user.getPassword())) {
                authenticationInfo = new SimpleAuthenticationInfo(
                        user, //用户实体对象,不能只是用户名,因为redis中针对不同用户缓存使用的是id,这里赋值用户名的话则会找不到id
                        user.getPassword(), //密码
                        getName() //realm name
                );
                log.info("===============Shiro用户认证成功");
            }
        }
        return authenticationInfo;
    }

    /**
     * 权限信息认证是用户访问controller的时候才进行验证(redis存储的也是权限信息)
     *
     * @param principals 身份信息
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("===============Shiro权限认证开始");
        //获取身份认证时设置的用户名(SimpleAuthenticationInfo)
        User user = (User) principals.getPrimaryPrincipal();
        String username = user.getUsername();
        if (username != null && !("".equals(username))) {
            int userId = user.getId();
            //通过用户名获取该用户所属角色名称
            //通过用户名获取该用户所拥有权限的名称
            //设置用户角色和权限
            List<Role> roles = userService.selectRolesByUserId(userId);
            Set<String> webRole_set=new HashSet<>();
            SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
            for (Role role:roles) {
                webRole_set.add(role.getRoleName());
                List<Permit> permitsList = userService.selectPermitByRoleId(role.getId());
                for (Permit permit:permitsList) {
                    authenticationInfo.addStringPermission(permit.getPermitName());
                }
            }
            authenticationInfo.setRoles(webRole_set);
            log.info("===============Shiro权限认证成功");
            return authenticationInfo;
        }
        return null;
    }

    /**
     * 清除当前用户的权限认证缓存
     *
     * @param principals 权限信息
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
}

