package com.springboot.lipeng.controller;

import com.springboot.lipeng.model.ParkUser;
import com.springboot.lipeng.model.User;
import com.springboot.lipeng.service.TestOneService;
import com.springboot.lipeng.vo.Result;
import com.springboot.lipeng.vo.redisUtils.RedisConfig;
import com.springboot.lipeng.vo.redisUtils.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestOne {

    private static final Logger logger = LoggerFactory.getLogger(TestOne.class);

    @Resource
    private TestOneService testOneService;

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/One.do")
    @ResponseBody
    public Result<List<User>> testOne() {
        User user = new User();
        List<User> users = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            user.setName("lipeng");
            user.setAge("24");
            user.setMessage("新世界,新起点");
            users.add(user);
        }
        System.out.println("进来了");
        Result result = Result.getInstance();
        result.setData(users);
        result.setStatus("1");
        return result;
    }

    /**
     * controller跳转页面
     */
    @RequestMapping("/html.do")
    public String testTwo() {
        return "/TestOne.html";
    }

    @RequestMapping("parkRole")
    @ResponseBody
    public Result<List<ParkUser>> testThree() {
        logger.info("日志记录");
        logger.error("空指针");
        logger.debug("调试");
        List<ParkUser> list = testOneService.selectAll();
        Result result = Result.getInstance();
        result.setData(list);
        //将List集合持久化到Redis中
        RedisUtils redisUtils = redisConfig.redisUtil(redisTemplate);
        redisUtils.lSet("demoList", list);
        return result;
    }

    /**
     * Redis持久化
     */
    @RequestMapping("/redisText.do")
    @ResponseBody
    public String redisTest() {
        RedisUtils redisUtils = redisConfig.redisUtil(redisTemplate);
        redisUtils.set("demo", "Hello New Word");
        return "OJBK";
    }

    @RequestMapping(value = "/test.do")
    @ResponseBody
    public Result index() {
        Result result = Result.getInstance();
        //获取shiro中的session
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        System.out.println(user);
        Session session = SecurityUtils.getSubject().getSession();
        if (session.getAttribute("MEMBER_USER_KEY") == null) {
            logger.info("用户没有登录,即将跳转登录页面");
        } else {
            logger.info("用户已经登录,即将跳转登录页面");
        }
        result.setMessage(user.toString());
        return result;
    }

}
