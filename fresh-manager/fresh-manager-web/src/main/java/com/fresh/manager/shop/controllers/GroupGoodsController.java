package com.fresh.manager.shop.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.common.utils.PageUtils;
import com.fresh.common.utils.R;
import com.fresh.manager.controllers.BaseController;
import com.fresh.manager.pojo.shop.Groupgoods;
import com.fresh.manager.shop.service.IGroupGoodsService;


@RestController
@RequestMapping("/groupgoods")
public class GroupGoodsController   extends BaseController {
    @Autowired
    private IGroupGoodsService groupGoodsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("groupgoods:list")
    public R list(Groupgoods groupgoods,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
        List<Groupgoods> userList = groupGoodsService.queryList(groupgoods, page, limit);
        int total = (int) groupGoodsService.queryTotal(groupgoods);
        PageUtils pageUtil = new PageUtils(userList, total, limit, page);
        return R.ok().put("page", pageUtil);
    }
    
    @RequestMapping("/listByGroupId")
//  @RequiresPermissions("groupgoods:list")
   public R listByGroupId(Groupgoods obj) {
      List<Groupgoods> groupGoodsList = groupGoodsService.queryListWithGoodsInfo(obj);
      return R.ok().put("list", groupGoodsList);
   }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("groupgoods:info")
    public R info(@PathVariable("id") Integer id) {
        Groupgoods groupGoods = groupGoodsService.queryById(id);
        return R.ok().put("groupGoods", groupGoods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("groupgoods:save")
    public R save(@RequestBody Groupgoods groupGoods) {
        groupGoodsService.insertSelective(groupGoods);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("groupgoods:update")
    public R update(@RequestBody Groupgoods groupGoods) {
        groupGoodsService.updateByPrimaryKey(groupGoods);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("groupgoods:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        groupGoodsService.deleteBatch(values);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(Groupgoods groupGoods) {
        List<Groupgoods> list = groupGoodsService.queryList(groupGoods,null,null);
        return R.ok().put("list", list);
    }
}
