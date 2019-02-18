package com.fresh.manager.mapper;

import com.fresh.manager.pojo.SysSmsLog;
import com.fresh.manager.pojo.SysSmsLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSmsLogMapper {
    long countByExample(SysSmsLogExample example);

    int deleteByExample(SysSmsLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysSmsLog record);

    int insertSelective(SysSmsLog record);

    List<SysSmsLog> selectByExample(SysSmsLogExample example);

    SysSmsLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysSmsLog record, @Param("example") SysSmsLogExample example);

    int updateByExample(@Param("record") SysSmsLog record, @Param("example") SysSmsLogExample example);

    int updateByPrimaryKeySelective(SysSmsLog record);

    int updateByPrimaryKey(SysSmsLog record);
    
    
    
    List<SysSmsLog> queryList(SysSmsLog sysSmsLog);
    
}