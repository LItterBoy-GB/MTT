package com.mtt.service;

import com.mtt.util.pojo.EasyUIResult;

public interface ItemService {
	EasyUIResult getItemList(int page, int rows);
}
