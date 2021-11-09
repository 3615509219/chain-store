package com.lq.service;

import com.lq.pojo.RefundImgTb;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-06-25
 */
public interface RefundImgTbService extends IService<RefundImgTb> {
    //增加上传文件
    void UploadRefundImg(@RequestParam(value = "file",required = false) MultipartFile multipartFile,Integer refundId);

    List<RefundImgTb> selectRefundImg (Integer refundId);
}
