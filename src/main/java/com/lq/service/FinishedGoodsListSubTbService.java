package com.lq.service;

import com.lq.pojo.FinishedGoodsListSubTb;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-08-26
 */
public interface FinishedGoodsListSubTbService extends IService<FinishedGoodsListSubTb> {

    //查询本商品的子商品
    List<FinishedGoodsListSubTb> selectSub (Integer purchaseOrderProductPnId);

    //增加本商品的子商品
    int addSub(FinishedGoodsListSubTb finishedGoodsListSubTb);

    //删除本商品的子商品
    void delSub(Integer finishedGoodsListSubId);

}
