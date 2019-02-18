package com.fresh.manager.shop.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.common.utils.PageUtils;
import com.fresh.common.utils.R;
import com.fresh.manager.controllers.BaseController;
import com.fresh.manager.pojo.shop.Group;
import com.fresh.manager.shop.service.IGroupGoodsService;
import com.fresh.manager.shop.service.IGroupService;

@RestController
@RequestMapping("group")
public class GroupController  extends BaseController {
    @Autowired
    private IGroupService groupService;
    @Autowired
    private IGroupGoodsService groupGoodsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("group:list")
    public R list(Group group,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	  List<Group> groupList = groupService.queryList(group, page, limit);
          int total = (int) groupService.queryTotal(group);
          PageUtils pageUtil = new PageUtils(groupList, total, limit, page);
          return R.ok().put("page", pageUtil);
      }
    
    
    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("group:info")
    public R info(@PathVariable("id") Integer id) {
        Group group = groupService.queryById(id);

        return R.ok().put("group", group);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("group:save")
    public R save(@RequestBody Group group) {
    	group.setCreateUserId(getUserId());
    	 
        groupService.save(group);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("group:update")
    public R update(@RequestBody Group group) {
        groupService.update(group);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("group:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        groupService.deleteBatch(values);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(Group group) {
        List<Group> list = groupService.queryList(group, null, null);
        return R.ok().put("list", list);
    }
}
