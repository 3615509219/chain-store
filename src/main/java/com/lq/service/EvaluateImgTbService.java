package com.lq.service;

import com.lq.pojo.EvaluateImgTb;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-24
 */
public interface EvaluateImgTbService extends IService<EvaluateImgTb> {


    //增加评论图片
    void addImg (@RequestParam(value = "file", required = false) MultipartFile multipartFile,Integer evaluateId);
}
