package com.fresh.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.common.cache.service.JedisClient;
import com.fresh.common.constant.Constant;
import com.fresh.common.exception.RRException;
import com.fresh.common.utils.DateUtils;
import com.fresh.common.utils.SmsCRYUtil;
import com.fresh.manager.mapper.SysSmsLogMapper;
import com.fresh.manager.pojo.SmsConfig;
import com.fresh.manager.pojo.SysSmsLog;
import com.fresh.manager.pojo.SysSmsLogExample;
import com.fresh.manager.pojo.SysSmsLogExample.Criteria;
import com.fresh.manager.pojo.shop.User;
import com.fresh.manager.service.ISysConfigService;
import com.fresh.manager.service.ISysSmsLogService;
import com.fresh.manager.service.utils.ShiroUtils;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysSmsLogServiceImpl implements ISysSmsLogService {
    @Autowired
    private SysSmsLogMapper smsLogMapper;
    @Autowired
    private ISysConfigService sysConfigService;
    
    @Value("${SMLOG_KEY}")
	private String SMLOG_KEY;
	
	@Value("${SMLOG_PREFIX}")
	private String SMLOG_PREFIX;
	
	@Value("${SMLOG_ID}")
	private String SMLOG_ID;
	
	@Autowired
	private JedisClient jedisClient;

    @Override
    public SysSmsLog queryById(String id) {
        return smsLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysSmsLog> queryList(SysSmsLog smsLog, Integer pageNum, Integer pageSize) {
    	if(pageNum!=null&&pageSize!=null)
    		PageHelper.startPage(pageNum, pageSize);
        return smsLogMapper.queryList(smsLog);
    }

    @Override
    public long queryTotal(SysSmsLog smsLog) {
        SysSmsLogExample example = new SysSmsLogExample();
        Criteria c = example.createCriteria();
        if(StringUtils.isNotEmpty(smsLog.getSendId())){
        	c.andSendIdEqualTo(smsLog.getSendId());
        }
		return smsLogMapper.countByExample(example);
    }

    @Override
    public int insertSelective(SysSmsLog smsLog) {
       
        String string = jedisClient.get(SMLOG_KEY);
		 if (StringUtils.isBlank(string)) {
			jedisClient.set(SMLOG_KEY, SMLOG_ID);
		 }
		 long incr = jedisClient.incr(SMLOG_KEY);
//		 smsLog.setId(IdUtil.createIdbyUUID());
		 smsLog.setId(SMLOG_PREFIX+incr);
        return smsLogMapper.insertSelective(smsLog);
    }

    @Override
    public int updateByPrimaryKey(SysSmsLog smsLog) {
        return smsLogMapper.updateByPrimaryKey(smsLog);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return smsLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBatch(List<String> ids) {
    	SysSmsLogExample se = new SysSmsLogExample();
		Criteria criteria =  se.createCriteria();
		criteria.andIdIn(ids);
		return smsLogMapper.deleteByExample(se);
    }

    @Override
    public SysSmsLog sendSms(User loginUser,SysSmsLog smsLog) {
        String result = "";
        //获取云存储配置信息
        SmsConfig config = sysConfigService.getConfigObject(Constant.SMS_CONFIG_KEY, SmsConfig.class);
        if (config==null) {
            throw new RRException("请先配置短信平台信息");
        }
        if (StringUtils.isEmpty(config.getName())) {
            throw new RRException("请先配置短信平台用户名");
        }
        if (StringUtils.isEmpty(config.getPwd())) {
            throw new RRException("请先配置短信平台密钥");
        }
        if (StringUtils.isEmpty(config.getSign())) {
            throw new RRException("请先配置短信平台签名");
        }
        try {
            /**
             * 状态,发送编号,无效号码数,成功提交数,黑名单数和消息，无论发送的号码是多少，一个发送请求只返回一个sendid，如果响应的状态不是“0”，则只有状态和消息
             */
            result = SmsCRYUtil.crSendSms(config.getName(), config.getPwd(), smsLog.getMobile(), smsLog.getContent(), config.getSign(),
                    DateUtils.format(smsLog.getStime(), "yyyy-MM-dd HH:mm:ss"), smsLog.getExtno());
        } catch (Exception e) {

        }
        String arr[] = result.split(",");
        //发送成功
        if ("0".equals(arr[0])) {
            smsLog.setSendId(arr[1]);
            smsLog.setInvalidNum(Integer.parseInt(arr[2]));
            smsLog.setSuccessNum(Integer.parseInt(arr[3]));
            smsLog.setBlackNum(Integer.parseInt(arr[4]));
            smsLog.setReturnMsg(arr[5]);
        } else {
            //发送失败
            smsLog.setReturnMsg(arr[1]);
        }
        smsLog.setSendStatus(Integer.parseInt(arr[0]));
        try {
        	if(loginUser==null)
        		smsLog.setUserId(ShiroUtils.getUserId());
        	else
        		smsLog.setUserId(new Long(loginUser.getId()));
        } catch (Exception e) {
            //外部发送短信
            smsLog.setUserId(1L);
        }
        smsLog.setSign(config.getSign());
        if (null == smsLog.getStime()) {
            smsLog.setStime(new Date());
        }
        //保存发送记录
        ISysSmsLogService gs = (ISysSmsLogService) AopContext.currentProxy();
        gs.insertSelective(smsLog);
        return smsLog;
    }
}
