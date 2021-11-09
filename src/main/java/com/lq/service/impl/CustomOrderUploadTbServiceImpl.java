package com.lq.service.impl;

import com.lq.pojo.CustomOrderUploadTb;
import com.lq.dao.CustomOrderUploadTbMapper;
import com.lq.service.CustomOrderUploadTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.utils.DealFile;
import org.springframework.stereotype.Service;
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
 * @since 2021-09-15
 */
@Service
public class CustomOrderUploadTbServiceImpl extends ServiceImpl<CustomOrderUploadTbMapper, CustomOrderUploadTb> implements CustomOrderUploadTbService {
@Resource
private DealFile dealFile;
@Resource
private CustomOrderUploadTbService customOrderUploadTbService;
    //定制上传文件
    @Override
    public void Upload(MultipartFile multipartFile, Integer accountId, Integer customOrderId, String customOrderUploadName, String customOrderUploadDescribe) {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (multipartFile != null){
            try {
                CustomOrderUploadTb customOrderUploadTb = new CustomOrderUploadTb();
                String photoPath = dealFile.getPhotoPath(multipartFile);
                customOrderUploadTb.setCustomOrderUploadAddr(photoPath);
                customOrderUploadTb.setAccountId(accountId);
                customOrderUploadTb.setDate(df.format(time));
                customOrderUploadTb.setCustomOrderUploadName(customOrderUploadName);
                customOrderUploadTb.setCustomOrderUploadDescribe(customOrderUploadDescribe);
                customOrderUploadTb.setCustomOrderId(customOrderId);
                customOrderUploadTbService.save(customOrderUploadTb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (multipartFile == null){
            try {
                CustomOrderUploadTb customOrderUploadTb = new CustomOrderUploadTb();
                customOrderUploadTb.setCustomOrderUploadAddr("null");
                customOrderUploadTb.setAccountId(accountId);
                customOrderUploadTb.setDate(df.format(time));
                customOrderUploadTb.setCustomOrderUploadName(customOrderUploadName);
                customOrderUploadTb.setCustomOrderUploadDescribe(customOrderUploadDescribe);
                customOrderUploadTb.setCustomOrderId(customOrderId);
                customOrderUploadTbService.save(customOrderUploadTb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
