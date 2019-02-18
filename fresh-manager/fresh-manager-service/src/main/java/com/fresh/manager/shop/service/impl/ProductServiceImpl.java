package com.fresh.manager.shop.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.common.utils.BeanUtils;
import com.fresh.manager.mapper.shop.GoodsspecificationMapper;
import com.fresh.manager.mapper.shop.ProductMapper;
import com.fresh.manager.pojo.shop.Goodsspecification;
import com.fresh.manager.pojo.shop.Product;
import com.fresh.manager.pojo.shop.ProductExample;
import com.fresh.manager.pojo.shop.ProductExample.Criteria;
import com.fresh.manager.shop.service.IProductService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private GoodsspecificationMapper goodsSpecificationMapper;
    @Override
    public Product queryById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }
    @Override
   	public List<Product> queryList(Product product, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
       	List<Product> list = productMapper.queryList(product);
       	List<Goodsspecification> listb = goodsSpecificationMapper.selectByExample(null);
       	Map<Integer,Goodsspecification> map = listb.stream().collect(Collectors.toMap(Goodsspecification::getId, a -> a));
        //翻译产品规格
        if (null != list && list.size() > 0) {
            for (Product item : list) {
                String specificationIds = item.getGoodsSpecificationIds();
                String specificationValue = "";
                if (!StringUtils.isEmpty(specificationIds)) {
                    String[] arr = specificationIds.split("_");
                    for (String goodsSpecificationId : arr) {
                        Goodsspecification entity = map.get(goodsSpecificationId);
                        if (null != entity) {
                            specificationValue += entity.getValue() + "；";
                        }
                    }
                }
                item.setSpecificationValue(item.getGoodsName() + " " + specificationValue);
            }
        }
        return list;
   	}
    
    @Override
    public long queryTotal(Product product) {
		return productMapper.queryTotal(product);
    }
    
    
    @Override
    public void insertSelective(Product product) {
    	 int result = 0;
         String goodsSpecificationIds = product.getGoodsSpecificationIds();
         if (StringUtils.isNotEmpty(goodsSpecificationIds)) {
             String[] goodsSpecificationIdArr = goodsSpecificationIds.split("_");
             for (int i = 0; i < goodsSpecificationIdArr.length - 1; i++) {
                 String[] oneId = goodsSpecificationIdArr[i].split(",");
                 String[] twoId = goodsSpecificationIdArr[i + 1].split(",");
                 for (int j = 0; j < oneId.length; j++) {
                     for (int k = 0; k < twoId.length; k++) {
                         String strGoodsSpecificationIds = null;
                         if (StringUtils.isEmpty(oneId[j]) || StringUtils.isEmpty(twoId[k])){
                             continue;
                         }
                         strGoodsSpecificationIds = oneId[j] + "_" + twoId[k];
                         product.setGoodsSpecificationIds(strGoodsSpecificationIds);
                         Product entity = new Product();
                         BeanUtils.copyProperties(product, entity);
                         result += productMapper.insertSelective(entity);
                     }
                 }
             }
         }
    }

    @Override
    public void updateByPrimaryKeySelective(Product product) {
    	if (StringUtils.isEmpty(product.getGoodsSpecificationIds())){
            product.setGoodsSpecificationIds("");
        }
    	productMapper.updateByPrimaryKeySelective(product);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	ProductExample ge = new ProductExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	productMapper.deleteByExample(ge);
    	
    }
}
