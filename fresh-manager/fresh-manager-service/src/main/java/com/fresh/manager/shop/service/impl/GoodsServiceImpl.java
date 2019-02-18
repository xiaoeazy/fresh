package com.fresh.manager.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.common.exception.RRException;
import com.fresh.manager.mapper.shop.GoodsMapper;
import com.fresh.manager.mapper.shop.GoodsattributeMapper;
import com.fresh.manager.mapper.shop.GoodsgalleryMapper;
import com.fresh.manager.mapper.shop.ProductMapper;
import com.fresh.manager.pojo.SysUser;
import com.fresh.manager.pojo.shop.Goods;
import com.fresh.manager.pojo.shop.GoodsExample;
import com.fresh.manager.pojo.shop.GoodsExample.Criteria;
import com.fresh.manager.pojo.shop.Goodsattribute;
import com.fresh.manager.pojo.shop.Goodsgallery;
import com.fresh.manager.pojo.shop.Product;
import com.fresh.manager.service.utils.ShiroUtils;
import com.fresh.manager.shop.service.IGoodsService;
import com.github.pagehelper.PageHelper;


@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsattributeMapper goodsattributeMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private GoodsgalleryMapper goodsgalleryMapper;

    
    @Override
    public List<Goods> queryAll(){
    	return goodsMapper.queryList(new Goods());
    }
    
    @Override
    public List<Goods> listWhereNoInGroupByGroupId(Integer groupId){
    	return goodsMapper.listWhereNoInGroupByGroupId(groupId);
    }
    
    @Override
    public List<Goods> listWhereInGroupByGroupId(Integer groupId){
    	return goodsMapper.listWhereInGroupByGroupId(groupId);
    }

    @Override
    public Goods queryById(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }
    @Override
   	public List<Goods> queryList(Goods goods, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
       	List<Goods> list = goodsMapper.queryList(goods);
        return list;
   	}
    
    @Override
    public long queryTotal(Goods goods) {
        GoodsExample ge = new GoodsExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(goods.getName())){
           	c.andNameLike("%"+goods.getName()+"%");
       	}
       	if(goods.getIsDelete()!=null){
           	c.andIsDeleteEqualTo(goods.getIsDelete());
       	}
		return goodsMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Goods goods) {
    	IGoodsService gs = (IGoodsService) AopContext.currentProxy();
    	SysUser user = ShiroUtils.getUser();
    	
    	Goods queryGood = new Goods();
    	queryGood.setName(goods.getName());
        List<Goods> list = gs.queryList(queryGood,null,null);
        if (null != list && list.size() != 0) {
            throw new RRException("商品名称已存在！");
        }
        goodsMapper.insertSelective(queryGood);
        

        //保存产品信息
        Product product = new Product();
        product.setGoodsId(queryGood.getId());
        product.setGoodsSn(goods.getGoodsSn());
        product.setGoodsNumber(goods.getGoodsNumber());
        product.setRetailPrice(goods.getRetailPrice());
        product.setMarketPrice(goods.getMarketPrice());
        product.setGoodsSpecificationIds("");
        productMapper.insert(product);
        
        //保存产品后提交
        goods.setAddTime(new Date());
        goods.setPrimaryProductId(product.getId());

        //保存商品详情页面显示的属性
        List<Goodsattribute> attributeList = goods.getAttributeList();
        if (null != attributeList && attributeList.size() > 0) {
            for (Goodsattribute item : attributeList) {
                item.setGoodsId(queryGood.getId());
                goodsattributeMapper.insertSelective(item);
            }
        }

        //商品轮播图
        List<Goodsgallery> galleryList = goods.getGoodsImgList();
        if (null != galleryList && galleryList.size() > 0) {
            for (Goodsgallery item : galleryList) {
            	item.setGoodsId(queryGood.getId());
                goodsgalleryMapper.insertSelective(item);
            }
        }
        //最后再次更新
        goods.setIsDelete(false);
        goods.setCreateUserDeptId(user.getDeptId());
        goods.setCreateUserId(user.getUserId());
        goods.setUpdateUserId(user.getUserId());
        goods.setUpdateTime(new Date());
        goodsMapper.updateByPrimaryKey(goods);
    }

    @Override
    public void updateByPrimaryKeySelective(Goods goods) {
    	SysUser user = ShiroUtils.getUser();
    	
    	 List<Goodsattribute> attributeEntityList = goods.getAttributeList();
         if (null != attributeEntityList && attributeEntityList.size() > 0) {
             for (Goodsattribute item : attributeEntityList) {
                 int result = goodsattributeMapper.updateByPrimaryKey(item);
                 if (result == 0) {
                	 goodsattributeMapper.insert(item);
                 }
             }
         }
         goods.setUpdateUserId(user.getUserId());
         goods.setUpdateTime(new Date());
         //商品轮播图
         //修改时全删全插
         List<Goodsgallery> galleryList = goods.getGoodsImgList();
         goodsattributeMapper.deleteByPrimaryKey(goods.getId());
         if (null != galleryList && galleryList.size() > 0) {
             for (Goodsgallery item : galleryList) {
            	 item.setGoodsId(goods.getId());
            	 goodsgalleryMapper.insertSelective(item);
             }
         }
    	goodsMapper.updateByPrimaryKeySelective(goods);
    }
    
    @Override
    public int delete(Integer id) {
    	SysUser user = ShiroUtils.getUser();
    	IGoodsService gs = (IGoodsService) AopContext.currentProxy();
        Goods goods = gs.queryById(id);
        goods.setIsDelete(true);
        goods.setIsOnSale(false);
        goods.setUpdateUserId(user.getUserId());
        goods.setUpdateTime(new Date());
        return goodsMapper.updateByPrimaryKey(goods);
    }

    
    @Override
    public void deleteBatch(List<Integer> values) {
    	IGoodsService gs = (IGoodsService) AopContext.currentProxy();
    	for(Integer i:values){
    		gs.delete(i);
    	}
    }
    
    @Override
    public int back(Integer[] ids) {
    	SysUser user = ShiroUtils.getUser();
        int result = 0;
        for (Integer id : ids) {
            Goods goods = goodsMapper.selectByPrimaryKey(id);
            goods.setIsDelete(false);
            goods.setIsOnSale(true);
            goods.setUpdateUserId(user.getUserId());
            goods.setUpdateTime(new Date());
            result += goodsMapper.updateByPrimaryKey(goods);
        }
        return result;
    }
    
    @Override
    public int enSale(Integer id) {
    	SysUser user = ShiroUtils.getUser();
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        if (true == goods.getIsOnSale()) {
            throw new RRException("此商品已处于上架状态！");
        }
        goods.setIsOnSale(true);
        goods.setUpdateUserId(user.getUserId());
        goods.setUpdateTime(new Date());
        return goodsMapper.updateByPrimaryKey(goods);
    }
    
    @Override
    public int unSale(Integer id) {
    	SysUser user = ShiroUtils.getUser();
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        if (false == goods.getIsOnSale()) {
            throw new RRException("此商品已处于下架状态！");
        }
        goods.setIsOnSale(false);
        goods.setUpdateUserId(user.getUserId());
        goods.setUpdateTime(new Date());
        return goodsMapper.updateByPrimaryKey(goods);
    }
}
