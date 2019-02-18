package com.fresh.manager.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.fresh.common.exception.RRException;
import com.fresh.common.utils.R;
import com.fresh.common.utils.RequestUtil;
import com.fresh.manager.pojo.SysUser;
import com.fresh.manager.service.utils.ShiroUtils;

/**
 * Controller公共组件
 *
 */
public  class BaseController {
	protected static final String DEFAULT_PAGE = "1";
	protected static final String DEFAULT_PAGE_SIZE = "10";
	
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUser getUser() {
        return ShiroUtils.getUser();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

    protected Long getDeptId() {
        return getUser().getDeptId();
    }
    
    
    
    /**
     * 处理控制层所有异常.
     *
     * @param exception
     *            未捕获的异常
     * @param request
     *            HttpServletRequest
     * @return ResponseData(BaseException 被处理) 或者 ModelAndView(其他 Exception
     *         ,500错误)
     */
    @ExceptionHandler(value = { Exception.class })
    public Object exceptionHandler(Exception exception, HttpServletRequest request) {
        logger.error(exception.getMessage(), exception);
        Throwable thr = getRootCause(exception);
        if (RequestUtil.isAjaxRequest(request) || RequestUtil.isAPIRequest(request) || ServletFileUpload.isMultipartContent(request)) {              
            R r = new R();
            if (thr instanceof RRException) {
            	 RRException rre = (RRException) thr;
            	 r.put("code", 500);
                 r.put("msg", rre.getMessage());
            } else {
            	r.put("code", 500);
                r.put("msg", thr.toString());
            }
            return r;
        } else {
            ModelAndView view = new ModelAndView("500");
            if (thr instanceof RRException) {
            	RRException rre = (RRException) thr;
            	view.addObject("message", rre.getMessage());
            }else {
            	view.addObject("message", thr.toString());
            }
            return view;
        }
    }
    
    
    private Throwable getRootCause(Throwable throwable) {
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }
        return throwable;
    }

    
}
