package com.mtt.service;

import com.mtt.util.pojo.EasyUIResult;
import org.springframework.stereotype.Service;

public interface ItemService {
	EasyUIResult getItemList(int page, int rows);
}
