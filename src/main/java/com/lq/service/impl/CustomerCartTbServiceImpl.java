package com.lq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.dao.CustomerCartTbMapper;
import com.lq.dao.FinishedGoodsListSubTbMapper;
import com.lq.dao.FinishedGoodsListTbMapper;
import com.lq.pojo.CustomerCartTb;
import com.lq.pojo.FinishedGoodsListSubTb;
import com.lq.pojo.FinishedGoodsListTb;
import com.lq.service.CustomerCartTbService;
import com.lq.service.FinishedGoodsListTbService;
import org.springframework.stereotype.Service;

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
 * @since 2021-01-13
 */
@Service
public class CustomerCartTbServiceImpl extends ServiceImpl<CustomerCartTbMapper, CustomerCartTb> implements CustomerCartTbService {
    @Resource
    private CustomerCartTbMapper customerCartTbMapper;
    @Resource
    private FinishedGoodsListTbService finishedGoodsListTbService;
    @Resource
    private CustomerCartTbService customerCartTbService;
    @Resource
    private FinishedGoodsListTbMapper finishedGoodsListTbMapper;
    @Resource
    private FinishedGoodsListSubTbMapper finishedGoodsListSubTbMapper;

    //获取购物车
    @Override
    public List<CustomerCartTb> getCart(Integer accountId) {
        List<CustomerCartTb> allCart = customerCartTbMapper.selectCart(accountId);
        for (CustomerCartTb cart : allCart) {
            FinishedGoodsListTb customerPrice = finishedGoodsListTbMapper.customerPrice(cart.getPurchaseOrderProductPn());
            if (customerPrice!=null){
                if (cart.getUnitPrice() != customerPrice.getFinishedGoodsCustomerPriced()) {
                    customerCartTbMapper.updatePrice(customerPrice.getFinishedGoodsCustomerPriced(), cart.getPurchaseOrderProductPnId());
                }
            }
        }
        List<CustomerCartTb> Cart = customerCartTbMapper.selectCart(accountId);
        return Cart;
    }

    //删除购物车
    @Override
    public void deiCart(Integer customerCartId, Integer accountId) {
        customerCartTbMapper.delCart(customerCartId, accountId);
    }

    //物品减少数量
    @Override
    public void reduceCart(Integer customerCartId, Integer accountId) {
        CustomerCartTb customerCartTb = customerCartTbMapper.CustomerCartTb(customerCartId, accountId);
        if (customerCartTb.getFinishedGoodsQty() == 1) {
            customerCartTbMapper.delCart(customerCartId, accountId);
        } else {
            customerCartTbMapper.updateQty(customerCartTb.getFinishedGoodsQty() - 1, customerCartId, accountId);
        }
    }

    //物品增加数量
    @Override
    public void addCart(Integer customerCartId, Integer accountId) {
        CustomerCartTb customerCartTb = customerCartTbMapper.CustomerCartTb(customerCartId, accountId);
        customerCartTbMapper.updateQty(customerCartTb.getFinishedGoodsQty() + 1, customerCartId, accountId);
    }

    //添加物品到购物车状态1
    @Override
    public int cart1(Integer accountId, Integer finishedGoodsQty, Integer purchaseOrderProductPnId, Integer finishedGoodsListSubId, Integer selectStatus, Integer integral, Integer jf) {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (purchaseOrderProductPnId != 0) {
            FinishedGoodsListTb finished = finishedGoodsListTbService.getFinished(purchaseOrderProductPnId);
            CustomerCartTb customerCart = new CustomerCartTb();
            customerCart.setAccountId(accountId);
            customerCart.setFinishedGoodsCartDate(df.format(time));
            customerCart.setFinishedGoodsDescription(finished.getFinishedGoodsDescription());
            customerCart.setFinishedGoodsName(finished.getFinishedGoodsName());
            customerCart.setFinishedGoodsQty(finishedGoodsQty);
            customerCart.setFinishedGoodsStatus(0);
            customerCart.setPurchaseOrderProductPnId(purchaseOrderProductPnId);
            customerCart.setPurchaseOrderProductPn(finished.getPurchaseOrderProductPn());
            customerCart.setUnitPrice(finished.getFinishedGoodsCustomerPriced());
            customerCart.setImgAddr(finished.getHomePageAddr());
//            customerCart.setUploadAddr(photoPath);
            customerCart.setComment("null");
            customerCart.setSelectStatus(selectStatus);
            customerCart.setIntegral(integral);
            customerCart.setJf(jf);
            customerCart.setBz(1);
            customerCart.setFinishedGoodsListSubId(0);
            customerCartTbService.save(customerCart);
        }
        if (finishedGoodsListSubId != 0) {
            FinishedGoodsListSubTb f = finishedGoodsListSubTbMapper.f(finishedGoodsListSubId);
            FinishedGoodsListTb finished = finishedGoodsListTbService.getFinished(f.getPurchaseOrderProductPnId());
            CustomerCartTb customerCart = new CustomerCartTb();
            customerCart.setAccountId(accountId);
            customerCart.setFinishedGoodsCartDate(df.format(time));
            customerCart.setFinishedGoodsDescription(finished.getFinishedGoodsDescription());
            customerCart.setFinishedGoodsName(f.getFinishedGoodsName());
            customerCart.setFinishedGoodsQty(finishedGoodsQty);
            customerCart.setFinishedGoodsStatus(0);
            customerCart.setPurchaseOrderProductPnId(f.getPurchaseOrderProductPnId());
            customerCart.setPurchaseOrderProductPn(f.getPurchaseOrderProductPn());
            customerCart.setUnitPrice(f.getFinishedGoodsCustomerPriced());
            customerCart.setFinishedGoodsListSubId(f.getFinishedGoodsListSubId());
            customerCart.setImgAddr(f.getHomePageAddr());
//            customerCart.setUploadAddr(photoPath);
            customerCart.setComment("null");
            customerCart.setSelectStatus(selectStatus);
            customerCart.setIntegral(integral);
            customerCart.setJf(jf);
            customerCart.setBz(1);
            customerCartTbService.save(customerCart);
        }
        int customerCartID = customerCartTbMapper.customerCartID();
        return customerCartID;
    }

    //添加物品到购物车状态0
    @Override
    public int cart0(Integer accountId, Integer finishedGoodsQty, Integer purchaseOrderProductPnId,String purchaseOrderProductPn, Integer finishedGoodsListSubId, Integer selectStatus, Integer integral, Integer jf) {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (purchaseOrderProductPnId != 0) {
            CustomerCartTb customerCartTb = customerCartTbMapper.selectBz(purchaseOrderProductPn, accountId);
            if (customerCartTb != null) {
                Integer qty = customerCartTb.getFinishedGoodsQty();
                Integer id = customerCartTb.getCustomerCartId();
                customerCartTbMapper.qty(qty + finishedGoodsQty, customerCartTb.getCustomerCartId());
                customerCartTbMapper.updateTime(df.format(time), customerCartTb.getCustomerCartId(), accountId);
                return id;
            } else {
                FinishedGoodsListTb finished = finishedGoodsListTbService.getById(purchaseOrderProductPnId);
                CustomerCartTb customerCart = new CustomerCartTb();
                customerCart.setAccountId(accountId);
                customerCart.setFinishedGoodsCartDate(df.format(time));
                customerCart.setFinishedGoodsDescription(finished.getFinishedGoodsDescription());
                customerCart.setFinishedGoodsName(finished.getFinishedGoodsName());
                customerCart.setFinishedGoodsQty(finishedGoodsQty);
                customerCart.setFinishedGoodsStatus(0);
                customerCart.setPurchaseOrderProductPnId(purchaseOrderProductPnId);
                customerCart.setPurchaseOrderProductPn(finished.getPurchaseOrderProductPn());
                customerCart.setUnitPrice(finished.getFinishedGoodsCustomerPriced());
                customerCart.setImgAddr(finished.getHomePageAddr());
                customerCart.setSelectStatus(selectStatus);
                customerCart.setIntegral(integral);
                customerCart.setJf(jf);
                customerCart.setBz(0);
                customerCart.setFinishedGoodsListSubId(0);
                customerCartTbService.save(customerCart);
            }

        }
        if (finishedGoodsListSubId != 0) {
            CustomerCartTb customerCartTb = customerCartTbMapper.selectBz1(finishedGoodsListSubId, accountId);
            if (customerCartTb != null) {
                Integer qty = customerCartTb.getFinishedGoodsQty();
                Integer id = customerCartTb.getCustomerCartId();
                customerCartTbMapper.qty(qty + finishedGoodsQty, customerCartTb.getCustomerCartId());
                customerCartTbMapper.updateTime(df.format(time), customerCartTb.getCustomerCartId(), accountId);
                return id;
            } else {
                FinishedGoodsListSubTb f = finishedGoodsListSubTbMapper.f(finishedGoodsListSubId);
                FinishedGoodsListTb finished = finishedGoodsListTbService.getFinished(f.getPurchaseOrderProductPnId());
                CustomerCartTb customerCart = new CustomerCartTb();
                customerCart.setAccountId(accountId);
                customerCart.setFinishedGoodsCartDate(df.format(time));
                customerCart.setFinishedGoodsDescription(finished.getFinishedGoodsDescription());
                customerCart.setFinishedGoodsName(f.getFinishedGoodsName());
                customerCart.setFinishedGoodsQty(finishedGoodsQty);
                customerCart.setFinishedGoodsStatus(0);
                customerCart.setPurchaseOrderProductPnId(f.getPurchaseOrderProductPnId());
                customerCart.setPurchaseOrderProductPn(f.getPurchaseOrderProductPn());
                customerCart.setUnitPrice(f.getFinishedGoodsCustomerPriced());
                customerCart.setFinishedGoodsListSubId(f.getFinishedGoodsListSubId());
                customerCart.setImgAddr(f.getHomePageAddr());
//            customerCart.setUploadAddr(photoPath);
                customerCart.setComment("null");
                customerCart.setSelectStatus(selectStatus);
                customerCart.setIntegral(integral);
                customerCart.setJf(jf);
                customerCart.setBz(0);
                customerCartTbService.save(customerCart);
            }
        }
        int customerCartID = customerCartTbMapper.customerCartID();
        return customerCartID;
    }

    //添加物品到购物车状态2
    @Override
    public int cart2(Integer accountId, Integer finishedGoodsQty, Integer purchaseOrderProductPnId, Integer finishedGoodsListSubId, String comment, Integer selectStatus, Integer integral, Integer jf) {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (purchaseOrderProductPnId != 0){
            FinishedGoodsListTb finished = finishedGoodsListTbService.getFinished(purchaseOrderProductPnId);
            CustomerCartTb customerCart = new CustomerCartTb();
            customerCart.setAccountId(accountId);
            customerCart.setFinishedGoodsCartDate(df.format(time));
            customerCart.setFinishedGoodsDescription(finished.getFinishedGoodsDescription());
            customerCart.setFinishedGoodsName(finished.getFinishedGoodsName());
            customerCart.setFinishedGoodsQty(finishedGoodsQty);
            customerCart.setFinishedGoodsStatus(0);
            customerCart.setPurchaseOrderProductPnId(purchaseOrderProductPnId);
            customerCart.setPurchaseOrderProductPn(finished.getPurchaseOrderProductPn());
            customerCart.setUnitPrice(finished.getFinishedGoodsCustomerPriced());
            customerCart.setImgAddr(finished.getHomePageAddr());
            customerCart.setComment(comment);
            customerCart.setSelectStatus(selectStatus);
            customerCart.setIntegral(integral);
            customerCart.setJf(jf);
            customerCart.setBz(2);
            customerCart.setFinishedGoodsListSubId(0);
            customerCartTbService.save(customerCart);
        }
        if (finishedGoodsListSubId != 0 ){
            FinishedGoodsListSubTb f = finishedGoodsListSubTbMapper.f(finishedGoodsListSubId);
            FinishedGoodsListTb finished = finishedGoodsListTbService.getFinished(f.getPurchaseOrderProductPnId());
            CustomerCartTb customerCart = new CustomerCartTb();
            customerCart.setAccountId(accountId);
            customerCart.setFinishedGoodsCartDate(df.format(time));
            customerCart.setFinishedGoodsDescription(finished.getFinishedGoodsDescription());
            customerCart.setFinishedGoodsName(f.getFinishedGoodsName());
            customerCart.setFinishedGoodsQty(finishedGoodsQty);
            customerCart.setFinishedGoodsStatus(0);
            customerCart.setPurchaseOrderProductPnId(f.getPurchaseOrderProductPnId());
            customerCart.setPurchaseOrderProductPn(f.getPurchaseOrderProductPn());
            customerCart.setUnitPrice(f.getFinishedGoodsCustomerPriced());
            customerCart.setFinishedGoodsListSubId(f.getFinishedGoodsListSubId());
            customerCart.setImgAddr(f.getHomePageAddr());
//            customerCart.setUploadAddr(photoPath);
            customerCart.setComment("null");
            customerCart.setSelectStatus(selectStatus);
            customerCart.setIntegral(integral);
            customerCart.setJf(jf);
            customerCart.setBz(2);
            customerCartTbService.save(customerCart);
        }
        int customerCartID = customerCartTbMapper.customerCartID();
        return customerCartID;
    }

    @Override
    public void choose(Integer customerCartId) {
        customerCartTbMapper.updateChoose(customerCartId);
    }

    @Override
    public void cancelChoose(Integer customerCartId) {
        customerCartTbMapper.updateCancelChoose(customerCartId);
    }

    @Override
    public void allChoose(Integer accountId) {
        customerCartTbMapper.updateAllChoose(accountId);
    }

    @Override
    public void cancelAllChoose(Integer accountId) {
        customerCartTbMapper.updateCancelAllChoose(accountId);
    }

    @Override
    public List<CustomerCartTb> getSelectStatus(Integer accountId) {
        return customerCartTbMapper.getAccountCart(accountId);
    }

    @Override
    public void updateIntegral(Integer integral, Integer customerCartId) {
        customerCartTbMapper.updateIntegral(integral, customerCartId);
    }
}
