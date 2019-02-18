package com.fresh.manager.mapper.shop;

import com.fresh.manager.pojo.shop.Userlevel;
import com.fresh.manager.pojo.shop.UserlevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserlevelMapper {
    long countByExample(UserlevelExample example);

    int deleteByExample(UserlevelExample example);

    int deleteByPrimaryKey(Short id);

    int insert(Userlevel record);

    int insertSelective(Userlevel record);

    List<Userlevel> selectByExample(UserlevelExample example);

    Userlevel selectByPrimaryKey(Short id);

    int updateByExampleSelective(@Param("record") Userlevel record, @Param("example") UserlevelExample example);

    int updateByExample(@Param("record") Userlevel record, @Param("example") UserlevelExample example);

    int updateByPrimaryKeySelective(Userlevel record);

    int updateByPrimaryKey(Userlevel record);
}