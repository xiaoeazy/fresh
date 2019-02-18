package com.fresh.manager.mapper.shop;

import com.fresh.manager.pojo.shop.Helpissue;
import com.fresh.manager.pojo.shop.HelpissueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HelpissueMapper {
    long countByExample(HelpissueExample example);

    int deleteByExample(HelpissueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Helpissue record);

    int insertSelective(Helpissue record);

    List<Helpissue> selectByExample(HelpissueExample example);

    Helpissue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Helpissue record, @Param("example") HelpissueExample example);

    int updateByExample(@Param("record") Helpissue record, @Param("example") HelpissueExample example);

    int updateByPrimaryKeySelective(Helpissue record);

    int updateByPrimaryKey(Helpissue record);
}