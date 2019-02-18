package com.fresh.manager.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fresh.common.constant.Constant;
import com.fresh.common.oss.AliyunCloudStorageService;
import com.fresh.common.oss.CloudStorageConfig;
import com.fresh.common.oss.CloudStorageService;
import com.fresh.common.oss.QcloudCloudStorageService;
import com.fresh.common.oss.QiniuCloudStorageService;
import com.fresh.manager.service.ISysConfigService;

/**
 * 文件上传Factory
 */
@Component
public final class OSSFactory {
	@Autowired
    private   ISysConfigService sysConfigService;

//    static {
//        OSSFactory.sysConfigService = (ISysConfigService) SpringContextUtils.getBean("sysConfigService");
//    }

    public  CloudStorageService build() {
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(Constant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            return new QiniuCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            return new AliyunCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
