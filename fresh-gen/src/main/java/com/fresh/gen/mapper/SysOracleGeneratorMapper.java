package com.fresh.gen.mapper;


import java.util.List;
import java.util.Map;

import com.fresh.gen.pojo.ResultMap;

/**
 * oracle代码生成器
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年07月23日 上午13:06:04
 */
public interface SysOracleGeneratorMapper {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<ResultMap> queryColumns(String tableName);
}
