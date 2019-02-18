package com.fresh.manager.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.fresh.common.cache.service.JedisClient;
import com.fresh.common.utils.JsonUtils;
import com.fresh.manager.core.AppContextInitListener;
import com.fresh.manager.mapper.SysRegionMapper;
import com.fresh.manager.pojo.SysRegion;

@Component
public class RegionCacheComponent implements AppContextInitListener {

    @Autowired
    private SysRegionMapper sysRegionMapper;
	
    @Value("${CACHE_KEY}")
	private String CACHE_KEY;
    
	@Value("${REGION_KEY}")
	private String REGION_KEY;
	
	@Autowired
	private JedisClient jedisClient;
	
    public  void init() {
//        SysRegionMapper regionDao = SpringContextUtils.getBean(SysRegionMapper.class);
//        if (null != regionDao) {
//            SysRegionList = regionDao.queryList(null);
//        }
    	 List<SysRegion> SysRegionList = sysRegionMapper.queryList(null);
    	
//    	 String json = jedisClient.get(REGION_KEY);
//		 if (StringUtils.isBlank(json)) {
			jedisClient.hset(CACHE_KEY, REGION_KEY, JsonUtils.objectToJson(SysRegionList));
//		 }
    	System.out.println("SysRegionList:"+SysRegionList.size());
    }
    
    public  List<SysRegion>  load(){
   	 	 String json = jedisClient.get(REGION_KEY);
		 if (StringUtils.isBlank(json)) {
			 List<SysRegion> SysRegionList = sysRegionMapper.queryList(null);
			 jedisClient.hset(CACHE_KEY, REGION_KEY, JsonUtils.objectToJson(SysRegionList));
		 }
		 return  JsonUtils.jsonToList(json, SysRegion.class);
    }
    
    public  List<SysRegion>  reload(){
  	 	 String json = jedisClient.get(REGION_KEY);
		 if (!StringUtils.isBlank(json)) {
			 jedisClient.del(REGION_KEY);
			 List<SysRegion> SysRegionList = sysRegionMapper.queryList(null);
			 jedisClient.hset(CACHE_KEY, REGION_KEY, JsonUtils.objectToJson(SysRegionList));
		 }
		 return  JsonUtils.jsonToList(json, SysRegion.class);
   }
    
    /**
     * 获取所有国家
     *
     * @return
     */
    public  List<SysRegion> getAllCountry() {
        List<SysRegion> resultObj = new ArrayList<SysRegion>();
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
                if (areaVo.getType()==0) {
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
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
                if (areaVo.getType()==1) {
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
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
                if (areaVo.getType()==2) {
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
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
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
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
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
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
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
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
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
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
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
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
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
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
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
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
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
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
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
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
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
        List<SysRegion> SysRegionList = load();
        if (null != SysRegionList) {
            for (SysRegion areaVo : SysRegionList) {
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
	public void contextInitialized(ApplicationContext applicationContext) {
		// TODO Auto-generated method stub
		init();
	}

}