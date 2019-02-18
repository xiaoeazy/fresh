package com.fresh.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.api.annotation.LoginUser;
import com.fresh.api.util.ApiBaseAction;
import com.fresh.manager.pojo.shop.Address;
import com.fresh.manager.pojo.shop.User;
import com.fresh.manager.shop.service.IAddressService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/address")
public class ApiAddressController  extends ApiBaseAction {
	@Autowired
    private IAddressService addressService;

    /**
     * 获取用户的收货地址
     */
    @ApiOperation(value = "获取用户的收货地址接口", response = Map.class)
    @PostMapping("/list")
    public Object list(@LoginUser User loginUser) {
        Address address = new Address();
        address.setUserId(loginUser.getId());
        List<Address> addressEntities = addressService.queryList(address,null,null);
        return toResponsSuccess(addressEntities);
    }

    /**
     * 获取收货地址的详情
     */
    @ApiOperation(value = "获取收货地址的详情", response = Map.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "收获地址ID", required = true, dataType = "Integer")})
    @PostMapping("/detail")
    public Object detail(Integer id, @LoginUser User loginUser) {
        Address entity = addressService.queryById(id);
        //判断越权行为
        if (!entity.getUserId().equals(loginUser.getId())) {
            return toResponsObject(403, "您无权查看", "");
        }
        return toResponsSuccess(entity);
    }

    /**
     * 添加或更新收货地址
     */
    @ApiOperation(value = "添加或更新收货地址", response = Map.class)
    @PostMapping("/save")
    public Object save(@LoginUser User loginUser,@RequestBody Address entity) {
//    	 JSONObject addressJson = this.getJsonRequest();
//         Address entitys = new Address();
//         if (null != addressJson) {
////             entity.setId(addressJson.getLong("id"));
//             entity.setUserId(loginUser.getId());
//             entity.setUserName(addressJson.getString("userName"));
//             entity.setPostalCode(addressJson.getString("postalCode"));
//             entity.setProvinceName(addressJson.getString("provinceName"));
//             entity.setCityName(addressJson.getString("cityName"));
//             entity.setCountyName(addressJson.getString("countyName"));
//             entity.setDetailInfo(addressJson.getString("detailInfo"));
//             entity.setNationalCode(addressJson.getString("nationalCode"));
//             entity.setTelNumber(addressJson.getString("telNumber"));
////             entity.setIs_default(addressJson.getInteger("is_default"));
//         }
    	entity.setUserId(loginUser.getId());
        addressService.saveOrUpdate(entity);
        return toResponsSuccess(entity);
    }

    /**
     * 删除指定的收货地址
     */
    @ApiOperation(value = "删除指定的收货地址", response = Map.class)
    @PostMapping("/delete")
    public Object delete(@LoginUser User loginUser,@RequestBody Address address ) {
        Address entity = addressService.queryById(address.getId());
        //判断越权行为
        if (!entity.getUserId().equals(loginUser.getId())) {
            return toResponsObject(403, "您无权删除", "");
        }
        addressService.deleteById(address.getId());
        return toResponsSuccess("");
    }
}
