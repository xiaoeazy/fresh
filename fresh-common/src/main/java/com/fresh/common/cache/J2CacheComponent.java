package com.fresh.common.cache;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fresh.common.cache.service.JedisClient;
import com.fresh.common.utils.JsonUtils;

/**
 * 作者: @author 李鹏军 <br>
 * 时间: 2017-07-09 17:12<br>
 * 描述: J2CacheUtils <br>
 */

@Component
public class J2CacheComponent {
    /**
     * 商城业务缓存
     */
    public  static String SHOP_CACHE_NAME = "shopCache";
    /**
     * 系统缓存
     */
    private  static String SYS_CACHE_NAME = "sysCache";

    @Autowired
    private JedisClient jedisClient;

    /**
     * 获取SYS_CACHE_NAME缓存
     *
     * @param key
     * @return
     */
    public  String get(String key) {
        return get(SYS_CACHE_NAME, key);
    }

    /**
     * 写入SYS_CACHE_NAME缓存
     *
     * @param key
     * @return
     */
    public  void put(String key, Object value) {
        put(SYS_CACHE_NAME, key, value);
    }

    /**
     * 从SYS_CACHE_NAME缓存中移除
     *
     * @param key
     * @return
     */
    public  void remove(String key) {
        remove(SYS_CACHE_NAME, key);
    }

    /**
     * 获取缓存
     *
     * @param cacheName
     * @param key
     * @return
     */
    public  String get(String cacheName, String key) {
        return jedisClient.hget(cacheName, key);
    }

    
    /**
     * 写入缓存
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public  void put(String cacheName, String key, Object value) {
    	String a = "";
    	if(value instanceof Number){
    		a = String.valueOf(value);
    	}else{
    		a =  JsonUtils.objectToJson(value);
    	}
    	
    	jedisClient.hset(cacheName, key, a);
    }

    /**
     * 从缓存中移除
     *
     * @param cacheName
     * @param key
     */
    public  void remove(String cacheName, String key) {
    	jedisClient.hdel(cacheName, key);
    }
    
    public  void remove_Serialize(String cacheName, String key) {
    	jedisClient.hdel(cacheName, key);
    }

    /**
     * 获取SYS_CACHE缓存的所有key
     *
     * @return
     */
    public  Collection<String> keys() {
    	Collection<String> set = jedisClient.hkeys(SYS_CACHE_NAME);
        return set;
    }
    /**
     * 获取缓存的所有key
     *
     * @param cacheName
     * @return
     */
    public  Collection<String> keys(String cacheName) {
        return jedisClient.hkeys(cacheName);
    }

    /**
     * Clear the cache
     *
     * @param cacheName: Cache region name
     */
    public  void clear(String cacheName) {
    	jedisClient.del(cacheName);
    }
    
    public  boolean  exists(String key) {
        return jedisClient.exist( key);
    }
    
    
    public byte[] hget_Serialize( String key){
    	return jedisClient.hget_Serialize(SYS_CACHE_NAME, key);
    }
    
    public void hset_Serialize( String key, Object value){
    	String a =  JsonUtils.objectToJson(value);
    	jedisClient.hset_Serialize(SYS_CACHE_NAME, key, value);
    }

    public  void remove_Serialize(String key) {
    	remove_Serialize(SYS_CACHE_NAME, key);
    }
    
    
    public void expire_Serialize(String key ,int second ){
    	jedisClient.expire_Serialize(key, second);
    }
}
