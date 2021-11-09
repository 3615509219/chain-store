package com.lq.service;

import com.lq.pojo.PointsTb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-08
 */
public interface PointsTbService extends IService<PointsTb> {

    void exchange (Integer bankId , Integer points);

    List<PointsTb> allPoints(Integer accountId , String handle);

    int point ();

    int personalPoints (Integer accountId);
}
