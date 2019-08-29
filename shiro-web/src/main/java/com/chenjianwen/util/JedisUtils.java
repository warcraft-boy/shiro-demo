package com.chenjianwen.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Description: <br>
 * @Date: Created in 2019/8/29 <br>
 * @Author: chenjianwen
 */
@Component
public class JedisUtils {

    @Resource
    private JedisPool jedisPool;

    private Jedis getResource(){
        return jedisPool.getResource();
    }

    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = getResource();
        try {
            jedis.set(key,value);
        } catch (Exception e) {
            jedis.close();
        }
        return value;
    }

    public void expire(byte[] key, int i) {
        Jedis jedis = getResource();
        try {
            jedis.expire(key,i);
        } catch (Exception e) {
            jedis.close();
        }
    }

    public byte[] get(byte[] key) {
        Jedis jedis = getResource();
        byte[] value = null;
        try {
            value = jedis.get(key);
        } catch (Exception e) {
            jedis.close();
        }
        return value;
    }


    public void del(byte[] key) {
        Jedis jedis = getResource();
        try {
            jedis.del(key);
        } catch (Exception e) {
            jedis.close();
        }
    }

    public Set<byte[]> keys(String str) {
        Jedis jedis = getResource();
        Set<byte[]> keys = null;
        try {
            keys = jedis.keys((str + "*").getBytes());
        } catch (Exception e) {
            jedis.close();
        }
        return keys;
    }
}
