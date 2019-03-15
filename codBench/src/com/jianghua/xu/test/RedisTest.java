package com.jianghua.xu.test;

import com.jianghua.xu.util.RedisUtil;
import redis.clients.jedis.Jedis;

/**
 * @author : xujianghua
 * @description : TODO
 * @date : 2019/2/28 13:56
 */
public class RedisTest {

    private Jedis jedis;
     /**
      * @description : 连接jedis服务器
      *
      */
     public void connectRedis(){
         jedis = RedisUtil.getJedis();
     }

     /**
      * redis操作字符串
      */
     public  void testString(){
         // 添加数据
         jedis.set("name","xu");
         System.out.println("name:"+jedis.get("name"));
         // 拼接字符串
         jedis.append("name","jianghua");
         System.out.println("name:"+jedis.get("name"));
         // 删除数据
         jedis.del("name");
         System.out.println("name:"+jedis.get("name"));
         // 设置多个键值对
         jedis.mset("name","jack","age","22");
         jedis.incr("age");
         System.out.println("name:"+jedis.get("name")+"-"+"age:"+jedis.get("age"));
     }


    public static void main(String[] args) {
        RedisTest test = new RedisTest();
        test.connectRedis();
        test.testString();
    }
}
