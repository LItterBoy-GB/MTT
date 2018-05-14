package com.mtt.service;

import com.mtt.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {
    List<TbItemCat> getItemCatList(Long parentId);
    TbItemCat getItemCatById(long id);
}
