package com.lq.dao;

import com.lq.pojo.PersonRegisterBankTb;
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
 * @since 2021-05-08
 */
public interface PersonRegisterBankTbMapper extends BaseMapper<PersonRegisterBankTb> {
    @Select("select * from person_register_bank_tb where account_ID = ${accountId}")
    List<PersonRegisterBankTb> allBank(Integer accountId);

    @Delete("delete from person_register_bank_tb where account_ID = ${accountId}")
    void delBank (Integer accountId);
}
