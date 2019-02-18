package com.fresh.common.cache.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fresh.common.cache.service.JedisClient;
import com.fresh.common.utils.SerializeUtil;

import redis.clients.jedis.JedisCluster;

public class JedisClientCluster implements JedisClient {

	@Autowired
	private JedisCluster jedisCluster;
	
	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String hget(String hkey, String key) {
		return jedisCluster.hget(hkey, key);
	}

	@Override
	public long hset(String hkey, String key, String value) {
		return jedisCluster.hset(hkey, key, value);
	}
	
	@Override
	public Set<String> hkeys(String hkey) {
		Set<String> set = jedisCluster.hkeys(hkey);
		return set;
	}

	@Override
	public long incr(String key) {
		return jedisCluster.incr(key);
	}

	@Override
	public long expire(String key, int second) {
		return jedisCluster.expire(key, second);
	}

	@Override
	public long ttl(String key) {
		return jedisCluster.ttl(key);
	}

	@Override
	public long del(String key) {
		
		return jedisCluster.del(key);
	}

	@Override
	public long hdel(String hkey, String key) {
		
		return jedisCluster.hdel(hkey, key);
	}
	
	@Override
	public boolean exist(String key) {
		boolean flag = jedisCluster.exists(key);
		return flag;
	}
	
	@Override
	public long hset_Serialize(String hkey, String key, Object obj){
		Long result = jedisCluster.hset(SerializeUtil.serialize(hkey), SerializeUtil.serialize(key), SerializeUtil.serialize(obj));
		return result;
	}
	@Override
	public byte[] hget_Serialize(String hkey, String key) {
		byte[] Obj = jedisCluster.hget(SerializeUtil.serialize(hkey), SerializeUtil.serialize(key));
		return Obj;
	}
	@Override
	public long hdel_Serialize(String hkey, String key){
		return jedisCluster.hdel(SerializeUtil.serialize(hkey),SerializeUtil.serialize(key));
	}
	
	@Override
	public long expire_Serialize(String key, int second){
		return jedisCluster.expire(SerializeUtil.serialize(key), second);
	}




}
