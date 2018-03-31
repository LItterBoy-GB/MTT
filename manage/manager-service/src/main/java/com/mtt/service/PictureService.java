package com.mtt.service;

import com.mtt.util.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
    PictureResult uploadFile(MultipartFile uploadFile);
}
