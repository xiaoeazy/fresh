package com.fresh.common.cache.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fresh.common.cache.service.JedisClient;
import com.fresh.common.utils.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingle implements JedisClient{
	
	@Autowired
	private JedisPool jedisPool; 
	
	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.set(key, value);
		jedis.close();
		return string;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.hget(hkey, key);
		jedis.close();
		return string;
	}
	
	@Override
	public Set<String> hkeys(String hkey) {
		Jedis jedis = jedisPool.getResource();
		Set<String> set = jedis.hkeys(hkey);
		jedis.close();
		return set;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}


	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(hkey, key);
		jedis.close();
		return result;
	}
	
	@Override
	public boolean exist(String key) {
		Jedis jedis = jedisPool.getResource();
		boolean flag = jedis.exists(key);
		jedis.close();
		return flag;
	}
	
	@Override
	public long hset_Serialize(String hkey, String key, Object obj){
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(SerializeUtil.serialize(hkey), SerializeUtil.serialize(key), SerializeUtil.serialize(obj));
		jedis.close();
		return result;
	}
	
	@Override
	public byte[] hget_Serialize(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		byte[] value = jedis.hget(SerializeUtil.serialize(hkey), SerializeUtil.serialize(key));
		jedis.close();
		return value;
	}
	@Override
	public long hdel_Serialize(String hkey, String key){
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(SerializeUtil.serialize(hkey), SerializeUtil.serialize(key));
		jedis.close();
		return result;
	}
	@Override
	public long expire_Serialize(String key, int second){
		Jedis jedis = jedisPool.getResource();
		long value = jedis.expire(SerializeUtil.serialize(key), second);
		jedis.close();
		return value;
	}

}
