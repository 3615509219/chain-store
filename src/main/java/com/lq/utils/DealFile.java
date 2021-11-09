package com.lq.utils;

import com.lq.pojo.my.FastDFSFile;
import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.TrackerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Slf4j
@Component
public class DealFile {

    public String getPhotoPath(MultipartFile multipartFile) throws Exception{
        String [] fileAbsolutePath = {};
        //获取文件名
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if (inputStream != null){
            int len = inputStream.available();
            file_buff = new byte[len];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName,file_buff,ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);
        } catch (Exception e){
            log.error("upload file eption!Exc",e);
        }
        if (fileAbsolutePath == null){
            log.error("upload file failed,please upload again!");
        }
        String path = FastDFSClient.getTrackerUrl()+"/"+fileAbsolutePath[0]+"/"+fileAbsolutePath[1];
        log.info(path+"------------------");
        return path;
    }

}
