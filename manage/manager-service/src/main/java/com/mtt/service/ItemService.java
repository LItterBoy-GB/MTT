package com.mtt.service;

import com.mtt.pojo.TbItem;
import com.mtt.pojo.TbItemDesc;
import com.mtt.util.pojo.EasyUIResult;
import org.springframework.stereotype.Service;

public interface ItemService {
	EasyUIResult getItemList(int page, int rows);
	void saveItem(TbItem item, String desc, String itemParams);
	void updateItem(TbItem item, String desc, String itemParams);
	TbItemDesc queryItemDescById(long ID);
}
