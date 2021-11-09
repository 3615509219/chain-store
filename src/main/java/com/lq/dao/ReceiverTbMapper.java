package com.lq.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.pojo.ReceiverTb;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-01-14
 */
public interface ReceiverTbMapper extends BaseMapper<ReceiverTb> {

    @Select("select * from receiver_tb where account_ID = '${accountId}' order by mr desc")
    List<ReceiverTb> getReceiverTb(Integer accountId);

    void updateRegisterTb (ReceiverTb receiverTb);

    @Select("select * from receiver_tb where account_ID = ${accountId} and receiver_ID = ${receiverId}")
    ReceiverTb re(Integer accountId ,Integer receiverId);

    @Delete("delete from receiver_tb where account_ID = ${accountId}")
    void delReceiver (Integer accountId);

    @Update("update receiver_tb set mr = '${mr}' where receiver_ID = ${receiverId}")
    void updateMr (int mr,Integer receiverId);

    @Select("select * from receiver_tb where receiver_ID = ${receiverId}")
    ReceiverTb mr (Integer receiverId);

    @Select("select * from receiver_tb where account_ID = ${accountId} and mr = '1'")
    ReceiverTb mrReceiver (Integer accountId);
}
