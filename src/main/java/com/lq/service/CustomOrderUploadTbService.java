package com.lq.service;

import com.lq.pojo.CustomOrderUploadTb;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-09-15
 */
public interface CustomOrderUploadTbService extends IService<CustomOrderUploadTb> {

    //定制上传文件
    void Upload(@RequestParam(value = "file",required = false) MultipartFile multipartFile, Integer accountId, Integer customOrderId,String customOrderUploadName,String customOrderUploadDescribe);

}
