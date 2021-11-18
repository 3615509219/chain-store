package com.lq.service.impl;

import com.lq.dao.*;
import com.lq.pojo.FinishedGoodsListTb;
import com.lq.pojo.HistoryFinishedGoodsListTb;
import com.lq.service.FinishedGoodsListTbService;
import com.lq.service.HistoryFinishedGoodsListTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.service.OffTheShelfTbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-11
 */
@Service
public class HistoryFinishedGoodsListTbServiceImpl extends ServiceImpl<HistoryFinishedGoodsListTbMapper, HistoryFinishedGoodsListTb> implements HistoryFinishedGoodsListTbService {

    @Resource
    private FinishedGoodsListTbService finishedGoodsListTbService;

    @Resource
    private HistoryFinishedGoodsListTbService historyFinishedGoodsListTbService;

    @Resource
    private CustomerCartTbMapper customerCartTbMapper;
    @Resource
    private HistoryFinishedGoodsListTbMapper historyFinishedGoodsListTbMapper;

    @Resource
    private OffTheShelfTbService offTheShelfTbService;

    @Resource
    private FinishedGoodsListTbMapper finishedGoodsListTbMapper;

    @Resource
    private CategoryTbMapper categoryTbMapper;
    //下架
    @Override
    public void OffTheShelf(Integer purchaseOrderProductPnId) {
        HistoryFinishedGoodsListTb historyFinishedGoodsListTb = new HistoryFinishedGoodsListTb();
        FinishedGoodsListTb byId = finishedGoodsListTbService.getById(purchaseOrderProductPnId);
        historyFinishedGoodsListTb.setFinishedGoodsAcc(byId.getFinishedGoodsAcc());
        historyFinishedGoodsListTb.setFinishedGoodsColor(byId.getFinishedGoodsColor());
        historyFinishedGoodsListTb.setFinishedGoodsHeight(byId.getFinishedGoodsHeight());
        historyFinishedGoodsListTb.setFinishedGoodsName(byId.getFinishedGoodsName());
        historyFinishedGoodsListTb.setFinishedGoodsLength(byId.getFinishedGoodsLength());
        historyFinishedGoodsListTb.setAccountId(byId.getAccountId());
        historyFinishedGoodsListTb.setFinishedGoodsPatent(byId.getFinishedGoodsPatent());
        historyFinishedGoodsListTb.setDiy(byId.getDiy());
        historyFinishedGoodsListTb.setFinishedGoodsTexture(byId.getFinishedGoodsTexture());
        historyFinishedGoodsListTb.setFinishedGoodsVolume(byId.getFinishedGoodsVolume());
        historyFinishedGoodsListTb.setDrawingFilesAddr(byId.getDrawingFilesAddr());
        historyFinishedGoodsListTb.setFactoryMaterialPn(byId.getFactoryMaterialPn());
        historyFinishedGoodsListTb.setFinishedGoodsCertificatioin(byId.getFinishedGoodsCertificatioin());
        historyFinishedGoodsListTb.setFinishedGoodsCustomerPriced(byId.getFinishedGoodsCustomerPriced());
        historyFinishedGoodsListTb.setFinishedGoodsDescription(byId.getFinishedGoodsDescription());
        historyFinishedGoodsListTb.setFinishedGoodsIssueDatetime(byId.getFinishedGoodsIssueDatetime());
        historyFinishedGoodsListTb.setFinishedGoodsManufacturePrice(byId.getFinishedGoodsManufacturePrice());
        historyFinishedGoodsListTb.setFinishedGoodsPatentNumber(byId.getFinishedGoodsPatentNumber());
        historyFinishedGoodsListTb.setFinishedGoodsPrintTime(byId.getFinishedGoodsPrintTime());
        historyFinishedGoodsListTb.setFinishedGoodsPrintTimes(byId.getFinishedGoodsPrintTimes());
        historyFinishedGoodsListTb.setHomePageAddr(byId.getHomePageAddr());
        historyFinishedGoodsListTb.setManufactureId(byId.getManufactureId());
        historyFinishedGoodsListTb.setPrinterModelRequestCustomer(byId.getPrinterModelRequestCustomer());
        historyFinishedGoodsListTb.setPurchaseOrderProductPn(byId.getPurchaseOrderProductPn());
        historyFinishedGoodsListTb.setPurchaseOrderProductPnId(purchaseOrderProductPnId);
        historyFinishedGoodsListTb.setFinishedGoodsWeight(byId.getFinishedGoodsWeight());
        historyFinishedGoodsListTb.setFinishedGoodsWidth(byId.getFinishedGoodsWidth());
        historyFinishedGoodsListTb.setXiaJia(byId.getXiaJia());
        historyFinishedGoodsListTb.setBz(byId.getBz());
        historyFinishedGoodsListTb.setNumImg(byId.getNumImg());
        historyFinishedGoodsListTb.setImgBz(byId.getImgBz());
        historyFinishedGoodsListTb.setPopupImg(byId.getPopupImg());
        categoryTbMapper.delCategory(purchaseOrderProductPnId);
        historyFinishedGoodsListTbService.save(historyFinishedGoodsListTb);
        finishedGoodsListTbService.removeById(purchaseOrderProductPnId);
        customerCartTbMapper.delCart1(purchaseOrderProductPnId);
    }
    //取消下架或再次上架
    @Override
    public int noTheShelf(Integer purchaseOrderProductPnId,Integer id) {
        FinishedGoodsListTb finishedGoodsListTb = new FinishedGoodsListTb();
        HistoryFinishedGoodsListTb s = historyFinishedGoodsListTbMapper.s(purchaseOrderProductPnId);
        finishedGoodsListTb.setFinishedGoodsAcc(s.getFinishedGoodsAcc());
        finishedGoodsListTb.setFinishedGoodsColor(s.getFinishedGoodsColor());
        finishedGoodsListTb.setFinishedGoodsHeight(s.getFinishedGoodsHeight());
        finishedGoodsListTb.setFinishedGoodsName(s.getFinishedGoodsName());
        finishedGoodsListTb.setFinishedGoodsLength(s.getFinishedGoodsLength());
        finishedGoodsListTb.setAccountId(s.getAccountId());
        finishedGoodsListTb.setFinishedGoodsPatent(s.getFinishedGoodsPatent());
        finishedGoodsListTb.setDiy(s.getDiy());
        finishedGoodsListTb.setFinishedGoodsTexture(s.getFinishedGoodsTexture());
        finishedGoodsListTb.setFinishedGoodsVolume(s.getFinishedGoodsVolume());
        finishedGoodsListTb.setDrawingFilesAddr(s.getDrawingFilesAddr());
        finishedGoodsListTb.setFactoryMaterialPn(s.getFactoryMaterialPn());
        finishedGoodsListTb.setFinishedGoodsCertificatioin(s.getFinishedGoodsCertificatioin());
        finishedGoodsListTb.setFinishedGoodsCustomerPriced(s.getFinishedGoodsCustomerPriced());
        finishedGoodsListTb.setFinishedGoodsDescription(s.getFinishedGoodsDescription());
        finishedGoodsListTb.setFinishedGoodsIssueDatetime(s.getFinishedGoodsIssueDatetime());
        finishedGoodsListTb.setFinishedGoodsManufacturePrice(s.getFinishedGoodsManufacturePrice());
        finishedGoodsListTb.setFinishedGoodsPatentNumber(s.getFinishedGoodsPatentNumber());
        finishedGoodsListTb.setFinishedGoodsPrintTime(s.getFinishedGoodsPrintTime());
        finishedGoodsListTb.setFinishedGoodsPrintTimes(s.getFinishedGoodsPrintTimes());
        finishedGoodsListTb.setHomePageAddr(s.getHomePageAddr());
        finishedGoodsListTb.setManufactureId(s.getManufactureId());
        finishedGoodsListTb.setPrinterModelRequestCustomer(s.getPrinterModelRequestCustomer());
        finishedGoodsListTb.setPurchaseOrderProductPn(s.getPurchaseOrderProductPn());
        finishedGoodsListTb.setFinishedGoodsWeight(s.getFinishedGoodsWeight());
        finishedGoodsListTb.setFinishedGoodsWidth(s.getFinishedGoodsWidth());
        finishedGoodsListTb.setXiaJia(s.getXiaJia());
        finishedGoodsListTb.setBz(s.getBz());
        finishedGoodsListTb.setNumImg(s.getNumImg());
        finishedGoodsListTb.setImgBz(s.getImgBz());
        finishedGoodsListTb.setPopupImg(s.getPopupImg());
        finishedGoodsListTbService.save(finishedGoodsListTb);
        historyFinishedGoodsListTbMapper.del(purchaseOrderProductPnId);
        offTheShelfTbService.removeById(id);
        return finishedGoodsListTbMapper.zuiXing();
    }
}
