package com.fresh.manager.service;

import java.util.List;

import com.fresh.manager.pojo.SysDept;

/**
 * 部门管理
 *
 */
public interface ISysDeptService {
	/**
	 * 根据id查询部门
	 * @param id
	 * @return
	 */
	SysDept queryById(Long id);
	/**
	 * 查询列表
	 * @param sysDept
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<SysDept> queryList(SysDept sysDept, Integer pageNum, Integer pageSize);
	/**
	 * 插入
	 * @param sysDept
	 * @return
	 */
	int insertSelective(SysDept sysDept);
	/**
	 * 更新
	 * @param sysDept
	 * @return
	 */
	int updateByPrimaryKeySelective(SysDept sysDept);
	/**
	 * 根据deptid删除
	 * @param deptId
	 */
	void deleteByPrimaryKey(Long deptId);
	/**
	 * 查询子部门ID列表
	 * @param parentId
	 * @return
	 */
	List<Long> queryDetpIdList(Long parentId);
	/**
	 * 分页查询组织审批选择范围
	 * @param deptId
	 * @return
	 */
	List<Long> getSubDeptIdList(Long deptId);

}
