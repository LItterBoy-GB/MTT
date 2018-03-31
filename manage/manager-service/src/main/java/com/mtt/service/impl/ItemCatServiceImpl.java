package com.mtt.service.impl;

import com.mtt.mapper.TbItemCatMapper;
import com.mtt.pojo.TbItemCat;
import com.mtt.pojo.TbItemCatExample;
import com.mtt.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<TbItemCat> getItemCatList(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        //根据parentid查询子节点
        criteria.andParentIdEqualTo(parentId);
        //返回子节点列表
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        return list;

    }
}
