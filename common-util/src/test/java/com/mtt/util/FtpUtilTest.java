package com.mtt.util;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FtpUtilTest extends TestCase {

    public void testUploadFile() throws Exception {
        String FILE_UPLOAD_PATH="/home/ftpuser/www/images/"+new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
        String FTP_SERVER_IP="192.168.247.130";
        int FTP_SERVER_PORT=21;
        String FTP_SERVER_USERNAME="ftpuser";
        String FTP_SERVER_PASSWORD="gb951124";
        File f = new File("C:\\Users\\14183\\Pictures\\Saved Pictures\\1.jpg");
        String newFileName = IDUtil.genImageID() + f.getName().substring(f.getName().lastIndexOf("."));
        FileInputStream in = new FileInputStream(f);
        FtpUtil.uploadFile(FTP_SERVER_IP,FTP_SERVER_PORT,FTP_SERVER_USERNAME,
                FTP_SERVER_PASSWORD,FILE_UPLOAD_PATH,newFileName,in);
    }
}