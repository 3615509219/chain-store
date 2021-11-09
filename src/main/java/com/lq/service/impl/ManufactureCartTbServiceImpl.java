package com.lq.service.impl;

import com.lq.pojo.CustomerCartTb;
import com.lq.pojo.ManufactureCartTb;
import com.lq.dao.ManufactureCartTbMapper;
import com.lq.pojo.ManufactureShopTb;
import com.lq.service.ManufactureCartTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.service.ManufactureShopTbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-04-15
 */
@Service
public class ManufactureCartTbServiceImpl extends ServiceImpl<ManufactureCartTbMapper, ManufactureCartTb> implements ManufactureCartTbService {

    @Resource
    private ManufactureCartTbMapper manufactureCartTbMapper;
    @Resource
    private ManufactureShopTbService manufactureShopTbService;
    @Resource
    private ManufactureCartTbService manufactureCartTbService;
    //获取工厂购物车
    @Override
    public List<ManufactureCartTb> allCart(Integer manufactureId) {
        return manufactureCartTbMapper.allCart(manufactureId);
    }
    //删除购物车
    @Override
    public void delCart(Integer manufactureCartId, Integer manufactureId) {
        manufactureCartTbMapper.delCart(manufactureCartId, manufactureId);
    }
    //删除购物车
    @Override
    public void reduceManufactureCart(Integer manufactureCartId, Integer manufactureId) {
        ManufactureCartTb manufactureCartTb = manufactureCartTbMapper.allManufactureCart(manufactureCartId, manufactureId);
        if (manufactureCartTb.getNumber() == 1) {
            manufactureCartTbMapper.delCart(manufactureCartId, manufactureId);
        } else {
            manufactureCartTbMapper.updateCartNum(manufactureCartTb.getNumber() - 1, manufactureCartId, manufactureId);
        }
    }
    //增加购物车
    @Override
    public void addManufactureCart(Integer manufactureCartId, Integer manufactureId) {
        ManufactureCartTb manufactureCartTb = manufactureCartTbMapper.allManufactureCart(manufactureCartId, manufactureId);
        manufactureCartTbMapper.updateCartNum(manufactureCartTb.getNumber() + 1, manufactureCartId, manufactureId);
    }
    @Override
    public void choose(Integer manufactureCartId) {
        manufactureCartTbMapper.updateChoose(manufactureCartId);
    }

    @Override
    public void cancelChoose(Integer manufactureCartId) {
        manufactureCartTbMapper.updateCancelChoose(manufactureCartId);
    }

    @Override
    public void allChoose(Integer manufactureId) {
        manufactureCartTbMapper.updateAllChoose(manufactureId);
    }

    @Override
    public void cancelAllChoose(Integer manufactureId) {
        manufactureCartTbMapper.updateCancelAllChoose(manufactureId);
    }

    @Override
    public List<ManufactureCartTb> getStatus(Integer manufactureId) {
        return manufactureCartTbMapper.getAccountCart(manufactureId);
    }
    //加入购物车
    @Override
    public void manufactureCart(Integer manufactureId, Integer shopId, Integer number, Integer status) {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ManufactureShopTb manufactureShopTb = manufactureShopTbService.getById(shopId);
        ManufactureCartTb manufactureCartTb = new ManufactureCartTb();
        manufactureCartTb.setManufactureId(manufactureId);
        manufactureCartTb.setCategory(manufactureShopTb.getCategory());
        manufactureCartTb.setImgAddr(manufactureShopTb.getImgAddr());
        manufactureCartTb.setRemarks(manufactureShopTb.getRemarks());
        manufactureCartTb.setNumber(number);
        manufactureCartTb.setSellingPrice(manufactureShopTb.getSellingPrice());
        manufactureCartTb.setShopDescribe(manufactureShopTb.getShopDescribe());
        manufactureCartTb.setShopId(shopId);
        manufactureCartTb.setShopName(manufactureShopTb.getShopName());
        manufactureCartTb.setShopPn(manufactureShopTb.getShopPn());
        manufactureCartTb.setStatus(status);
        manufactureCartTb.setTime(df.format(time));
        manufactureCartTbService.save(manufactureCartTb);
    }
}
