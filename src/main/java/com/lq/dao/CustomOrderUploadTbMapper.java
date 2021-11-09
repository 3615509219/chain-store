package com.lq.dao;

import com.lq.pojo.CustomOrderUploadTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-09-15
 */
public interface CustomOrderUploadTbMapper extends BaseMapper<CustomOrderUploadTb> {

    @Delete("delete from custom_order_upload_tb where custom_order_Id = ${customOrderId}")
    void delUpload (Integer customOrderId);

    @Select("select * from custom_order_upload_tb where custom_order_Id = ${customOrderId}")
    List<CustomOrderUploadTb> selectUpload (Integer customOrderId);
}
