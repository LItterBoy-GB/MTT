package com.mtt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtt.mapper.TbItemDescMapper;
import com.mtt.mapper.TbItemMapper;
import com.mtt.pojo.TbItem;
import com.mtt.pojo.TbItemDesc;
import com.mtt.pojo.TbItemExample;
import com.mtt.service.ItemService;
import com.mtt.util.IDUtil;
import com.mtt.util.pojo.EasyUIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ItemServiceImpl.class.getName());

    @Override
    public EasyUIResult getItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();
        //设置分页
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        EasyUIResult result = new EasyUIResult(total, list);

        return result;

    }

    @Override
    public void saveItem(TbItem item, String desc, String itemParams) {
        Long id = IDUtil.genItemID();
        //添加商品信息
        //设置商品id
        item.setId(id);
        //设置商品状态
        item.setStatus((byte) 1);
        //设置时间
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        itemMapper.insert(item);

        //商品描述
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(id);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        itemDescMapper.insert(itemDesc);
    }

    @Override
    public void updateItem(TbItem item, String desc, String itemParams) {
        //设置时间
        TbItem tbItem = itemMapper.selectByPrimaryKey(item.getId());
        Date date = new Date();
        item.setStatus(tbItem.getStatus());
        item.setCreated(tbItem.getCreated());
        item.setUpdated(date);
        itemMapper.updateByPrimaryKey(item);

        TbItemDesc tbItemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemDesc(desc);
        itemDescMapper.updateByPrimaryKey(tbItemDesc);
    }

    @Override
    public TbItemDesc queryItemDescById(long ID) {
        return itemDescMapper.selectByPrimaryKey(ID);
    }
}
