package com.lq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.dao.PersonRegisterTbMapper;
import com.lq.dao.UploadTbMapper;
import com.lq.pojo.BuyNowImgTb;
import com.lq.pojo.HistoryUploadTb;
import com.lq.pojo.UploadTb;
import com.lq.service.BuyNowImgTbService;
import com.lq.service.HistoryUploadTbService;
import com.lq.service.UploadTbService;
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
 * @since 2021-02-04
 */
@Service
public class UploadTbServiceImpl extends ServiceImpl<UploadTbMapper, UploadTb> implements UploadTbService {

    @Resource
    private UploadTbMapper uploadTbMapper;
    @Resource
    private PersonRegisterTbMapper personRegisterTbMapper;
    @Resource
    private HistoryUploadTbService historyUploadTbService;
    @Resource
    private DealFile dealFile;
    @Resource
    private BuyNowImgTbService nowImgTbService;

    //增加上传文件
    @Override
    public String Upload(@RequestParam(value = "file", required = false) MultipartFile multipartFile, Integer accountId, String description, String patent, String title) {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            String photoPath = dealFile.getPhotoPath(multipartFile);
            uploadTbMapper.addUpload(accountId, 0, photoPath, df.format(time), description, patent, "上传物品", title, "审核中", "", df.format(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return title;
    }

    @Override
    public void BuyNow(MultipartFile multipartFile, Integer productId, String productBz) throws Exception {
        if (multipartFile == null){
            BuyNowImgTb buyNowImgTb = new BuyNowImgTb();
            buyNowImgTb.setProductId(productId);
            buyNowImgTb.setProductImg("null");
            buyNowImgTb.setProductBz(productBz);
            nowImgTbService.save(buyNowImgTb);
        }
        if (multipartFile != null){
            String photoPath = dealFile.getPhotoPath(multipartFile);
            BuyNowImgTb buyNowImgTb = new BuyNowImgTb();
            buyNowImgTb.setProductId(productId);
            buyNowImgTb.setProductImg(photoPath);
            buyNowImgTb.setProductBz(productBz);
            nowImgTbService.save(buyNowImgTb);
        }
    }
    //审核中
    @Override
    public List<UploadTb> UnderReview(Integer accountId) {
        return uploadTbMapper.UnderReview(accountId);
    }

    //审核失败
    @Override
    public List<UploadTb> AuditFailed(Integer accountId) {
        return uploadTbMapper.AuditFailed(accountId);
    }

    //审核通过
    @Override
    public List<UploadTb> Approved(Integer accountId) {
        return uploadTbMapper.Approved(accountId);
    }

    //客户上传3D文档列表
    @Override
    public List<UploadTb> uploadList() {
        return uploadTbMapper.uploadList();
    }

    //上传修改后的客户3D文档到服务器
    @Override
    public void newUpload(MultipartFile multipartFile, Integer accountId, Integer uploadID, String title, String description, String approveStatus, String fail, String patent) {
        HistoryUploadTb historyUploadTb = new HistoryUploadTb();
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        UploadTb byId = uploadTbMapper.uploadTb(uploadID);
        historyUploadTb.setAccountId(byId.getAccountId());
        historyUploadTb.setManufactureId(byId.getManufactureId());
        historyUploadTb.setUploadAddr(byId.getUploadAddr());
        historyUploadTb.setUploadTime(byId.getUploadTime());
        historyUploadTb.setDescription(byId.getDescription());
        historyUploadTb.setTitle(byId.getTitle());
        historyUploadTb.setApproveStatus(byId.getApproveStatus());
        historyUploadTbService.save(historyUploadTb);
        int points = personRegisterTbMapper.getPoints(accountId);
        if (approveStatus.equals("审核通过")) {
            try {
                String photoPath = dealFile.getPhotoPath(multipartFile);
                uploadTbMapper.updateUpload(uploadID, photoPath, title, description, approveStatus, fail, df.format(time), patent);
                if (approveStatus.equals("审核通过") && patent.length() != 0) {
                    personRegisterTbMapper.updatePoints(points + 400, accountId);
                    uploadTbMapper.updateIntegral(uploadID, 400, "未处理");
                }
                if (approveStatus.equals("审核通过") && patent.length() == 0) {
                    personRegisterTbMapper.updatePoints(points + 200, accountId);
                    uploadTbMapper.updateIntegral(uploadID, 200, "未处理");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (approveStatus.equals("审核失败")) {
            uploadTbMapper.updateUpload(uploadID, byId.getUploadAddr(), title, description, approveStatus, fail, df.format(time), patent);
        }
    }

    //全部审核通过
    @Override
    public List<UploadTb> ApprovedList() {
        return uploadTbMapper.ApprovedList();
    }

    //全部审核失败
    @Override
    public List<UploadTb> noUpload() {
        return uploadTbMapper.noUpload();
    }

    //全部审核成功
    @Override
    public List<UploadTb> yesUpload() {
        return uploadTbMapper.yesUpload();
    }

    //处理审核成功
    @Override
    public void stateUpload(Integer uploadID) {
        uploadTbMapper.updateState(uploadID);
    }
}
