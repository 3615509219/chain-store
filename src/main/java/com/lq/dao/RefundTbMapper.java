package com.lq.dao;

import com.lq.pojo.RefundTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-06-25
 */
public interface RefundTbMapper extends BaseMapper<RefundTb> {
    @Select("select refund_Id from refund_tb order by refund_Id desc limit 1")
    int refundId();

    @Select("select * from refund_tb where application_status = '申请中' order by refund_application_time asc")
    List<RefundTb> applicationInProgress();

    @Select("select * from refund_tb where application_status = '已完成' order by refund_application_time desc")
    List<RefundTb> completed();

    @Update("update refund_tb set application_status = '已完成',bz = '${bz}',refund_application_completed_time = '${time}' where refund_Id = ${refundId}")
    void updateStatus(Integer refundId,String bz,String time);

    @Select("select * from refund_tb where product_Id = ${productId}")
    RefundTb select (Integer productId);
}
