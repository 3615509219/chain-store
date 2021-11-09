package com.lq.service;

import com.lq.pojo.PersonRegisterTb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
public interface PersonRegisterTbService extends IService<PersonRegisterTb> {

    //增加用户
    void addPerson(PersonRegisterTb personRegister);

    //获取所有用户
    List<PersonRegisterTb> getAllPerson();

    //验证用户OpenID
    PersonRegisterTb  getPerson(String personOpenId);

    //修改用户资料
    void updatePersonRegister(PersonRegisterTb personRegister);

    //查询个人资料
    PersonRegisterTb person(Integer accountId);

    //修改积分
    void updatePoints(Integer accountPoints,Integer accountId);


}
