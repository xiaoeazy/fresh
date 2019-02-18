package com.fresh.common.cache.service;

import java.util.Set;

public interface JedisClient {

	String get(String key);
	String set(String key, String value);
	String hget(String hkey, String key);
	long hset(String hkey, String key, String value);
	Set<String> hkeys(String hkey);
	long incr(String key);
	long expire(String key, int second);
	long ttl(String key);
	long del(String key);
	long hdel(String hkey, String key);
	boolean exist(String key);
	
	long hset_Serialize(String hkey, String key, Object obj);
	byte[] hget_Serialize(String hkey, String key);
	long hdel_Serialize(String hkey, String key);
	long expire_Serialize(String key, int second);
}
