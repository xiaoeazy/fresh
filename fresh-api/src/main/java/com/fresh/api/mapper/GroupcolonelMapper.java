package com.fresh.api.mapper;

import com.fresh.api.pojo.Groupcolonel;
import com.fresh.api.pojo.GroupcolonelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupcolonelMapper {
    long countByExample(GroupcolonelExample example);

    int deleteByExample(GroupcolonelExample example);

    int deleteByPrimaryKey(Integer colonelGroupId);

    int insert(Groupcolonel record);

    int insertSelective(Groupcolonel record);

    List<Groupcolonel> selectByExample(GroupcolonelExample example);

    Groupcolonel selectByPrimaryKey(Integer colonelGroupId);

    int updateByExampleSelective(@Param("record") Groupcolonel record, @Param("example") GroupcolonelExample example);

    int updateByExample(@Param("record") Groupcolonel record, @Param("example") GroupcolonelExample example);

    int updateByPrimaryKeySelective(Groupcolonel record);

    int updateByPrimaryKey(Groupcolonel record);
}