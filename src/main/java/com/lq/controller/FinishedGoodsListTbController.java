package com.lq.controller;


import com.lq.dao.FinishedGoodsListTbMapper;
import com.lq.pojo.CategoriesTb;
import com.lq.pojo.CategoryTb;
import com.lq.pojo.FinishedGoodsListTb;
import com.lq.service.FinishedGoodsListTbService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
@Controller
@RequestMapping("/finishedGoodsListTb")
public class FinishedGoodsListTbController {

    @Resource
    private FinishedGoodsListTbService finishedGoodsListTbService;

    @Resource
    private FinishedGoodsListTbMapper finishedGoodsListTbMapper;

    //查询全部
    @GetMapping("getAllGoods")
    @ResponseBody
    public List<FinishedGoodsListTb> getAllGoods() {
        return finishedGoodsListTbService.getAllFinished();
    }

    //模糊查询
    @GetMapping("getVagueGoods")
    @ResponseBody
    public List<FinishedGoodsListTb> getVagueGoods(String finishedGoodsDescription) {
        return finishedGoodsListTbService.getVagueFinished(finishedGoodsDescription);
    }

    //是否专利
    @GetMapping("getPatentGoods")
    @ResponseBody
    public List<FinishedGoodsListTb> getPatentGoods() {
        return finishedGoodsListTbService.getPatentFinished();
    }

    //分类
    @GetMapping("getCategory")
    @ResponseBody
    public List<CategoriesTb> getCategory() {
        return finishedGoodsListTbService.getAllCategory();
    }

    //查询单个物品详情
    @GetMapping("getGoods")
    @ResponseBody
    public FinishedGoodsListTb getGoods(Integer purchaseOrderProductPnId) {
        return finishedGoodsListTbService.getFinished(purchaseOrderProductPnId);
    }

    //分类商品
    @GetMapping("categoryGoods")
    @ResponseBody
    public List<FinishedGoodsListTb> categoryGoods(String finishedGoodsCategory) {
        return finishedGoodsListTbService.getCategory(finishedGoodsCategory);
    }

    //分类专利
    @GetMapping("categoryPatent")
    @ResponseBody
    public List<FinishedGoodsListTb> categoryPatent(String finishedGoodsCategory) {
        return finishedGoodsListTbService.getPatent(finishedGoodsCategory);
    }

    //查询个人的前4条记录
    @GetMapping("accountLimit")
    @ResponseBody
    public List<FinishedGoodsListTb> accountLimit(Integer accountId, Integer num) {
        return finishedGoodsListTbService.getAccountIdLimit(accountId, num);
    }

    //查询个人的全部记录
    @GetMapping("account")
    @ResponseBody
    public List<FinishedGoodsListTb> account(Integer accountId) {
        return finishedGoodsListTbService.getAccountId(accountId);
    }

    //将修改后的客户3D文档商品加入到3D打印商城
    @PostMapping("insertFinishedS")
    @ResponseBody
    public int insertFinishedS(@RequestParam(value = "file", required = false)MultipartFile multipartFile, String finishedGoodsName, String purchaseOrderProductPn,
                               String finishedGoodsDescription, Double finishedGoodsLength, Double finishedGoodsWidth,
                               Double finishedGoodsHeight, Double finishedGoodsAcc, Double finishedGoodsWeight,
                               String finishedGoodsTexture, String finishedGoodsCertificatioin, Integer finishedGoodsPrintTimes,
                               Double finishedGoodsCustomerPriced, Double finishedGoodsManufacturePrice, String factoryMaterialPn,
                               String finishedGoodsColor, String finishedGoodsCategory, Double finishedGoodsVolume,
                               Integer finishedGoodsPrintTime, String printerModelRequestCustomer, Integer diy, Integer uploadId,Integer bz,Integer numImg,String imgBz,@RequestParam(value = "file1",required = false) MultipartFile multipartFile1) {
        return finishedGoodsListTbService.insertFinished(multipartFile, finishedGoodsName, purchaseOrderProductPn, finishedGoodsDescription,
                finishedGoodsLength, finishedGoodsWidth, finishedGoodsHeight, finishedGoodsAcc, finishedGoodsWeight, finishedGoodsTexture,
                finishedGoodsCertificatioin, finishedGoodsPrintTimes, finishedGoodsCustomerPriced, finishedGoodsManufacturePrice,
                factoryMaterialPn, finishedGoodsColor, finishedGoodsCategory, finishedGoodsVolume, finishedGoodsPrintTime,
                printerModelRequestCustomer, diy, uploadId,bz,numImg,imgBz,multipartFile1);
    }

    //修改商城数据
    @PostMapping("updateFinishedS")
    @ResponseBody
    public void updateFinishedS(FinishedGoodsListTb finishedGoodsListTb) {
        finishedGoodsListTbService.updateById(finishedGoodsListTb);
    }

    //修改商城文档地址
    @PostMapping("updateUploadFinishedS")
    @ResponseBody
    public void updateUploadFinishedS(@RequestParam(value = "file", required = false)MultipartFile multipartFile, Integer purchaseOrderProductPnId) {
        finishedGoodsListTbService.updateUploadFinished(multipartFile, purchaseOrderProductPnId);
    }

    //修改首页图片
    @PostMapping("updateHomeImgS")
    @ResponseBody
    public void updateHomeImgS(@RequestParam(value = "file", required = false)MultipartFile multipartFile, Integer purchaseOrderProductPnId) {
        finishedGoodsListTbService.updateHomeImg(multipartFile, purchaseOrderProductPnId);
    }

    //价格重量
    @GetMapping("priceS")
    @ResponseBody
    public List<FinishedGoodsListTb> priceS(Integer purchaseOrderProductPnId) {
        return finishedGoodsListTbService.price(purchaseOrderProductPnId);
    }

    //修改时间
    @PostMapping("timeS")
    @ResponseBody
    public void timeS (Integer purchaseOrderProductPnId){
        finishedGoodsListTbMapper.t(purchaseOrderProductPnId);
    }

    //取消
    @PostMapping("quXiaoXiaJia")
    @ResponseBody
    public void quXiaoXiaJia (Integer purchaseOrderProductPnId){
        finishedGoodsListTbMapper.t1(purchaseOrderProductPnId);
    }

    //修改优先级
    @PostMapping("updateBzS")
    @ResponseBody
    public void updateBzS (Integer purchaseOrderProductPnId,Integer bz){
        finishedGoodsListTbService.updateBz(purchaseOrderProductPnId, bz);
    }

    //所有料号
    @GetMapping("pnS")
    @ResponseBody
    public List<FinishedGoodsListTb> pnS() {
        return finishedGoodsListTbMapper.pn();
    }

    //通过料号查询重量
    @GetMapping("weight")
    @ResponseBody
    public Map<String,Double> weight (String ItemNo){
        Double weight = finishedGoodsListTbMapper.weight(ItemNo);
        Double weight1 = finishedGoodsListTbMapper.weight1(ItemNo);
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("weight", weight);
        map.put("weight1", weight1);
        return map;
    }

    //添加和修改弹窗图片
    @PostMapping("addPopupS")
    @ResponseBody
    public void addPopupS (@RequestParam(value = "file", required = false)MultipartFile multipartFile, Integer purchaseOrderProductPnId){
        finishedGoodsListTbService.addPopup(purchaseOrderProductPnId, multipartFile);
    }

    //查看弹窗图片
    @GetMapping("selectPopupS")
    @ResponseBody
    public String selectPopupS (Integer purchaseOrderProductPnId){
        return finishedGoodsListTbService.selectPopup(purchaseOrderProductPnId);
    }

    //访问次数
    @PostMapping("numberOfVisitsS")
    @ResponseBody
    public void numberOfVisitsS (Integer purchaseOrderProductPnId){
        int i = finishedGoodsListTbMapper.numberOfVisits(purchaseOrderProductPnId);
        finishedGoodsListTbMapper.updateNumber(i+1,purchaseOrderProductPnId);
    }
}

