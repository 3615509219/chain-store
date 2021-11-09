package com.lq.dao;

import com.lq.pojo.NfcTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.pojo.UserTb;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-07-27
 */
public interface NfcTbMapper extends BaseMapper<NfcTb> {

    @Select("select * from nfc_tb where nfc_url = '${nfcUrl}' and nfc_pwd = '${nfcPwd}'")
    NfcTb nfc (String nfcUrl, String nfcPwd);

    @Select("select * from nfc_tb where product_Id = ${productId}")
    List<NfcTb> selectOneNfc(Integer productId);

    @Select("select * from nfc_tb n join product_tb p\n" +
            "on n.product_Id = p.product_Id\n" +
            "join purchase_order_tb po\n" +
            "on p.purchase_order_number = po.purchase_order_number\n" +
            "where po.purchase_order_number = '${purchaseOrderNumber}'")
    List<NfcTb> selectPoNFC (String purchaseOrderNumber);

    @Insert("insert into nfc_tb values (default,'${nfcUrl}','${nfcPwd}',${productId},'${purchaseOrderProductName}')")
    void addNfc (String nfcUrl,String nfcPwd,Integer productId,String purchaseOrderProductName);
    @Update("update nfc_tb set nfc_pwd = '${nfcPwd}' where nfc_Id = ${nfcId}")
    void updPwd (Integer nfcId,String nfcPwd);

    @Select("select * from nfc_tb where nfc_url = '${nfcUrl}'")
    NfcTb nfctb (String nfcUrl);


}
