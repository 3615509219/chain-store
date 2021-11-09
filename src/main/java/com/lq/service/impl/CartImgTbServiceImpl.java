package com.lq.service.impl;

import com.lq.dao.CustomerCartTbMapper;
import com.lq.pojo.CartImgTb;
import com.lq.dao.CartImgTbMapper;
import com.lq.service.CartImgTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.utils.DealFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-25
 */
@Service
public class CartImgTbServiceImpl extends ServiceImpl<CartImgTbMapper, CartImgTb> implements CartImgTbService {

    @Resource
    private CustomerCartTbMapper customerCartTbMapper;
    @Resource
    private CartImgTbService cartImgTbService;
    @Resource
    private DealFile dealFile;
    //添加购物车图片
    @Override
    public void addCartImg(MultipartFile multipartFile,Integer customerCartID,String comment) {
        try {
            CartImgTb cartImgTb = new CartImgTb();
            String photoPath = dealFile.getPhotoPath(multipartFile);
            cartImgTb.setCustomerCartId(customerCartID);
            cartImgTb.setCartImg(photoPath);
            cartImgTb.setCommentBz(comment);
            cartImgTbService.save(cartImgTb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
