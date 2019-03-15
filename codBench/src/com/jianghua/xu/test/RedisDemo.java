package com.jianghua.xu.test;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author : xujianghua
 * @description : TODO
 * @date : 2019/2/26 16:07
 */
public class RedisDemo {

    public static void main(String[] args) {
        Jedis jedis = RedisDemo.connectTest();
        RedisDemo.stringTest(jedis);
    }

    // 连接Redis
    public static Jedis connectTest(){
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        System.out.println("服务正在运行："+jedis.ping());
        return jedis;
    }

    /**
     * redis操作字符串
     */
    public static void stringTest(Jedis jedis){
        jedis.set("name","xu");
        System.out.println("name:"+jedis.get("name"));
        jedis.append("name","jainghua");
        System.out.println("name:"+jedis.get("name"));
        jedis.mset("name","jack","age","22");
        jedis.incr("age");
        System.out.println("name:"+jedis.get("name")+"-age:"+jedis.get("age"));
    }

    //列表实例
    public static void listTest(Jedis jedis){
        // 存储数据到列表中
        jedis.lpush("sit-list","xu");
        jedis.lpush("sit-list","jiang");
        jedis.lpush("sit-list","hua");
        // 获取存储数据并输出
        List<String> list = jedis.lrange("sit-list",0,2);
        for (String l:list ) {
            System.out.println(l);
        }
    }

    // keys实例
    public static void keysTest(Jedis jedis){
        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }
    }

}
