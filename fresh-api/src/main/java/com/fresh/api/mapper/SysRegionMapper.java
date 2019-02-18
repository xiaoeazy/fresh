package com.fresh.api.mapper;

import com.fresh.api.pojo.SysRegion;
import com.fresh.api.pojo.SysRegionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRegionMapper {
    long countByExample(SysRegionExample example);

    int deleteByExample(SysRegionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRegion record);

    int insertSelective(SysRegion record);

    List<SysRegion> selectByExample(SysRegionExample example);

    SysRegion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysRegion record, @Param("example") SysRegionExample example);

    int updateByExample(@Param("record") SysRegion record, @Param("example") SysRegionExample example);

    int updateByPrimaryKeySelective(SysRegion record);

    int updateByPrimaryKey(SysRegion record);
    
    
    List<SysRegion> selectAllWithParentName();
}