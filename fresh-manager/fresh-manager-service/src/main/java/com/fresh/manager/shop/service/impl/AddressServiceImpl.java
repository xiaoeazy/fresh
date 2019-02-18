package com.fresh.manager.shop.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.manager.mapper.shop.AddressMapper;
import com.fresh.manager.pojo.shop.Address;
import com.fresh.manager.pojo.shop.AddressExample;
import com.fresh.manager.pojo.shop.AddressExample.Criteria;
import com.fresh.manager.shop.service.IAddressService;
import com.github.pagehelper.PageHelper;


@Service
@Transactional(rollbackFor = Exception.class)
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Address queryById(Integer id) {
        return addressMapper.queryById(id);
    }
    
    @Override
   	public List<Address> queryList(Address address, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
//   		AddressExample ge = new AddressExample();
//   		Criteria c =  ge.createCriteria();
//       	if(!StringUtils.isEmpty(user.getUsername())){
//           	c.andUsernameLike("%"+user.getUsername()+"%");
//       	}
       	List<Address> list = addressMapper.queryList(address);
       	if (null != list && list.size() > 0) {
            for (Address Address : list) {
                //翻译收货地址国家码

            }
        }
           
        return list;
   	}
    

    @Override
    public long queryTotal(Address address) {
        AddressExample ge = new AddressExample();
   		Criteria c =  ge.createCriteria();
   		if(address.getUserId()!=null){
           	c.andUserIdEqualTo(address.getUserId());
       	}
       	if(!StringUtils.isEmpty(address.getUserName())){
           	c.andUserNameLike("%"+address.getUserName()+"%");
       	}
       	if(!StringUtils.isEmpty(address.getTelNumber())){
           	c.andTelNumberLike("%"+address.getTelNumber()+"%");
       	}
		return addressMapper.countByExample(ge);
    }
    
    
    @Override
    public void insertSelective(Address address) {
    	addressMapper.insertSelective(address);
    }

    @Override
    public void updateByPrimaryKeySelective(Address address) {
    	addressMapper.updateByPrimaryKeySelective(address);
    }
    
    @Override
    public void deleteBatch(List<Integer> values) {
    	AddressExample ge = new AddressExample();
    	Criteria c =  ge.createCriteria();
    	c.andIdIn(values);
    	addressMapper.deleteByExample(ge);
    }
    
    
    @Override
	public void saveOrUpdate(Address entity){
		if(entity.getIsDefault()==true){
			AddressExample isDefaultAddress = new AddressExample();
			Criteria criteria = isDefaultAddress.createCriteria();
			criteria.andUserIdEqualTo(entity.getUserId());
			Address a = new Address();
			a.setIsDefault(false);
			addressMapper.updateByExampleSelective(a, isDefaultAddress);
        }
		if (null == entity.getId() || entity.getId() == 0) {
            entity.setId(null);
            addressMapper.insertSelective(entity);
        } else {
        	addressMapper.updateByPrimaryKey(entity);
        }
	}
    
    @Override
	public void deleteById(Integer id ){
		addressMapper.deleteByPrimaryKey(id);
	}
}
