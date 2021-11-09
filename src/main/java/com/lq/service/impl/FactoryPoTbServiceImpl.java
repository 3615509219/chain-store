package com.lq.service.impl;

import com.lq.dao.ManufactureCartTbMapper;
import com.lq.pojo.*;
import com.lq.dao.FactoryPoTbMapper;
import com.lq.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-04-15
 */
@Service
public class FactoryPoTbServiceImpl extends ServiceImpl<FactoryPoTbMapper, FactoryPoTb> implements FactoryPoTbService {

    @Resource
    private FactoryPoTbMapper factoryPoTbMapper;
    @Resource
    private ManufactureRegisterTbService manufactureRegisterTbService;
    @Resource
    private ManufactureReceiverTbService manufactureReceiverTbService;
    @Resource
    private ManufactureCartTbMapper manufactureCartTbMapper;
    @Resource
    private FactoryPoTbService factoryPoTbService;
    @Resource
    private ManufactureShopTbService manufactureShopTbService;
    @Resource
    private ManufactureProductTbService manufactureProductTbService;

    //生成订单
    @Override
    @Transactional
    public int addFactoryPo(Integer manufactureId, Integer manufactureReceiverId, Integer factoryPrice) {
        FactoryPoTb factoryPoTb = new FactoryPoTb();
        ManufactureProductTb manufactureProductTb = new ManufactureProductTb();
        //获取最近一次的订单号
        String factoryPo = factoryPoTbMapper.factoryPo();
        //获取某个人
        ManufactureRegisterTb manufactureRegisterTb = manufactureRegisterTbService.getById(manufactureId);
        //获取某个地址
        ManufactureReceiverTb manufactureReceiverTb = manufactureReceiverTbService.getById(manufactureReceiverId);
        //获取某个人打勾的购物车
        List<ManufactureCartTb> manufactureCartTbs = manufactureCartTbMapper.getAccountCart(manufactureId);
        Integer result = Integer.valueOf(factoryPo.substring(factoryPo.length() - 5));
        int num = result + 1;
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Lqp = manufactureRegisterTb.getManufactureCode();
        String purchaseOrderNumber1 = Lqp + df.format(time) + num;
        factoryPoTb.setFactoryPo(purchaseOrderNumber1);
        factoryPoTb.setFactoryName(manufactureRegisterTb.getManufactureWxName());
        factoryPoTb.setFactoryTime(df2.format(time));
        factoryPoTb.setFactoryPrice(factoryPrice);
        factoryPoTb.setAddressOfConsignee(manufactureReceiverTb.getManufactureReceiverAddr() + manufactureReceiverTb.getManufactureDetailsAddr());
        factoryPoTb.setConsignee(manufactureReceiverTb.getManufactureReceiverName());
        factoryPoTb.setTelephone(manufactureReceiverTb.getManufactureReceiverPhone());
        List<String> list = Lists.newArrayList();
        for (ManufactureCartTb c : manufactureCartTbs) {
            list.add(c.getShopName());
        }
        factoryPoTb.setOrderStatus(String.join(",", list));
        factoryPoTb.setOrderType("待支付");
        factoryPoTb.setManufactureId(manufactureId);
        factoryPoTb.setPayTime(df2.format(time));
        factoryPoTbService.save(factoryPoTb);
        //添加到物品清单
        for (ManufactureCartTb m : manufactureCartTbs) {
            ManufactureShopTb manufactureShopTb = manufactureShopTbService.getById(m.getShopId());
            manufactureProductTb.setFactoryPo(purchaseOrderNumber1);
            manufactureProductTb.setManufacturer(manufactureShopTb.getManufacturer());
            manufactureProductTb.setProductPn(manufactureShopTb.getShopPn());
            manufactureProductTb.setCategory(manufactureShopTb.getCategory());
            manufactureProductTb.setProductName(manufactureShopTb.getShopName());
            manufactureProductTb.setProductDescribe(manufactureShopTb.getShopDescribe());
            manufactureProductTb.setColour(manufactureShopTb.getColour());
            manufactureProductTb.setTexture(manufactureShopTb.getTexture());
            manufactureProductTb.setDensity(manufactureShopTb.getDensity());
            manufactureProductTb.setAccuracy(manufactureShopTb.getAccuracy());
            manufactureProductTb.setParticleSize(manufactureShopTb.getParticleSize());
            manufactureProductTb.setSize(manufactureShopTb.getSize());
            manufactureProductTb.setImgAddr(manufactureShopTb.getImgAddr());
            manufactureProductTb.setRemarks(manufactureShopTb.getRemarks());
            manufactureProductTb.setSellingPrice(manufactureShopTb.getSellingPrice());
            manufactureProductTbService.save(manufactureProductTb);
        }
        //删除购物车
        for (ManufactureCartTb cart : manufactureCartTbs) {
            manufactureCartTbMapper.delCart(cart.getManufactureCartId(), manufactureId);
        }
        return factoryPoTb.getFactoryPoId();
    }

    //待发货
    @Override
    public List<FactoryPoTb> ToBeDelivered(Integer manufactureId) {
        return factoryPoTbMapper.ToBeDelivered(manufactureId);
    }

    //已发货
    @Override
    public List<FactoryPoTb> Delivered(Integer manufactureId) {
        return factoryPoTbMapper.Delivered(manufactureId);
    }

    //待支付
    @Override
    public List<FactoryPoTb> ToBePaid(Integer manufactureId) {
        return factoryPoTbMapper.ToBePaid(manufactureId);
    }
}
