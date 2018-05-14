package com.mtt.service;

import com.mtt.pojo.TbItemParam;
import com.mtt.util.pojo.EasyUIResult;

public interface ItemParamService {
    TbItemParam queryCatalogById(long cid);

    EasyUIResult  getItemParamList(Integer page, Integer rows);
}
