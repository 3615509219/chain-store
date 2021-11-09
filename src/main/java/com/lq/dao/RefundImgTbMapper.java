package com.lq.dao;

import com.lq.pojo.RefundImgTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-06-25
 */
public interface RefundImgTbMapper extends BaseMapper<RefundImgTb> {

    @Select("select * from refund_img_tb where refund_Id = ${refundId}")
    List<RefundImgTb> selectRefundImg(Integer refundId);

}
