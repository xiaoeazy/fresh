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
import com.fresh.manager.pojo.shop.Feedback;
import com.fresh.manager.shop.service.IFeedbackService;


@RestController
@RequestMapping("/feedback")
public class FeedbackController  extends BaseController {
    @Autowired
    private IFeedbackService feedbackService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("feedback:list")
    public R list(Feedback feedback,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
    	 List<Feedback> userList = feedbackService.queryList(feedback, page, limit);
         int total = (int) feedbackService.queryTotal(feedback);
         PageUtils pageUtil = new PageUtils(userList, total, limit, page);
         return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{msgId}")
    @RequiresPermissions("feedback:info")
    public R info(@PathVariable("msgId") Integer msgId) {
        Feedback feedback = feedbackService.queryById(msgId);
        return R.ok().put("feedback", feedback);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("feedback:save")
    public R save(@RequestBody Feedback feedback) {
        feedbackService.insertSelective(feedback);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("feedback:update")
    public R update(@RequestBody Feedback feedback) {
        feedbackService.updateByPrimaryKeySelective(feedback);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("feedback:delete")
    public R delete(@RequestBody Integer[] msgIds) {
    	List<Integer> values = Arrays.asList(msgIds);
        feedbackService.deleteBatch(values);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(Feedback feedback) {
        List<Feedback> list = feedbackService.queryList(feedback,null,null);
        return R.ok().put("list", list);
    }
}
