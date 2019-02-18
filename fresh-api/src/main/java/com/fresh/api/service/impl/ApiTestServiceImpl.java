package com.fresh.api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.api.service.IApiTestService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ApiTestServiceImpl implements IApiTestService {
	
}
