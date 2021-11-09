package com.lq.dao;

import com.lq.pojo.UploadTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-02-04
 */
public interface UploadTbMapper extends BaseMapper<UploadTb> {

    @Insert("insert into upload_tb values(default,${accountId},${manufactureId},'${uploadAddr}','${uploadTime}','${description}','${patent}','${buyNow}','${title}','${approveStatus}','${fail}','${time}',0,'0');")
    void addUpload(Integer accountId,Integer manufactureId, String uploadAddr, String uploadTime, String description, String patent, String buyNow, String title, String approveStatus, String fail, String time);

    @Select("select * from upload_tb where account_ID = ${accountId} order by upload_ID desc limit 1")
    UploadTb addr(Integer accountId);

    @Select("select title,upload_time,time from upload_tb where account_ID = ${accountId} and approve_status = '审核中'")
    List<UploadTb> UnderReview(Integer accountId);

    @Select("select title,upload_time,fail,time from upload_tb where account_ID = ${accountId} and approve_status = '审核失败'")
    List<UploadTb> AuditFailed(Integer accountId);

    @Select("select title,upload_time,time from upload_tb where account_ID = ${accountId} and approve_status = '审核通过'")
    List<UploadTb> Approved(Integer accountId);

    List<UploadTb> uploadList();

    @Update("update upload_tb set upload_addr = '${uploadAddr}',title='${title}',description='${description}',approve_status = '${approveStatus}',fail = '${fail}',time = '${time}',patent = '${patent}' where upload_ID = ${uploadID}")
    void updateUpload (Integer uploadID,String uploadAddr,String title,String description,String approveStatus,String fail,String time,String patent);

    @Update("update upload_tb set integral = ${integral} , state = '${state}' where upload_ID = ${uploadID}")
    void updateIntegral (Integer uploadID,Integer integral,String state);

    @Select("select * from upload_tb where upload_ID = ${uploadID}")
    UploadTb uploadTb (Integer uploadID);

    @Select("select patent from upload_tb where upload_ID = ${uploadID}")
    String patent (Integer uploadID);

    @Select("select * from upload_tb where approve_status='审核通过' and state = '未处理'")
    List<UploadTb> ApprovedList();

    @Select("select * from upload_tb where upload_ID = ${uploadID}")
    UploadTb upload(Integer uploadID);

    @Update("update upload_tb set state='已处理' where upload_ID = ${uploadID}")
    void updateStatus (Integer uploadID);

    @Select("select * from upload_tb where approve_status = '审核失败'")
    List<UploadTb> noUpload();

    @Select("select * from upload_tb where approve_status = '审核通过' order by upload_ID desc")
    List<UploadTb> yesUpload();

    @Update("update upload_tb set state='已处理' where upload_ID = ${uploadID}")
    void updateState (Integer uploadID);
}
