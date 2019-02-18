package com.fresh.manager.shop.service;

import java.util.List;

import com.fresh.manager.pojo.shop.Goods;

/**
 * Service接口
 *
 */
public interface IGoodsService {

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	Goods queryById(Integer id);

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	int delete(Integer id);

	/**
	 * 批量删除
	 * 
	 * @param values
	 */
	void deleteBatch(List<Integer> values);

	/**
	 * 更新
	 * 
	 * @param goods
	 */
	void updateByPrimaryKeySelective(Goods goods);

	/**
	 * 插入
	 * 
	 * @param goods
	 */
	void insertSelective(Goods goods);

	/**
	 * 查询总数
	 * 
	 * @param goods
	 * @return
	 */
	long queryTotal(Goods goods);

	/**
	 * 查询列表
	 * 
	 * @param goods
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Goods> queryList(Goods goods, Integer pageNum, Integer pageSize);

	/**
	 * 商品从回收站恢复
	 * 
	 * @param ids
	 * @return
	 */
	int back(Integer[] ids);

	/**
	 * 上架
	 * 
	 * @param id
	 * @return
	 */
	int enSale(Integer id);

	/**
	 * 下架
	 * 
	 * @param id
	 * @return
	 */
	int unSale(Integer id);
	
	/**
	 * 查询所有
	 * @return
	 */
	List<Goods> queryAll();
	/**
	 * 查询所有不在该团购的产品信息
	 * @param groupId
	 * @return
	 */
	List<Goods> listWhereNoInGroupByGroupId(Integer groupId);
	/**
	 * 查询所有在该团购的产品信息
	 * @param groupId
	 * @return
	 */
	List<Goods> listWhereInGroupByGroupId(Integer groupId);

}
