package com.fresh.api.service;

import java.util.Map;

import com.fresh.api.pojo.TbToken;


public interface ITokenService {

	TbToken queryByToken(String token);

	Map<String, Object> createToken(Integer userId);


    
    
}
