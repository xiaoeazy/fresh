package com.fresh.manager.shop.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.AddressMapper;
import com.fresh.manager.mapper.shop.UserMapper;
import com.fresh.manager.pojo.shop.AddressExample;
import com.fresh.manager.pojo.shop.User;
import com.fresh.manager.pojo.shop.UserExample;
import com.fresh.manager.pojo.shop.UserExample.Criteria;
import com.fresh.manager.shop.service.IUserService;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public User queryById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
    @Override
   	public List<User> queryList(User user, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
//   		UserExample ge = new UserExample();
//   		Criteria c =  ge.createCriteria();
//       	if(!StringUtils.isEmpty(user.getUsername())){
//           	c.andUsernameLike("%"+user.getUsername()+"%");
//       	}
       	List<User> list = userMapper.queryList(user);
           
        return list;
   	}
    
    @Override
    public long queryTotal(User user) {
        UserExample ge = new UserExample();
   		Criteria c =  ge.createCriteria();
       	if(!StringUtils.isEmpty(user.getUsername())){
           	c.andUsernameLike("%"+user.getUsername()+"%");
       	}
		return userMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(User user) {
    	user.setRegisterTime(new Date());
    	userMapper.insertSelective(user);
    }

    @Override
    public void updateByPrimaryKeySelective(User user) {
    	userMapper.updateByPrimaryKeySelective(user);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	UserExample ge = new UserExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	userMapper.deleteByExample(ge);
    	
    	AddressExample ge2 = new AddressExample();
    	com.fresh.manager.pojo.shop.AddressExample.Criteria c2 =ge2.createCriteria();
    	c2.andUserIdIn(values);
    	addressMapper.deleteByExample(ge2);
    }

    

	
	@Override
	public User queryByOpenId(String openid){
		UserExample u = new UserExample();
        Criteria c = u.createCriteria();
        c.andWeixinOpenidEqualTo(openid);
        List<User> userList = userMapper.selectByExample(u);
        if(userList.size()==0)
        	return null;
        else
        	return userList.get(0);
	}
}
