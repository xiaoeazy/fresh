package com.fresh.manager.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 通用的 Controller,用来获取公共数据.
 *
 * @author shengyang.zhou@hand-china.com
 * @author njq.niu@hand-china.com
 */
@RestController
public class CommonController  {


    @RequestMapping(value = { "/{folder1}/{name}.html", "/{folder1}/{name}.view" })
    public ModelAndView renderFolder1View(@PathVariable String folder1, @PathVariable String name, Model model) {
        return new ModelAndView(
                new StringBuilder().append("/").append(folder1).append("/").append(name).toString());
    }

    @RequestMapping(value = { "/{folder1}/{folder2}/{name}.html", "/{folder1}/{folder2}/{name}.view" })
    public ModelAndView renderFolder2View(@PathVariable String folder1, @PathVariable String folder2,
            @PathVariable String name, Model model) {
        return new ModelAndView(new StringBuilder().append("/").append(folder1).append("/").append(folder2)
                .append("/").append(name).toString());
    }

    @RequestMapping(value = { "/{folder1}/{folder2}/{folder3}/{name}.html",
            "/{folder1}/{folder2}/{folder3}/{name}.view" })
    public ModelAndView renderFolder3View(@PathVariable String folder1, @PathVariable String folder2,
            @PathVariable String folder3, @PathVariable String name, Model model) {
        return new ModelAndView(new StringBuilder().append("/").append(folder1).append("/").append(folder2)
                .append("/").append(folder3).append("/").append(name).toString());
    }

    @RequestMapping(value = { "/{folder1}/{folder2}/{folder3}/{folder4}/{name}.html",
            "/{folder1}/{folder2}/{folder3}/{folder4}/{name}.view" })
    public ModelAndView renderFolder4View(@PathVariable String folder1, @PathVariable String folder2,
            @PathVariable String folder3, @PathVariable String folder4, @PathVariable String name, Model model) {
        return new ModelAndView(new StringBuilder().append("/").append(folder1).append("/").append(folder2)
                .append("/").append(folder3).append("/").append(folder4).append("/").append(name).toString());
    }


}
