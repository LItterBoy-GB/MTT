package com.mtt.service;

import com.mtt.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {
    public List<TbItemCat> getItemCatList(Long parentId);
}
