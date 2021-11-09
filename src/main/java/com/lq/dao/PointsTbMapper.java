package com.lq.dao;

import com.lq.pojo.PointsTb;
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
 * @since 2021-05-08
 */
public interface PointsTbMapper extends BaseMapper<PointsTb> {

    @Select("select * from points_tb where account_ID = ${accountId} and handle = '${handle}' order by time desc")
    List<PointsTb> allPoints (Integer accountId , String handle);

    @Select("select * from points_tb where handle = '${handle} ' order by time desc")
    List<PointsTb> webPoints (String handle);

    @Update("update points_tb set handle = '已转账',completed_time = '${completedTime}' where points_Id = ${pointsId}")
    void updateHandle (Integer pointsId ,String completedTime);
    @Select("select * from points_tb where handle = '已转账' order by time desc")
    List<PointsTb> selectHandle();

    @Select("select sum(points) as 总积分 from points_tb where handle = '已转账'")
    int point ();

    @Select("select sum(points) as 个人总积分 from points_tb where account_Id = ${accountId} and handle = '已转账' ")
    int personalPoints(Integer accountId);

    @Select("select distinct  account_Id from points_tb")
    List<PointsTb> userAccountId();

}
