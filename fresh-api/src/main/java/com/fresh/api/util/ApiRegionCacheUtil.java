package com.fresh.api.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.fresh.api.mapper.SysRegionMapper;
import com.fresh.api.pojo.SysRegion;
import com.fresh.common.utils.SpringContextUtils;


public class ApiRegionCacheUtil implements InitializingBean {
	
    public  List<SysRegion> regionList;

    @Autowired
    private  SysRegionMapper sysRegionMapper;
    
    public  void init() {
//    	SysRegionMapper sysRegionMapper = SpringContextUtils.getBean(SysRegionMapper.class);
        if (null != sysRegionMapper) {
        	regionList = sysRegionMapper.selectAllWithParentName();
        }
    }

    /**
     * 获取所有国家
     *
     * @return
     */
    public  List<SysRegion> getAllCountry() {
        List<SysRegion> resultObj = new ArrayList<SysRegion>();
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (areaVo.getType().equals(0)) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取全部省份
     *
     * @return
     */
    public  List<SysRegion> getAllProvice() {
        List<SysRegion> resultObj = new ArrayList<SysRegion>();
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (areaVo.getType().equals(1)) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取所有城市
     *
     * @return
     */
    public  List<SysRegion> getAllCity() {
        List<SysRegion> resultObj = new ArrayList<SysRegion>();
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (areaVo.getType().equals(2)) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据国家获取全部省份
     *
     * @return
     */
    public  List<SysRegion> getAllProviceByParentId(Integer areaId) {
        List<SysRegion> resultObj = new ArrayList<SysRegion>();
        if (null == areaId) {
            return resultObj;
        }
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(1) && areaId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取地市
     *
     * @return
     */
    public  List<SysRegion> getChildrenCity(Integer areaId) {
        List<SysRegion> resultObj = new ArrayList<SysRegion>();
        if (null == areaId) {
            return resultObj;
        }
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(2) && areaId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取地市
     *
     * @return
     */
    public  List<SysRegion> getChildrenCity(String proviceName) {
        List<SysRegion> resultObj = new ArrayList<SysRegion>();
        if (null == proviceName) {
            return resultObj;
        }
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(2) && proviceName.equals(areaVo.getParentName())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取区县
     *
     * @return
     */
    public  List<SysRegion> getChildrenDistrict(Integer areaId) {
        List<SysRegion> resultObj = new ArrayList<SysRegion>();
        if (null == areaId) {
            return resultObj;
        }
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(3) && areaId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取区县
     *
     * @return
     */
    public  List<SysRegion> getChildrenDistrict(String provinceName, String cityName) {
        List<SysRegion> resultObj = new ArrayList<SysRegion>();
        if (null == provinceName || null == cityName) {
            return resultObj;
        }
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (null != areaVo.getParentId() && areaVo.getType().equals(3)
                        && cityName.equals(areaVo.getParentName())
                        && null != getAreaByAreaId(areaVo.getParentId())
                        && provinceName.equals(getAreaByAreaId(areaVo.getParentId()).getParentName())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }


    /**
     * 获取区县
     *
     * @return
     */
    public  List<SysRegion> getChildrenByParentId(Integer parentId) {
        List<SysRegion> resultObj = new ArrayList<SysRegion>();
        if (null == parentId) {
            return resultObj;
        }
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (null != areaVo.getParentId() && parentId.equals(areaVo.getParentId())) {
                    resultObj.add(areaVo);
                }
            }
        }
        return resultObj;
    }

    /**
     * 获取区域名称
     *
     * @return
     */
    public  String getAreaNameByAreaId(Integer areaId) {
        if (null == areaId) {
            return "";
        }
        String resultObj = areaId.toString();
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (areaVo.getId().equals(areaId)) {
                    resultObj = areaVo.getName();
                    break;
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据Id获取区域
     *
     * @return
     */
    public  SysRegion getAreaByAreaId(Integer areaId) {
        SysRegion resultObj = null;
        if (null == areaId) {
            return resultObj;
        }
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (areaVo.getId().equals(areaId)) {
                    resultObj = areaVo;
                    break;
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据Id获取区域
     *
     * @return
     */
    public  Integer getProvinceIdByName(String areaName) {
        Integer resultObj = null;
        if (null == areaName) {
            return resultObj;
        }
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (areaVo.getType() == 1 && areaVo.getName().equals(areaName)) {
                    resultObj = areaVo.getId();
                    break;
                }
            }
        }
        return resultObj;
    }

    /**
     * 根据Id获取区域
     *
     * @return
     */
    public  Integer getCityIdByName(Integer provinceId, String areaName) {
        Integer resultObj = null;
        if (null == areaName) {
            return resultObj;
        }
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (areaVo.getType() == 2 && areaVo.getName().equals(areaName)
                        && areaVo.getParentId().equals(provinceId)) {
                    resultObj = areaVo.getId();
                    break;
                }
            }
        }
        return resultObj;
    }


    /**
     * 根据Id获取区域
     *
     * @return
     */
    public  Integer getDistrictIdByName(Integer provinceId, Integer cityId, String areaName) {
        Integer resultObj = null;
        if (null == areaName) {
            return resultObj;
        }
        if (null != regionList) {
            for (SysRegion areaVo : regionList) {
                if (areaVo.getType() == 3 && areaVo.getName().equals(areaName)
                        && areaVo.getParentId().equals(cityId)
                        && null != getAreaByAreaId(areaVo.getParentId())
                        && null != getAreaByAreaId(areaVo.getParentId()).getParentId()
                        && getAreaByAreaId(areaVo.getParentId()).getParentId().equals(provinceId)) {
                    resultObj = areaVo.getId();
                    break;
                }
            }
        }
        return resultObj;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

}