package com.mtt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtt.mapper.TbItemMapper;
import com.mtt.pojo.TbItem;
import com.mtt.service.ItemService;
import com.mtt.util.pojo.EasyUIResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public EasyUIResult getItemList(int page, int rows) {
        //设置分页
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectAll();
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        EasyUIResult result = new EasyUIResult(total, list);

        return result;
    }
}
