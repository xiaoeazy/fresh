package com.fresh.manager.mapper.shop;

import com.fresh.manager.pojo.shop.Goodsgallery;
import com.fresh.manager.pojo.shop.GoodsgalleryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsgalleryMapper {
    long countByExample(GoodsgalleryExample example);

    int deleteByExample(GoodsgalleryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goodsgallery record);

    int insertSelective(Goodsgallery record);

    List<Goodsgallery> selectByExampleWithBLOBs(GoodsgalleryExample example);

    List<Goodsgallery> selectByExample(GoodsgalleryExample example);

    Goodsgallery selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goodsgallery record, @Param("example") GoodsgalleryExample example);

    int updateByExampleWithBLOBs(@Param("record") Goodsgallery record, @Param("example") GoodsgalleryExample example);

    int updateByExample(@Param("record") Goodsgallery record, @Param("example") GoodsgalleryExample example);

    int updateByPrimaryKeySelective(Goodsgallery record);

    int updateByPrimaryKeyWithBLOBs(Goodsgallery record);

    int updateByPrimaryKey(Goodsgallery record);
    
    
    
    List<Goodsgallery> queryList(Goodsgallery goodsgallery);
    
    int queryTotal(Goodsgallery goodsgallery);
}