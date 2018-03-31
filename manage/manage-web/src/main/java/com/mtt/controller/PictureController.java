package com.mtt.controller;

import com.mtt.service.PictureService;
import com.mtt.util.pojo.PictureResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/pic")
@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @ResponseBody
    @RequestMapping("/upload")
    public PictureResult upload(MultipartFile uploadFile){
        return pictureService.uploadFile(uploadFile);
    }
}
