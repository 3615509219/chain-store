package com.lq.service.impl;

import com.lq.pojo.ImageTb;
import com.lq.dao.ImageTbMapper;
import com.lq.service.ImageTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.utils.DealFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-01-21
 */
@Service
public class ImageTbServiceImpl extends ServiceImpl<ImageTbMapper, ImageTb> implements ImageTbService {

    @Resource
    private ImageTbService imageTbService;

    @Resource
    private ImageTbMapper imageTbMapper;

    @Resource
    private DealFile dealFile;
    //获取全部图片
    @Override
    public List<ImageTb> getAllImage() {
        return imageTbService.list();
    }
    //图片所在
    @Override
    public List<ImageTb> imageAdd(Integer purchaseOrderProductPnId, String shopImage) {
        return imageTbMapper.imageAddr(purchaseOrderProductPnId,shopImage);
    }

    //上传图片
    @Override
    public String uploadImage(MultipartFile multipartFile, Integer purchaseOrderProductPnId, String shopImage) {
        ImageTb imageTb = new ImageTb();
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            imageTb.setPurchaseOrderProductPnId(purchaseOrderProductPnId);
            imageTb.setShopImage(shopImage);
            imageTb.setDrawingImageAddr(photoPath);
            imageTbService.save(imageTb);
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }
    //查询这个商品的图片
    @Override
    public List<ImageTb> getImage(Integer purchaseOrderProductPnId) {
        return imageTbMapper.geiImage(purchaseOrderProductPnId);
    }

    //一件修改页面说明
    @Override
    public void updateImage(MultipartFile multipartFile) {
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            imageTbMapper.updateImage(photoPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
