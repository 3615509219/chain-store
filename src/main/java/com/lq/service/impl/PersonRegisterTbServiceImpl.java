package com.lq.service.impl;

import com.lq.pojo.PersonRegisterTb;
import com.lq.dao.PersonRegisterTbMapper;
import com.lq.service.PersonRegisterTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
@Service
public class PersonRegisterTbServiceImpl extends ServiceImpl<PersonRegisterTbMapper, PersonRegisterTb> implements PersonRegisterTbService {
    @Resource
    private PersonRegisterTbService personRegisterTbService;

    @Resource
    private PersonRegisterTbMapper personRegisterTbMapper;

    //添加客户信息
    @Override
    public synchronized void addPerson(PersonRegisterTb personRegister) {
        personRegisterTbService.save(personRegister);
        int[] num = {244,245,246,247,248,249,250,254,264,274,284,294,304,314,324,334,341,342,343,344,345,346,347,348,349,354,364,374,384,394,404,
        414,424,434,440,441,442,443,445,446,447,448,449,454,464,474,484,494,504,514,524,534,540,541,542,543,544,
        545,546,547,548,549,554,564,574,584,594,604,614,624,634,640,641,642,643,644,645,646,647,648,649,654,664,674,
        684,694,704,714,724,734,740,741,742,743,744,745,746,747,748,749,754,764,774,784,794,804,814,824,834,844,854,
        864,874,884,894,904,914,924,934,940,941,942,943,944,945,946,947,948,949,954,964,974,984,994};
        for (int s : num) {
            if (s == personRegister.getAccountId()) {
                personRegisterTbMapper.updateAccountId(personRegister.getAccountId() + 1, personRegister.getAccountId());
            } else {
                System.out.println("no");
            }
        }
    }

    //获取所有用户
    @Override
    public List<PersonRegisterTb> getAllPerson() {
        return personRegisterTbService.list();
    }

    //查询某个人
    @Override
    public PersonRegisterTb getPerson(String personOpenId) {
        return personRegisterTbMapper.getPersonOpenId(personOpenId);
    }

    //修改用户数据
    @Override
    public void updatePersonRegister(PersonRegisterTb personRegister) {
        personRegisterTbMapper.updatePerson(personRegister);
    }

    //查询个人资料
    @Override
    public PersonRegisterTb person(Integer accountId) {
        return personRegisterTbMapper.person(accountId);
    }

    //修改积分
    @Override
    public void updatePoints(Integer accountPoints, Integer accountId) {
        int points = personRegisterTbMapper.getPoints(accountId);
        if (points + accountPoints >= 0) {
            personRegisterTbMapper.updatePoints(points + accountPoints, accountId);
        }
        if (points + accountPoints < 0) {
            personRegisterTbMapper.updatePoints(0, accountId);
        }
    }
}
