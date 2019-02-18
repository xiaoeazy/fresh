package com.fresh.manager.service;

import java.util.List;

import com.fresh.manager.pojo.SysConfig;

/**
 * 系统配置信息
 *
 */
public interface ISysConfigService {
	/**
     * 保存配置信息
     */
	int insertSelective(SysConfig config);
	 /**
     * 更新配置信息
     */
	int updateByPrimaryKeySelective(SysConfig config);
	 /**
     * 根据key，更新value
     */
	int updateValueByKey(String name, String value);
	 /**
     * 删除配置信息
     */
	int deleteBatch(List<Long> ids);
	 /**
     * 获取List列表
     */
	List<SysConfig> queryList(SysConfig sysConfig, int pageNum, int pageSize);
	  /**
     * 获取总记录数
     */
	long queryTotal(SysConfig sysConfig);
	SysConfig queryById(Long id);
	 /**
     * 根据key，获取配置的value值
     *
     * @param key          key
     * @param defaultValue 缺省值
     */
	String getValue(String key, String defaultValue);
	/**
     * 根据key，获取value的Object对象
     *
     * @param key   key
     * @param clazz Object对象
     */
	 public <T> T getConfigObject(String key, Class<T> clazz);


}
