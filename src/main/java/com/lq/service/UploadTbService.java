package com.lq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lq.pojo.UploadTb;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-02-04
 */
public interface UploadTbService extends IService<UploadTb> {
    //增加上传文件
    String Upload(@RequestParam(value = "file",required = false)MultipartFile multipartFile, Integer accountId, String description,String patent,String title);

    //立即购买上传
    void BuyNow(@RequestParam(value = "file",required = false)MultipartFile multipartFile, Integer productId, String productBz)throws Exception;

    //审核中
    List<UploadTb> UnderReview(Integer accountId);
    //审核失败
    List<UploadTb> AuditFailed(Integer accountId);
    //审核通过
    List<UploadTb> Approved(Integer accountId);

    //客户上传3D文档列表
    List<UploadTb> uploadList();

    //上传修改后的客户3D文档到服务器
    void newUpload(@RequestParam(value = "file",required = false)MultipartFile multipartFile,Integer accountId,Integer uploadID,String title,String description,String approveStatus,String fail,String patent);

    //全部审核通过的
    List<UploadTb> ApprovedList();

    //全部审核失败
    List<UploadTb> noUpload();

    //全部审核成功
    List<UploadTb> yesUpload();

    //处理审核成功
    void stateUpload (Integer uploadID);
}
