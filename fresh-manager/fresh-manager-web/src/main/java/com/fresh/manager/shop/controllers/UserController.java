package com.fresh.manager.shop.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fresh.common.excel.ExcelExport;
import com.fresh.common.utils.PageUtils;
import com.fresh.common.utils.R;
import com.fresh.manager.controllers.BaseController;
import com.fresh.manager.pojo.shop.User;
import com.fresh.manager.shop.service.IUserService;


@RestController
@RequestMapping("/user")
public class UserController  extends BaseController {
    @Autowired
    private IUserService userService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:list")
    public R list(User user,@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page, 
    		@RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_SIZE) Integer limit) {
        List<User> userList = userService.queryList(user, page, limit);
        int total = (int) userService.queryTotal(user);
        PageUtils pageUtil = new PageUtils(userList, total, limit, page);
        return R.ok().put("page", pageUtil);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("user:info")
    public R info(@PathVariable("id") Integer id) {
        User user = userService.queryById(id);
        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:save")
    public R save(@RequestBody User user) {
        userService.insertSelective(user);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:update")
    public R update(@RequestBody User user) {
        userService.updateByPrimaryKeySelective(user);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:delete")
    public R delete(@RequestBody Integer[] ids) {
    	List<Integer> values = Arrays.asList(ids);
        userService.deleteBatch(values);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(User user) {
        List<User> userList = userService.queryList(user, null, null);
        return R.ok().put("list", userList);
    }

    /**
     * 总计
     */
    @RequestMapping("/queryTotal")
    public R queryTotal(User user) {
        int sum = (int) userService.queryTotal(user);
        return R.ok().put("userSum", sum);
    }

    /**
     * 导出会员
     */
    @RequestMapping("/export")
    @RequiresPermissions("user:export")
    public R export(User user, HttpServletResponse response) {
        List<User> userList = userService.queryList(user, null, null);
        ExcelExport ee = new ExcelExport("会员列表");
        String[] header = new String[]{"会员名称", "性别", "会员级别", "手机号码"};
        List<Map<String, Object>> list = new ArrayList<>();
        if (userList != null && userList.size() != 0) {
            for (User User : userList) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                map.put("USERNAME", User.getUsername());
                map.put("GENDER", User.getGender() == 1 ? "男" : (User.getGender() == 2 ? "女" : "未知"));
                map.put("LEVEL_NAME", User.getLevelName());
                map.put("MOBILE", User.getMobile());
                list.add(map);
            }
        }
        ee.addSheetByMap("会员", list, header);
        ee.export(response);
        return R.ok();
    }
}
