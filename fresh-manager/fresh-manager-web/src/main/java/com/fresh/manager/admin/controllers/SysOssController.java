package com.fresh.manager.admin.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.fresh.common.annotation.SysLog;
import com.fresh.common.constant.Constant;
import com.fresh.common.exception.RRException;
import com.fresh.common.oss.CloudStorageConfig;
import com.fresh.common.utils.PageUtils;
import com.fresh.common.utils.R;
import com.fresh.common.validator.ValidatorUtils;
import com.fresh.common.validator.group.AliyunGroup;
import com.fresh.common.validator.group.QcloudGroup;
import com.fresh.common.validator.group.QiniuGroup;
import com.fresh.manager.components.OSSFactory;
import com.fresh.manager.controllers.BaseController;
import com.fresh.manager.pojo.SysOss;
import com.fresh.manager.service.ISysConfigService;
import com.fresh.manager.service.ISysOssService;

/**
 * 文件上传Controller
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController  extends BaseController {
    @Autowired
    private ISysOssService sysOssService;
    @Autowired
    private ISysConfigService sysConfigService;
    @Autowired
    private OSSFactory ossFactory;
    
    private final static String KEY = Constant.CLOUD_STORAGE_CONFIG_KEY;

    /**
     * 列表
     *
     * @param params 请求参数
     * @return R
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:oss:all")
    public R list(SysOss sysOss,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	
        List<SysOss> sysOssList = sysOssService.queryList(sysOss, page, limit);
        int total = (int) sysOssService.queryTotal(sysOss);

        PageUtils pageUtil = new PageUtils(sysOssList, total, limit, page);

        return R.ok().put("page", pageUtil);
    }


    /**
     * 获取云存储配置信息
     *
     * @return R
     */
    @RequestMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config() {
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }


    /**
     * 保存云存储配置信息
     *
     * @param config 配置信息
     * @return R
     */
    @SysLog("保存云存储配置信息")
    @RequestMapping("/saveConfig")
    @RequiresPermissions("sys:oss:all")
    public R saveConfig(@RequestBody CloudStorageConfig config) {
        //校验类型
        ValidatorUtils.validateEntity(config);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            //校验七牛数据
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            //校验阿里云数据
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            //校验腾讯云数据
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }

        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));

        return R.ok();
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return R
     * @throws Exception 异常
     */
    @RequestMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String url = ossFactory.build().upload(file);

        //保存文件信息
        SysOss sysOss = new SysOss();
        sysOss.setUrl(url);
        sysOss.setCreateDate(new Date());
        sysOssService.insertSelective(sysOss);

        R r = new R();
        r.put("url", url);
        r.put("link", url);
        return r;
    }


    /**
     * 删除图片
     *
     * @param ids 主键集
     * @return R
     */
    @SysLog("删除图片")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:oss:all")
    public R delete(@RequestBody Long[] ids) {
    	List<Long> values = new ArrayList<Long>();
        sysOssService.deleteBatch(values);
        return R.ok();
    }

    /**
     * 查询所有列表
     *
     * @param params 请求参数
     * @return R
     */
    @RequestMapping("/queryAll")
    public List<String> queryAll(SysOss sysOss) {
        //查询列表数据
        List<SysOss> sysOssList = sysOssService.queryList(sysOss, null, null);

        List<String> list = new ArrayList<>();
        if (null != sysOssList && sysOssList.size() > 0) {
            for (SysOss item : sysOssList) {
                list.add(item.getUrl());
            }
        }
        return list;
    }
}