package com.mtt.controller;

import com.mtt.service.ItemParamService;
import com.mtt.util.pojo.EasyUIResult;
import com.mtt.util.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    ItemParamService itemParamService;

    @RequestMapping("/query/itemcatid/{id}")
    @ResponseBody
    public TaotaoResult queryCatalogById(@PathVariable("id") long cid){
        //根据分类id查询列表
        return TaotaoResult.ok(itemParamService.queryCatalogById(cid));
    }

    @RequestMapping("/list")
    @ResponseBody
    public EasyUIResult getItemParamList(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "30") Integer rows){
        //查询列表
        return itemParamService.getItemParamList(page, rows);
    }
}
