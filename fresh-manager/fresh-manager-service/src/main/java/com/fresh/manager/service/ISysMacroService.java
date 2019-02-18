package com.fresh.manager.service;

import java.util.List;

import com.fresh.manager.pojo.SysMacro;

/**
 * 通用字典表Service接口
 *
 */
public interface ISysMacroService {
	/**
	 * 根据主键查询
	 * @param macroId
	 * @return
	 */
	SysMacro queryById(Long macroId);
	/**
	 * 查询
	 * @param sysMacro
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<SysMacro> queryList(SysMacro sysMacro, Integer pageNum, Integer pageSize);
	/**
	 * 查询size
	 * @param sysMacro
	 * @return
	 */
	long queryTotal(SysMacro sysMacro);
	/**
	 * 插入
	 * @param sysMacro
	 * @return
	 */
	int insertSelective(SysMacro sysMacro);
	/**
	 * 更新
	 * @param sysMacro
	 * @return
	 */
	int update(SysMacro sysMacro);
	/**
	 * 根据主键删除
	 * @param macroId
	 * @return
	 */
	int deleteByPrimaryKey(Long macroId);
	/**
	 * 根据主键批量删除
	 * @param ids
	 * @return
	 */
	int deleteBatch(List<Long> ids);
	List<SysMacro> queryMacrosByValue(String value);

    
}
