package com.fresh.manager.mapper.shop;

import com.fresh.manager.pojo.shop.Product;
import com.fresh.manager.pojo.shop.Productsku;
import com.fresh.manager.pojo.shop.ProductskuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductskuMapper {
    long countByExample(ProductskuExample example);

    int deleteByExample(ProductskuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Productsku record);

    int insertSelective(Productsku record);

    List<Productsku> selectByExample(ProductskuExample example);

    Productsku selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Productsku record, @Param("example") ProductskuExample example);

    int updateByExample(@Param("record") Productsku record, @Param("example") ProductskuExample example);

    int updateByPrimaryKeySelective(Productsku record);

    int updateByPrimaryKey(Productsku record);
    
    List<Product> queryList(Product product);
    
    int queryTotal(Product product);
}