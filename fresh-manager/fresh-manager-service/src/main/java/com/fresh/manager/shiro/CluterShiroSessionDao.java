package com.fresh.manager.shiro;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import com.fresh.common.cache.J2CacheComponent;
import com.fresh.common.constant.Constant;
import com.fresh.common.utils.SerializeUtil;
//import com.platform.cache.J2CacheUtils;

/**
 * 分布式session管理
 *
 * @author lipengjun
 * @date 2018年07月31日 上午14:50
 */
public class CluterShiroSessionDao extends EnterpriseCacheSessionDAO {
	
	@Autowired
	private J2CacheComponent j2CacheComponent;
    @Override
    protected Serializable doCreate(Session session) {
    	System.out.println("=============doCreate");
        Serializable sessionId = super.doCreate(session);

        final String key = Constant.SESSION_KEY + sessionId.toString();
        setShiroSession(key, session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
    	System.out.println("=============doReadSession");
        Session session = super.doReadSession(sessionId);
        if (null == session) {
            final String key = Constant.SESSION_KEY + sessionId.toString();
            session = getShiroSession(key);
        }
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
    	System.out.println("=============doUpdate");
        super.doUpdate(session);
        final String key = Constant.SESSION_KEY + session.getId().toString();
        setShiroSession(key, session);
    }

    @Override
    protected void doDelete(Session session) {
    	System.out.println("=============doDelete");
        super.doDelete(session);
        final String key = Constant.SESSION_KEY + session.getId().toString();

        j2CacheComponent.remove_Serialize(key);
    }

    private Session getShiroSession(String key) {
    	System.out.println("=============getShiroSession");
    	System.out.println(key);
    	byte[]  str  =  j2CacheComponent.hget_Serialize(key);
    	Session session = (Session) SerializeUtil.unserialize(str) ;
        return session;
    }

    private void setShiroSession(String key, Session session) {
    	System.out.println("=============setShiroSession");
    	j2CacheComponent.hset_Serialize(key, session);
    	j2CacheComponent.expire_Serialize(key, 3600);
    }
}
