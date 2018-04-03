package com.mtt.controller;

import com.mtt.pojo.TbItem;
import com.mtt.service.ItemService;
import com.mtt.util.pojo.EasyUIResult;
import com.mtt.util.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/list")
    //设置相应的内容为json数据
    @ResponseBody
    public EasyUIResult getItemlist(@RequestParam(defaultValue="1")Integer page,
                                    @RequestParam(defaultValue="30")Integer rows){
        //查询商品列表
        return itemService.getItemList(page, rows);
    }

    @RequestMapping("/query/item/desc/{id}")
    public TaotaoResult queryItemDesc(@PathVariable("id") Long id){
        return TaotaoResult.ok(itemService.queryItemDescById(id));
    }

    @RequestMapping("/save")
    //设置相应的内容为json数据
    @ResponseBody
    public TaotaoResult saveItem(TbItem item,String desc){
        itemService.saveItem(item,desc,null);
        return TaotaoResult.ok();
    }

    @RequestMapping("/update")
    //设置相应的内容为json数据
    @ResponseBody
    public TaotaoResult updateItem(TbItem item,String desc){
        itemService.updateItem(item,desc,null);
        return TaotaoResult.ok();
    }
}
