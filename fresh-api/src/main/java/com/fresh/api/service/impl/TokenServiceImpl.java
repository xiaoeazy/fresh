package com.fresh.api.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.api.mapper.TbTokenMapper;
import com.fresh.api.pojo.TbToken;
import com.fresh.api.pojo.TbTokenExample;
import com.fresh.api.pojo.TbTokenExample.Criteria;
import com.fresh.api.service.ITokenService;
import com.fresh.common.utils.CharUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class TokenServiceImpl implements ITokenService {
	 //12小时后过期
    private final static int EXPIRE = 3600 * 12;
    
    
	@Autowired
	private TbTokenMapper tokenMapper;
	@Override
	public TbToken queryByToken(String token){
		// TODO Auto-generated method stub
		TbTokenExample te = new TbTokenExample();
		Criteria t = te.createCriteria();
		t.andTokenEqualTo(token);
		List<TbToken> list = tokenMapper.selectByExample(te);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	
	@Override
    public Map<String, Object> createToken(Integer userId) {
        //生成一个token
        String token = CharUtil.getRandomString(32);
        //当前时间
        Date now = new Date();

        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        TbToken tbToken = tokenMapper.selectByPrimaryKey(userId);
        if (tbToken == null) {
        	tbToken = new TbToken();
        	tbToken.setUserId(userId);
        	tbToken.setToken(token);
        	tbToken.setUpdateTime(now);
        	tbToken.setExpireTime(expireTime);
            //保存token
        	tokenMapper.insertSelective(tbToken);
        } else {
        	tbToken.setToken(token);
        	tbToken.setUpdateTime(now);
        	tbToken.setExpireTime(expireTime);
            //更新token
        	tokenMapper.updateByPrimaryKeySelective(tbToken);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
        map.put("expire", EXPIRE);
        return map;
    }
}
