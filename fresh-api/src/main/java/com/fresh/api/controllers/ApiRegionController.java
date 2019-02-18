package com.fresh.api.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.api.annotation.IgnoreAuth;
import com.fresh.api.pojo.SysRegion;
import com.fresh.api.util.ApiBaseAction;
import com.fresh.api.util.ApiRegionCacheUtil;
import com.fresh.common.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "地区")
@RestController
@RequestMapping("/api/region")
public class ApiRegionController extends ApiBaseAction {

	@Autowired
	private ApiRegionCacheUtil apiRegionCacheUtil;
	
    @ApiOperation(value = "地区列表")
    @IgnoreAuth
    @PostMapping("/list")
    public Object list(Integer parentId) {
        List<SysRegion> regionList = apiRegionCacheUtil.getChildrenByParentId(parentId);
        List<SysRegion> returnRegionList = new ArrayList<SysRegion>();
        if (null != regionList && regionList.size() > 0) {
            for (SysRegion sysRegion : regionList) {
                returnRegionList.add(new SysRegion(sysRegion));
            }
        }
        return toResponsSuccess(returnRegionList);
    }

    @IgnoreAuth
    @PostMapping("/provinceList")
    public Object provinceList() {
        List<SysRegion> regionList = apiRegionCacheUtil.getAllProvice();
        List<SysRegion> returnRegionList = new ArrayList<SysRegion>();
        if (null != regionList && regionList.size() > 0) {
            for (SysRegion sysRegion : regionList) {
                returnRegionList.add(new SysRegion(sysRegion));
            }
        }
        return toResponsSuccess(returnRegionList);
    }

    @IgnoreAuth
    @PostMapping("/cityList")
    public Object provinceList(String proviceName) {
        List<SysRegion> regionList = apiRegionCacheUtil.getChildrenCity(proviceName);
        List<SysRegion> returnRegionList = new ArrayList<SysRegion>();
        if (null != regionList && regionList.size() > 0) {
            for (SysRegion sysRegion : regionList) {
                returnRegionList.add(new SysRegion(sysRegion));
            }
        }
        return toResponsSuccess(returnRegionList);
    }

    @IgnoreAuth
    @PostMapping("/distinctList")
    public Object distinctList(String proviceName, String cityName) {
        List<SysRegion> regionList = apiRegionCacheUtil.getChildrenDistrict(proviceName, cityName);
        List<SysRegion> returnRegionList = new ArrayList<SysRegion>();
        if (null != regionList && regionList.size() > 0) {
            for (SysRegion sysRegion : regionList) {
                returnRegionList.add(new SysRegion(sysRegion));
            }
        }
        return toResponsSuccess(returnRegionList);
    }

    @IgnoreAuth
    @PostMapping("/info")
    public Object info(Integer regionId) {
        SysRegion regionEntity = apiRegionCacheUtil.getAreaByAreaId(regionId);
        return toResponsSuccess(new SysRegion(regionEntity));
    }

    @IgnoreAuth
    @PostMapping("/regionIdsByNames")
    public Object regionIdsByNames(String provinceName, String cityName, String districtName) {
        Map<String, Integer> resultObj = new HashMap<String, Integer>();
        Integer provinceId = 0;
        Integer cityId = 0;
        Integer districtId = 0;
        if (null != provinceName) {
            provinceId = apiRegionCacheUtil.getProvinceIdByName(provinceName);
        }
        if (null != provinceId && !StringUtils.isNullOrEmpty(cityName)) {
            cityId = apiRegionCacheUtil.getCityIdByName(provinceId, cityName);
        }
        if (null != provinceId && null != cityId && !StringUtils.isNullOrEmpty(districtName)) {
            districtId = apiRegionCacheUtil.getDistrictIdByName(provinceId, cityId, districtName);
        }
        resultObj.put("provinceId", provinceId);
        resultObj.put("cityId", cityId);
        resultObj.put("districtId", districtId);
        return toResponsSuccess(resultObj);
    }
}