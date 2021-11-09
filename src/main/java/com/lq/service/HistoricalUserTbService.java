package com.lq.service;

import com.lq.pojo.HistoricalUserTb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-10
 */
public interface HistoricalUserTbService extends IService<HistoricalUserTb> {

    void cancellation (Integer accountID);

}
