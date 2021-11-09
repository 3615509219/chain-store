package com.lq.dao;

import com.lq.pojo.UserFeedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-05-17
 */
public interface UserFeedbackMapper extends BaseMapper<UserFeedback> {


    @Select("select * from user_feedback where bz = '待处理'")
    List<UserFeedback> dcl ();

    @Select("select * from user_feedback where bz = '已处理'")
    List<UserFeedback> ycl ();

    @Update("update user_feedback set bz = '已处理',bz_time = '${bzTime}' where feedback_Id = ${feedbackId}")
    void updateBz (Integer feedbackId,String bzTime);
}
