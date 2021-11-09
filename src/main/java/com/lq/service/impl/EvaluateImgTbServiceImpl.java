package com.lq.service.impl;

import com.lq.dao.EvaluateTbMapper;
import com.lq.pojo.EvaluateImgTb;
import com.lq.dao.EvaluateImgTbMapper;
import com.lq.service.EvaluateImgTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.utils.DealFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-24
 */
@Service
public class EvaluateImgTbServiceImpl extends ServiceImpl<EvaluateImgTbMapper, EvaluateImgTb> implements EvaluateImgTbService {
    @Resource
    private EvaluateTbMapper evaluateTbMapper;


    @Resource
    private DealFile dealFile;
    @Resource
    private EvaluateImgTbService evaluateImgTbService;

    //增加评论图片
    @Override
    public void addImg(MultipartFile multipartFile,Integer evaluateId) {
        try {
            EvaluateImgTb evaluateImgTb1 = new EvaluateImgTb();
            if (multipartFile == null){
                evaluateImgTb1.setEvaluateId(evaluateId);
                evaluateImgTb1.setEvaluateImg(null);
                evaluateImgTbService.save(evaluateImgTb1);
            }if (multipartFile != null){
                String photoPath1 = dealFile.getPhotoPath(multipartFile);
                evaluateImgTb1.setEvaluateId(evaluateId);
                evaluateImgTb1.setEvaluateImg(photoPath1);
                evaluateImgTbService.save(evaluateImgTb1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
