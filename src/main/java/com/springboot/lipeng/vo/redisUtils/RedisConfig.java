package com.springboot.lipeng.vo.redisUtils;

//import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

/**
* @Description:    设置RedisTemplate的序列化方式
* @Author:         LiPeng
* @CreateDate:     2019/5/30 13:37
* @UpdateUser:     LiPeng
* @UpdateDate:     2019/5/30 13:37
* @UpdateRemark:   暂无
* @Version:        1.0
*/
@Configuration  //初始化spring容器,提前加载"EnableCaching"springBean对象
@EnableCaching
//@EnableCaching:注解驱动的缓存管理,如果在注入"RedisConfig"时,缓存中有这个Sring Bean对象,
//                 则会使用缓存的bean执行处理
public class RedisConfig extends CachingConfigurerSupport {
    @Autowired
    RedisTemplate redisTemplate;
    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }

    /**
     * 配置缓存管理器
     * @return
     */

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheManager cacheManager = RedisCacheManager.create(factory);
        return cacheManager;
    }

    @Bean
    public RedisTemplate initRedisTemplate(RedisConnectionFactory redisConnectionFactory, RedisSerializer fastJson2JsonRedisSerializer) throws Exception {
       // RedisTemplate redisTemplate = new RedisTemplate();
        //ParserConfig.getGlobalInstance().addAccept("org.apache.shiro.session.mgt.SimpleSession");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean(name = "redisUtils")
    public RedisUtils redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtils redisUtil = new RedisUtils();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }



    //自定义keyGenerator必须实现org.springframework.cache.interceptor.KeyGenerator接口
    @Bean
    public KeyGenerator accountKeyGenerator() {
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                //first parameter is caching object
                //second paramter is the name of the method, we like the caching key has nothing to do with method name
                //third parameter is the list of parameters in the method being called
                return target.getClass().toString()/* + "accountId:" + params[0].toString()*/;
            }
        };
    }

}
