package com.mtt.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class FtpUtil {
    private static Logger log = Logger.getLogger(FtpUtil.class);
    public static void uploadFile(String ip,int port, String user, String passwd,String filePath,String fileName,InputStream in) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(ip,port);
        ftpClient.login(user,passwd);
        ftpClient.makeDirectory(filePath);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        log.debug(filePath+fileName);
        if(!ftpClient.storeFile(filePath+fileName,in)) {
            log.debug("上传失败!");
        }
        in.close();
    }
}