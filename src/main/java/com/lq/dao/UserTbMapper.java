package com.lq.dao;

import com.lq.pojo.UserTb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tooLate
 * @since 2021-04-22
 */
public interface UserTbMapper extends BaseMapper<UserTb> {
    @Select("select * from user_tb where user_name = '${userName}' and user_password = '${userPassword}'")
    UserTb login (String userName,String userPassword);

}
