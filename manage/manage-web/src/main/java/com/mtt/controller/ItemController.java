package com.mtt.controller;

import com.mtt.service.ItemService;
import com.mtt.util.pojo.EasyUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
                                    @RequestParam(defaultValue="30")Integer rows) throws Exception {
        //查询商品列表
        EasyUIResult result = itemService.getItemList(page, rows);
        return result;
    }
}
