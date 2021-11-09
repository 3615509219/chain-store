package com.lq.service.impl;

import com.lq.pojo.RefundImgTb;
import com.lq.dao.RefundImgTbMapper;
import com.lq.service.RefundImgTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.utils.DealFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-06-25
 */
@Service
public class RefundImgTbServiceImpl extends ServiceImpl<RefundImgTbMapper, RefundImgTb> implements RefundImgTbService {
    @Resource
    private DealFile dealFile;
    @Resource
    private RefundImgTbService refundImgTbService;

    @Resource
    private RefundImgTbMapper refundImgTbMapper;

    //添加售后图片
    @Override
    public void UploadRefundImg(MultipartFile multipartFile, Integer refundId) {
        if (multipartFile != null) {
            try {
                RefundImgTb refundImgTb = new RefundImgTb();
                String photoPath = dealFile.getPhotoPath(multipartFile);
                refundImgTb.setRefundId(refundId);
                refundImgTb.setRefundImg(photoPath);
                refundImgTbService.save(refundImgTb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (multipartFile == null) {
            RefundImgTb refundImgTb = new RefundImgTb();
            refundImgTb.setRefundId(refundId);
            refundImgTb.setRefundImg("null");
            refundImgTbService.save(refundImgTb);
        }
    }

    //查看售后上传的图片
    @Override
    public List<RefundImgTb> selectRefundImg(Integer refundId) {
        return refundImgTbMapper.selectRefundImg(refundId);
    }
}
