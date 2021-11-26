package com.lq.service.impl;

import com.lq.dao.UploadTbMapper;
import com.lq.pojo.CategoriesTb;
import com.lq.pojo.CategoryTb;
import com.lq.pojo.FinishedGoodsListTb;
import com.lq.dao.FinishedGoodsListTbMapper;
import com.lq.pojo.UploadTb;
import com.lq.service.CategoryTbService;
import com.lq.service.FinishedGoodsListTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.utils.DealFile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
public class FinishedGoodsListTbServiceImpl extends ServiceImpl<FinishedGoodsListTbMapper, FinishedGoodsListTb> implements FinishedGoodsListTbService {

    @Resource
    private FinishedGoodsListTbService finishedGoodsListTbService;

    @Resource
    private FinishedGoodsListTbMapper finishedGoodsListTbMapper;

    @Resource
    private CategoryTbService categoryTbService;

    @Resource
    private DealFile dealFile;

    @Resource
    private UploadTbMapper uploadTbMapper;

    //查询全部
    @Override
    public List<FinishedGoodsListTb> getAllFinished() {
        return finishedGoodsListTbMapper.selectAll();
    }

    //模糊查询
    @Override
    public List<FinishedGoodsListTb> getVagueFinished(String finishedGoodsDescription) {
        return finishedGoodsListTbMapper.getVague(finishedGoodsDescription);
    }

    //是否专利
    @Override
    public List<FinishedGoodsListTb> getPatentFinished() {
        return finishedGoodsListTbMapper.getPatent();
    }

    //分类
    @Override
    public List<CategoriesTb> getAllCategory() {
        return finishedGoodsListTbMapper.getCategory();
    }

    //查询单个物品详情
    @Override
    public FinishedGoodsListTb getFinished(Integer purchaseOrderProductPnId) {
        return finishedGoodsListTbService.getById(purchaseOrderProductPnId);
    }

    //分类商品
    @Override
    public List<FinishedGoodsListTb> getCategory(String finishedGoodsCategory) {
        return finishedGoodsListTbMapper.getGoodsCategory(finishedGoodsCategory);
    }

    //分类专利
    @Override
    public List<FinishedGoodsListTb> getPatent(String finishedGoodsCategory) {
        return finishedGoodsListTbMapper.getPatentCategory(finishedGoodsCategory);
    }

    @Override
    public List<FinishedGoodsListTb> getAccountIdLimit(Integer accountId,Integer num) {
        return finishedGoodsListTbMapper.getAccountLimit(accountId,num);
    }

    @Override
    public List<FinishedGoodsListTb> getAccountId(Integer accountId) {
        return finishedGoodsListTbMapper.getAccount(accountId);
    }
    //将修改后的客户3D文档商品加入到3D打印商城
    @Override
    public int insertFinished(@RequestParam(value = "file", required = false)MultipartFile multipartFile, String finishedGoodsName, String purchaseOrderProductPn,
                               String finishedGoodsDescription, Double finishedGoodsLength, Double finishedGoodsWidth,
                               Double finishedGoodsHeight, Double finishedGoodsAcc, Double finishedGoodsWeight,
                               String finishedGoodsTexture, String finishedGoodsCertificatioin, Integer finishedGoodsPrintTimes,
                               Double finishedGoodsCustomerPriced, Double finishedGoodsManufacturePrice, String factoryMaterialPn,
                               String finishedGoodsColor, String finishedGoodsCategory, Double finishedGoodsVolume,
                               Integer finishedGoodsPrintTime, String printerModelRequestCustomer, Integer diy,Integer uploadId,Integer bz,Integer numImg,String imgBz,@RequestParam(value = "file1",required = false) MultipartFile multipartFile1) {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        UploadTb uploads = uploadTbMapper.upload(uploadId);
        FinishedGoodsListTb finishedGoodsListTb = new FinishedGoodsListTb();
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            String photoPath1 = dealFile.getPhotoPath(multipartFile1);
            finishedGoodsListTb.setFinishedGoodsName(finishedGoodsName);
            finishedGoodsListTb.setPurchaseOrderProductPn(purchaseOrderProductPn);
            finishedGoodsListTb.setFinishedGoodsDescription(finishedGoodsDescription);
            finishedGoodsListTb.setFinishedGoodsLength(finishedGoodsLength);
            finishedGoodsListTb.setFinishedGoodsWidth(finishedGoodsWidth);
            finishedGoodsListTb.setFinishedGoodsHeight(finishedGoodsHeight);
            finishedGoodsListTb.setFinishedGoodsAcc(finishedGoodsAcc);
            finishedGoodsListTb.setFinishedGoodsWeight(finishedGoodsWeight);
            finishedGoodsListTb.setFinishedGoodsTexture(finishedGoodsTexture);
            finishedGoodsListTb.setFinishedGoodsCertificatioin(finishedGoodsCertificatioin);
            finishedGoodsListTb.setFinishedGoodsPrintTimes(finishedGoodsPrintTimes);
            finishedGoodsListTb.setFinishedGoodsCustomerPriced(finishedGoodsCustomerPriced);
            finishedGoodsListTb.setFinishedGoodsManufacturePrice(finishedGoodsManufacturePrice);
            finishedGoodsListTb.setFactoryMaterialPn(factoryMaterialPn);
            finishedGoodsListTb.setFinishedGoodsColor(finishedGoodsColor);
            finishedGoodsListTb.setFinishedGoodsVolume(finishedGoodsVolume);
            finishedGoodsListTb.setFinishedGoodsPrintTime(finishedGoodsPrintTime);
            finishedGoodsListTb.setPrinterModelRequestCustomer(printerModelRequestCustomer);
            finishedGoodsListTb.setDiy(diy);
            if (uploads.getPatent().length()!=0){
                finishedGoodsListTb.setFinishedGoodsPatent("是");
                finishedGoodsListTb.setFinishedGoodsPatentNumber(uploads.getPatent());
            }if (uploads.getPatent().length()==0){
                finishedGoodsListTb.setFinishedGoodsPatent("否");
                finishedGoodsListTb.setFinishedGoodsPatentNumber(null);
            }
            finishedGoodsListTb.setAccountId(uploads.getAccountId());
            finishedGoodsListTb.setManufactureId(uploads.getManufactureId());
            finishedGoodsListTb.setFinishedGoodsIssueDatetime(df.format(time));
            finishedGoodsListTb.setDrawingFilesAddr(uploads.getUploadAddr());
            finishedGoodsListTb.setHomePageAddr(photoPath);
            finishedGoodsListTb.setXiaJia(0);
            finishedGoodsListTb.setBz(bz);
            finishedGoodsListTb.setNumImg(numImg);
            finishedGoodsListTb.setImgBz(imgBz);
            finishedGoodsListTb.setPopupImg(photoPath1);
            finishedGoodsListTb.setNumberOfVisits(0);
            finishedGoodsListTbService.save(finishedGoodsListTb);
            uploadTbMapper.updateStatus(uploadId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finishedGoodsListTb.getPurchaseOrderProductPnId();
    }
    //修改商城文档
    @Override
    public void updateUploadFinished(@RequestParam(value = "file", required = false)MultipartFile multipartFile, Integer purchaseOrderProductPnId) {
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            finishedGoodsListTbMapper.updateUploadFinished(photoPath,purchaseOrderProductPnId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改首页图片
    @Override
    public void updateHomeImg(@RequestParam(value = "file", required = false)MultipartFile multipartFile, Integer purchaseOrderProductPnId) {
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            finishedGoodsListTbMapper.updateHomeImg(photoPath,purchaseOrderProductPnId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FinishedGoodsListTb> price(Integer purchaseOrderProductPnId) {
        return finishedGoodsListTbMapper.price(purchaseOrderProductPnId);
    }

    //修改优先级
    @Override
    public void updateBz(Integer purchaseOrderProductPnId, Integer bz) {
        finishedGoodsListTbMapper.updateBz(purchaseOrderProductPnId, bz);
    }

    //增加和修改弹窗图片
    @Override
    public void addPopup(Integer purchaseOrderProductPnId, MultipartFile multipartFile) {
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            finishedGoodsListTbMapper.updatePopupImg(photoPath,purchaseOrderProductPnId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改弹窗图片
    @Override
    public void updatePopup(Integer purchaseOrderProductPnId, MultipartFile multipartFile) {

    }
    //查看弹窗图片
    @Override
    public String selectPopup(Integer purchaseOrderProductPnId) {
        String s = finishedGoodsListTbMapper.selectPopupImg(purchaseOrderProductPnId);
        return s;
    }



}
