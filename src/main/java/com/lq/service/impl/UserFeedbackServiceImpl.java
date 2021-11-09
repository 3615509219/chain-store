package com.lq.service.impl;

import com.lq.pojo.UserFeedback;
import com.lq.dao.UserFeedbackMapper;
import com.lq.service.UserFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.utils.DealFile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-17
 */
@Service
public class UserFeedbackServiceImpl extends ServiceImpl<UserFeedbackMapper, UserFeedback> implements UserFeedbackService {

    @Resource
    private DealFile dealFile;
    @Resource
    private UserFeedbackService userFeedbackService;
    //用户反馈Url
    @Override
    public void feedbackUrl(Integer accountId,MultipartFile multipartFile, String feedback, String proposal) {
        Date time = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserFeedback userFeedback = new UserFeedback();
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            userFeedback.setAccountId(accountId);
            userFeedback.setFeedbackUrl(photoPath);
            userFeedback.setProposal(proposal);
            userFeedback.setFeedback(feedback);
            userFeedback.setBz("待处理");
            userFeedback.setTime(df2.format(time));
            userFeedbackService.save(userFeedback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //用户反馈
    @Override
    public void feedback(Integer accountId, String feedback, String proposal) {
        Date time = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setAccountId(accountId);
        userFeedback.setProposal(proposal);
        userFeedback.setFeedback(feedback);
        userFeedback.setBz("待处理");
        userFeedback.setTime(df2.format(time));
        userFeedbackService.save(userFeedback);
    }
}
