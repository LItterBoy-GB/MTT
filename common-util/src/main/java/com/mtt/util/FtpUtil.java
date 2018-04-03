package com.mtt.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class FtpUtil {
    private static Logger log = Logger.getLogger(FtpUtil.class);
    public static void uploadFile(String ip,int port, String user, String passwd,String filePath,String fileName,InputStream in) throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(ip,port);
        ftpClient.login(user,passwd);
        if(!CreateDirecroty(ftpClient,filePath)){
            throw new Exception("创建目录失败");
        }
        ftpClient.makeDirectory(filePath);
        ftpClient.changeWorkingDirectory(filePath);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        if(!ftpClient.storeFile(fileName,in)) {
            throw new Exception("上传失败！");
        }
        in.close();
    }
    //判断ftp服务器文件是否存在
    private static boolean existFile(FTPClient ftpClient,String path) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }
    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    private static boolean CreateDirecroty(FTPClient ftpClient,String remote) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(new String(directory))) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(ftpClient,path)) {
                    if (ftpClient.makeDirectory(subDirectory)) {
                        ftpClient.changeWorkingDirectory(subDirectory);
                    } else {
                        System.out.println("创建目录[" + subDirectory + "]失败");
                        success = false;
                        break;
                    }
                } else {
                    ftpClient.changeWorkingDirectory(subDirectory);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }
}