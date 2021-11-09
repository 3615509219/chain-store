package com.lq.dao;

import com.lq.pojo.EvaluateImgTb;
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
 * @since 2021-05-24
 */
public interface EvaluateImgTbMapper extends BaseMapper<EvaluateImgTb> {

    @Select("select * from evaluate_img_tb where evaluate_Id = ${evaluateId}")
    List<EvaluateImgTb> selectImg(Integer evaluateId);

    @Delete("delete from evaluate_img_tb where evaluate_Id = ${evaluateId}")
    void delImg (Integer evaluateId);

}
