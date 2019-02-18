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
import com.fresh.manager.pojo.shop.Channel;
import com.fresh.manager.shop.service.IChannelService;


@RestController
@RequestMapping("/channel")
public class ChannelController  extends BaseController {
    @Autowired
    private IChannelService channelService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("channel:list")
    public R list(Channel channel,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
        List<Channel> userList = channelService.queryList(channel, page, limit);
        int total = (int) channelService.queryTotal(channel);
        PageUtils pageUtil = new PageUtils(userList, total, limit, page);
        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("channel:info")
    public R info(@PathVariable("id") Integer id) {
        Channel channel = channelService.queryById(id);

        return R.ok().put("channel", channel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("channel:save")
    public R save(@RequestBody Channel channel) {
        channelService.insertSelective(channel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("channel:update")
    public R update(@RequestBody Channel channel) {
        channelService.updateByPrimaryKeySelective(channel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("channel:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
    	channelService.deleteBatch(values);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(Channel channel) {

        List<Channel> list =  channelService.queryList(channel, null, null);

        return R.ok().put("list", list);
    }
}
