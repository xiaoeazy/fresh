package com.fresh.api.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.api.annotation.LoginUser;
import com.fresh.api.exception.CommonException;
import com.fresh.api.pojo.Goods;
import com.fresh.api.pojo.Groupcolonel;
import com.fresh.api.pojo.Groupgoods;
import com.fresh.api.service.IApiGroupconlonelService;
import com.fresh.api.service.IApiGroupgoodsService;
import com.fresh.api.util.ApiBaseAction;
import com.fresh.manager.pojo.shop.Address;
import com.fresh.manager.pojo.shop.User;
import com.fresh.manager.shop.service.IAddressService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/validate")
public class ApiValidateController extends ApiBaseAction {

	@Autowired
	private IAddressService addressService;
	@Autowired
	private IApiGroupconlonelService groupconlonelService;
	@Autowired
	private IApiGroupgoodsService groupgoodsService;

	@ApiOperation(value = "验证商品")
	@PostMapping("/validateSelectGroupGoods")
	public Object validateGroupGoods(@LoginUser User loginUser, Integer colonelGroupId,
			@RequestBody List<Goods> goodsList) {
		Map<String, Object> resultObj = new HashMap();

		try {
			// 默认收货地址
			Address address = new Address();
			address.setUserId(loginUser.getId());
			List<Address> useraddress = addressService.queryList(address,null,null);
			// 商品总价
			BigDecimal goodsTotalPrice = new BigDecimal(0.00);
			if (null == useraddress || useraddress.size() == 0) {
				resultObj.put("checkedAddress", new Address());
			} else {
				Address defalut = address.defaultAddress(useraddress);
				resultObj.put("checkedAddress", defalut);
			}
			Groupcolonel groupcolonel  = groupconlonelService.queryById(colonelGroupId);
			if(groupcolonel==null){
				throw new CommonException("该团购已经不存在了！");
			}
			if(groupcolonel.getColonelGroupType()==1){
				resultObj.put("isCustomerPickUp", true);
			}else{
				resultObj.put("isCustomerPickUp", false);
			}
			Date endTime = groupcolonel.getGroupEndTime();
			int i = endTime.compareTo(new Date());
			if(i<0){
				throw new CommonException("该团购已经结束");
			}
			for (Goods gg : goodsList) {
				Groupgoods selectgg = new Groupgoods();
				selectgg.setGroupId(groupcolonel.getGroupId());
				selectgg.setGoodsId(gg.getId());
				Groupgoods groupgoods = groupgoodsService.queryByIdWithGoodsInfo(selectgg);
				if (groupgoods == null)
					throw new CommonException("该物品已经不再该团购列表中了！");
				goodsTotalPrice =goodsTotalPrice.add(groupgoods.getGoods().getRetailPrice().multiply(new BigDecimal(gg.getBuyNum()))) ;
			}
			// 订单总价
			BigDecimal freightPrice = new BigDecimal(0.00); // 快递费用
			BigDecimal orderTotalPrice = goodsTotalPrice.add(freightPrice);

			BigDecimal couponPrice = new BigDecimal(0.00); // 优惠券金额
			BigDecimal actualPrice = orderTotalPrice.subtract(couponPrice); // 减去其它支付的金额后，要实际支付的金额

			resultObj.put("freightPrice", freightPrice);

			resultObj.put("couponPrice", couponPrice);
			resultObj.put("checkedGoodsList", goodsList);
			resultObj.put("goodsTotalPrice", goodsTotalPrice);
			resultObj.put("orderTotalPrice", orderTotalPrice);
			resultObj.put("actualPrice", actualPrice);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return toResponsFail("验证失败");
		}
		return toResponsSuccess(resultObj);
	}
}
