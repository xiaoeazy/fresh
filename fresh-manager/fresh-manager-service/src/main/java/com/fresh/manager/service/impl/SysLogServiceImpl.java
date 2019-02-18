package com.fresh.manager.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.fresh.manager.mapper.SysLogMapper;
import com.fresh.manager.pojo.SysLog;
import com.fresh.manager.pojo.SysLogExample;
import com.fresh.manager.pojo.SysLogExample.Criteria;
import com.fresh.manager.service.ISysLogService;
import com.github.pagehelper.PageHelper;


@Service
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl implements ISysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public SysLog queryById(Long id) {
        return sysLogMapper.selectByPrimaryKey(id);
    }

    @Override
   	public List<SysLog> queryList(SysLog sysLog, int pageNum, int pageSize) {
   		PageHelper.startPage(pageNum, pageSize);
   		SysLogExample ge = new SysLogExample();
   		Criteria c = ge.createCriteria();
       	if(sysLog.getId()!=null){
           	c.andIdEqualTo(sysLog.getId());
       	}
       	if(!StringUtils.isEmpty(sysLog.getMethod())){
           	c.andMethodEqualTo(sysLog.getMethod());
       	}
       	if(!StringUtils.isEmpty(sysLog.getOperation())){
           	c.andOperationEqualTo(sysLog.getOperation());
       	}
       	if(!StringUtils.isEmpty(sysLog.getParams())){
           	c.andParamsEqualTo(sysLog.getParams());
       	}
       	if(!StringUtils.isEmpty(sysLog.getUsername())){
           	c.andUsernameEqualTo(sysLog.getUsername());
       	}
       	List<SysLog> list = sysLogMapper.selectByExample(ge);
           
        for (SysLog SysLog : list) {
        	SysLog.setIp(getIpDetails(SysLog.getIp()));
        }
        return list;
   	}

    @Override
    public long queryTotal(SysLog sysLog) {
    	SysLogExample ge = new SysLogExample();
   		Criteria c = ge.createCriteria();
       	if(sysLog.getId()!=null){
           	c.andIdEqualTo(sysLog.getId());
       	}
       	if(!StringUtils.isEmpty(sysLog.getMethod())){
           	c.andMethodEqualTo(sysLog.getMethod());
       	}
       	if(!StringUtils.isEmpty(sysLog.getOperation())){
           	c.andOperationEqualTo(sysLog.getOperation());
       	}
       	if(!StringUtils.isEmpty(sysLog.getParams())){
           	c.andParamsEqualTo(sysLog.getParams());
       	}
       	if(!StringUtils.isEmpty(sysLog.getUsername())){
           	c.andUsernameEqualTo(sysLog.getUsername());
       	}
       	return sysLogMapper.countByExample(ge);
    }

    @Override
    public int insertSelective(SysLog sysLog) {
        return sysLogMapper.insertSelective(sysLog);
    }

    @Override
    public int updateByPrimaryKeySelective(SysLog sysLog) {
       return sysLogMapper.updateByPrimaryKeySelective(sysLog);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
       return sysLogMapper.deleteByPrimaryKey(id);
    }

    
    @Override
	public int deleteBatch(List<Long> ids) {
    	SysLogExample se = new SysLogExample();
		Criteria criteria =  se.createCriteria();
		criteria.andIdIn(ids);
		return sysLogMapper.deleteByExample(se);
	}

    /**
     * 向指定URL发送GET方法的请求
     */
    public  String getIpDetails(String ip) {
    	String str=null;
    	
    	if(ip.startsWith("0:") ||ip.startsWith("0.") || ip.startsWith("127.")|| ip.startsWith("192.") ){
    		str = ip ;
    		return str;
    	}
		try {
			str = restTemplate.getForObject("http://ip.taobao.com/service/getIpInfo.php?ip="+ip, String.class);
	    	JSONObject jsonObject = JSONObject.parseObject(str.toString());
	    	
	    	//{"code":0,"data":{"ip":"1.1.1.1","country":"澳大利亚","area":"","region":"XX","city":"XX","county":"XX","isp":"XX","country_id":"AU","area_id":"","region_id":"xx","city_id":"xx","county_id":"xx","isp_id":"xx"}}
	    	jsonObject =(JSONObject) jsonObject.get("data");
	    	
	    	str =ip + ":" + jsonObject.getString("country") + " " + jsonObject.getString("region") + " "
            + jsonObject.getString("city") + " " + jsonObject.getString("county") + " " + jsonObject.getString("isp");
		} catch (RestClientException e) {
			str=ip;
  		} catch(Exception e){
  			str=ip;
  		}
    	return str;   
    }
}
