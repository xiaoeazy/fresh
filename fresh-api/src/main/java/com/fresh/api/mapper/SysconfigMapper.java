package com.fresh.api.mapper;

import com.fresh.api.pojo.Sysconfig;
import com.fresh.api.pojo.SysconfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysconfigMapper {
    long countByExample(SysconfigExample example);

    int deleteByExample(SysconfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Sysconfig record);

    int insertSelective(Sysconfig record);

    List<Sysconfig> selectByExample(SysconfigExample example);

    Sysconfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Sysconfig record, @Param("example") SysconfigExample example);

    int updateByExample(@Param("record") Sysconfig record, @Param("example") SysconfigExample example);

    int updateByPrimaryKeySelective(Sysconfig record);

    int updateByPrimaryKey(Sysconfig record);
}