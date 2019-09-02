package com.chenjianwen.cache;

import com.chenjianwen.util.JedisUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.Set;

/**
 * @Description: <br>
 * @Date: Created in 2019/9/2 <br>
 * @Author: chenjianwen
 */
@Component
public class RedisCache<K,V> implements Cache<K,V>{

    private final String CACHE_PREFIX = "chenjianwen_chche:";

    @Autowired
    private JedisUtils jedisUtils;

    private byte[] getKey(K k){
        if(k instanceof String){
            return (CACHE_PREFIX + k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    @Override
    public V get(K k) throws CacheException {
        System.out.println("从redis中获取授权数据");
        //这里可以优化一下，可以将数据存储到本地缓存中，如果读不到数据，再从redis中读取，redis读取不到，从数据库中读取
        byte[] value = jedisUtils.get(getKey(k));
        if(value != null){
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        byte[] key = getKey(k);
        byte[] value = SerializationUtils.serialize(v);
        jedisUtils.set(key,value);
        jedisUtils.expire(key,600);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        byte[] key = getKey(k);
        byte[] value = jedisUtils.get(key);
        jedisUtils.del(key);
        if(value != null){
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {
        //这里是清空redis数据操作，由于redis中不仅仅存储权限信息，所以这里不做处理
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
