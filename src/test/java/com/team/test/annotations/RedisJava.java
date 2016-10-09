package com.team.test.annotations;

import redis.clients.jedis.Jedis;
public class RedisJava {
   public static void main(String[] args) {
      //连接本地的 Redis 服务
      Jedis jedis = new Jedis("localhost");
      System.out.println("Connection to server sucessfully");
      //查看服务是否运行
      System.out.println("Server is running: "+jedis.ping());
      jedis.set("runoobkey", "Redis tutoria******");
      // 获取存储的数据并输出
      System.out.println("Stored string in redis:: "+ jedis.get("runoobkey"));
  }
}
