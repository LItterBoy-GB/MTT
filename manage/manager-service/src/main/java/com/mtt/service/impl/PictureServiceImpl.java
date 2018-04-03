package com.mtt.service.impl;

import com.mtt.service.PictureService;
import com.mtt.util.FtpUtil;
import com.mtt.util.IDUtil;
import com.mtt.util.pojo.PictureResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PictureServiceImpl implements PictureService {

    private static Logger logger = Logger.getLogger(PictureServiceImpl.class.getName());

    @Value("${FILE_UPLOAD_PATH}")
    private String fileUploadPath;

    @Value("${IMAGE_BASE_URL}")
    private String imageBaseUrl;

    @Value("${FTP_SERVER_IP}")
    private String FTP_SERVER_IP;

    @Value("${FTP_SERVER_PORT}")
    private Integer FTP_SERVER_PORT;

    @Value("${FTP_SERVER_USERNAME}")
    private String FTP_SERVER_USERNAME;

    @Value("${FTP_SERVER_PASSWORD}")
    private String FTP_SERVER_PASSWORD;

    @Override
    public PictureResult uploadFile(MultipartFile uploadFile) {

        String path = saveFile(uploadFile);
        if(path==null)
            return new PictureResult(1,imageBaseUrl+path,"保存失败");
        else
            return new PictureResult(0,imageBaseUrl+path,"");
    }

    private String saveFile(MultipartFile uploadFile){
        String filePath = new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
        String originFileName = uploadFile.getOriginalFilename();
        logger.debug(originFileName);
        String newFileName = IDUtil.genImageID() + originFileName.substring(originFileName.lastIndexOf("."));
        try {
            FtpUtil.uploadFile(FTP_SERVER_IP,FTP_SERVER_PORT,FTP_SERVER_USERNAME,FTP_SERVER_PASSWORD,
                    fileUploadPath+filePath,newFileName,uploadFile.getInputStream());
            return filePath+newFileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
