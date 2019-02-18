package com.fresh.manager.mapper;

import com.fresh.manager.pojo.SysMacro;
import com.fresh.manager.pojo.SysMacroExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMacroMapper {
    long countByExample(SysMacroExample example);

    int deleteByExample(SysMacroExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysMacro record);

    int insertSelective(SysMacro record);

    List<SysMacro> selectByExample(SysMacroExample example);

    SysMacro selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysMacro record, @Param("example") SysMacroExample example);

    int updateByExample(@Param("record") SysMacro record, @Param("example") SysMacroExample example);

    int updateByPrimaryKeySelective(SysMacro record);

    int updateByPrimaryKey(SysMacro record);
    
    
    /**
     * 查询数据字段
     *
     * @param value
     * @return
     */
    List<SysMacro> queryMacrosByValue(@Param("value") String value);
}