package com.lq.service;

import com.lq.pojo.CategoriesTb;
import com.lq.pojo.CategoryTb;
import com.lq.pojo.FinishedGoodsListTb;
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
 * @since 2021-01-13
 */
public interface FinishedGoodsListTbService extends IService<FinishedGoodsListTb> {

    //查询全部
    List<FinishedGoodsListTb> getAllFinished();
    //模糊查询
    List<FinishedGoodsListTb> getVagueFinished(String finishedGoodsDescription);
    //是否专利
    List<FinishedGoodsListTb> getPatentFinished();
    //分类
    List<CategoriesTb> getAllCategory();
    //查询单个物品详情
    FinishedGoodsListTb getFinished(Integer purchaseOrderProductPnId);
    //分类商品
    List<FinishedGoodsListTb> getCategory(String finishedGoodsCategory);
    //分类专利
    List<FinishedGoodsListTb> getPatent(String finishedGoodsCategory);
    //查询个人的前4条记录
    List<FinishedGoodsListTb> getAccountIdLimit(Integer accountId ,Integer num);
    //查询个人的全部记录
    List<FinishedGoodsListTb> getAccountId(Integer accountId);

    //将修改后的客户3D文档商品加入到3D打印商城
    int insertFinished (@RequestParam(value = "file",required = false) MultipartFile multipartFile,String finishedGoodsName ,
                         String purchaseOrderProductPn,String finishedGoodsDescription,Double finishedGoodsLength,
                         Double finishedGoodsWidth,Double finishedGoodsHeight,Double finishedGoodsAcc,Double finishedGoodsWeight,
                         String finishedGoodsTexture,String finishedGoodsCertificatioin,Integer finishedGoodsPrintTimes,
                         Double finishedGoodsCustomerPriced,Double finishedGoodsManufacturePrice,String factoryMaterialPn,
                         String finishedGoodsColor,String finishedGoodsCategory,Double finishedGoodsVolume,Integer finishedGoodsPrintTime,
                         String printerModelRequestCustomer,Integer diy,Integer uploadId,Integer bz,Integer numImg,String imgBz,@RequestParam(value = "file1",required = false) MultipartFile multipartFile1);

    //修改商城文档
    void updateUploadFinished(@RequestParam(value = "file",required = false) MultipartFile multipartFile,Integer purchaseOrderProductPnId);


    //修改首页图片
    void updateHomeImg (@RequestParam(value = "file",required = false) MultipartFile multipartFile,Integer purchaseOrderProductPnId);

    //价格
    List<FinishedGoodsListTb> price (Integer purchaseOrderProductPnId);

    //修改优先级
    void updateBz (Integer purchaseOrderProductPnId,Integer bz);

    //添加弹窗图片
    void addPopup (Integer purchaseOrderProductPnId,@RequestParam(value = "file",required = false) MultipartFile multipartFile);

    //修改弹窗图片
    void updatePopup (Integer purchaseOrderProductPnId,@RequestParam(value = "file",required = false) MultipartFile multipartFile);

    //查询弹窗图片
    String selectPopup (Integer purchaseOrderProductPnId);
}
