package com.lq.dao;

import com.lq.pojo.OffTheShelfTb;
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
 * @since 2021-05-11
 */
public interface OffTheShelfTbMapper extends BaseMapper<OffTheShelfTb> {

    @Select("select * from Off_the_shelf_tb where purchase_order_product_pn_ID = ${purchaseOrderProductPnId}")
    OffTheShelfTb select (Integer purchaseOrderProductPnId);

    @Update("update Off_the_shelf_tb set audit_time = '${auditTime}' , examine = '已处理' where shelf_Id = ${shelfId}")
    void upd (String auditTime,Integer shelfId);

    @Select("select * from Off_the_shelf_tb where examine = '待处理' order by application_time desc")
    List<OffTheShelfTb> allUnprocessed ();

    @Select("select * from Off_the_shelf_tb where examine = '已处理' order by audit_time desc")
    List<OffTheShelfTb> allProcessed ();

    @Select("select * from Off_the_shelf_tb where account_ID = ${accountId} and examine = '待处理'")
    List<OffTheShelfTb> xiaJiaZhong(Integer accountId);

    @Update("update Off_the_shelf_tb set reasons_for_removal = '${ReasonsForRemoval}' where shelf_Id = ${shelfId}")
    void updateReasonsForRemoval (String ReasonsForRemoval,Integer shelfId);

}
