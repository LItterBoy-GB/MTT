package com.mtt.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtt.mapper.TbItemParamMapper;
import com.mtt.pojo.TbItemParam;
import com.mtt.pojo.TbItemParamExample;
import com.mtt.service.ItemParamService;
import com.mtt.util.pojo.EasyUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    TbItemParamMapper itemParamMapper;

    @Override
    public TbItemParam queryCatalogById(long cid) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        //如果查询中有结果返回查询结果
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        //查询成功但是没有查到数据
        return null;
    }

    @Override
    public EasyUIResult getItemParamList(Integer page, Integer rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        //查询规格列表
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
        //取分页信息
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        //返回结果
        return new EasyUIResult(pageInfo.getTotal(), list);


    }
}
