package com.lq.service;

import com.lq.pojo.UserFeedback;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-17
 */
public interface UserFeedbackService extends IService<UserFeedback> {

    //用户反馈Url
    void feedbackUrl (Integer accountId, @RequestParam(value = "file",required = false) MultipartFile multipartFile,String feedback,String proposal);

    //用户反馈
    void feedback (Integer accountId,String feedback,String proposal);
}
