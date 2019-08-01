package com.springboot.lipeng.controller.userLogin;

import com.alibaba.fastjson.JSONObject;
import com.springboot.lipeng.controller.TestOne;
import com.springboot.lipeng.model.user.User;
import com.springboot.lipeng.vo.Result;
import com.springboot.lipeng.vo.redisUtils.RedisConfig;
import com.springboot.lipeng.vo.redisUtils.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisShardInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserLogin {

    private static final Logger logger = LoggerFactory.getLogger(TestOne.class);
    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/login.do")
    @ResponseBody
    public Result userLogin(HttpServletRequest request) {
        Result result = Result.getInstance();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + "--" + password);

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        //用户认证开始
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        //进行认证，这里可以捕获异常，然后返回对应信息
        User user=null;
        try {
            subject.login(usernamePasswordToken);
            user = (User) subject.getPrincipal();
            session.setAttribute("user", user);
            System.out.println(user);
//        String sessionId = (String) session.getId();
            result.setMessage(user.toString());
            logger.info("用户登录验证成功");
        }catch (Exception e){
            logger.info("用户登录验证失败");
        }
//        if (session.getAttribute("MEMBER_USER_KEY") == null) {
//            logger.info("用户没有登录,即将跳转登录页面");
//        } else {
//            logger.info("用户已经登录,即将跳转登录页面");
//        }
        return result;
    }

    @RequestMapping("/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        String sessionId = (String) session.getId();
        logger.info("sessionId{}", sessionId);
        JedisShardInfo shardInfo = new JedisShardInfo("redis://192.168.1.234:6379");
        shardInfo.setPassword("123456");
        RedisUtils redisUtils = redisConfig.redisUtil(redisTemplate);
        System.out.println("----------------------sessionId:" + sessionId);
        redisUtils.del(sessionId);
//        Jedis jedis = new Jedis(shardInfo);
//        long jedis_key = jedis.del("shiro:session:"+sessionId);
//        logger.info("jedis_key{}",jedis_key);
        logger.info("--------数据已经删除--------");
    }
}
