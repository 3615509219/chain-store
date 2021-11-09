package com.lq.controller;


import com.lq.dao.FinishedGoodsListTbMapper;
import com.lq.dao.PersonRegisterTbMapper;
import com.lq.pojo.FinishedGoodsListTb;
import com.lq.pojo.PersonRegisterTb;
import com.lq.service.PersonRegisterTbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-01-13
 */
@Controller
@RequestMapping("/personRegisterTb")
public class PersonRegisterTbController {
    @Resource
    private PersonRegisterTbService personRegisterTbService;

    @Resource
    private PersonRegisterTbMapper personRegisterTbMapper;
    @Resource
    private FinishedGoodsListTbMapper finishedGoodsListTbMapper;
    //添加客户信息
    @PostMapping("addPerson")
    @ResponseBody
    public void addPerson(PersonRegisterTb personRegisterTb) {
        personRegisterTbService.addPerson(personRegisterTb);
    }

    //获取所有用户
    @GetMapping("getAllPerson")
    @ResponseBody
    public List<PersonRegisterTb> getAllPerson() {
        return personRegisterTbService.getAllPerson();
    }

    //查询某个人
    @GetMapping("getPerson")
    @ResponseBody
    public PersonRegisterTb getPersonOpenId(String personOpenId) {
        return personRegisterTbService.getPerson(personOpenId);
    }

    //修改用户数据
    @RequestMapping("updatePerson")
    @ResponseBody
    public void updatePersonRegister(PersonRegisterTb personRegisterTb) {
        personRegisterTbService.updatePersonRegister(personRegisterTb);
    }

    //查询查询个人资料
    @GetMapping("personS")
    @ResponseBody
    public PersonRegisterTb personS(Integer accountId) {
        return personRegisterTbService.person(accountId);
    }

    //修改积分
    @PostMapping("updatePointsS")
    @ResponseBody
    public void updatePointsS(Integer accountPoints, Integer accountId) {
        personRegisterTbService.updatePoints(accountPoints,accountId);
    }

    //所有的accountId
    @GetMapping("selectAccountIdS")
    @ResponseBody
    public List<PersonRegisterTb> selectAccountIdS (){
        return personRegisterTbMapper.selectAccountId();
    }

    //用户支付总价
    @GetMapping("PreferentialTotalPrice")
    @ResponseBody
    public Integer PreferentialTotalPrice (Integer accountId){
        String s = personRegisterTbMapper.payPrice(accountId);
        String s1 = personRegisterTbMapper.pay1Price(accountId);
        if (s == null && s1 !=null){
            return Integer.valueOf(s1);
        }
        if (s != null && s1 == null){
            return Integer.valueOf(s);
        }
        if (s == null && s1 == null){
            return 0;
        }
        if (s !=null && s1 != null){
            int i = Integer.valueOf(s);
            int i1 = Integer.valueOf(s1);
            return i+i1;
        }
        return null;
    }
    //用户优惠卷总价
    @GetMapping("PayPriceS")
    @ResponseBody
    public Double PayPriceS (Integer accountId){
        String s = personRegisterTbMapper.patP(accountId);
        String s1 = personRegisterTbMapper.patP1(accountId);
        if (s == null && s1 !=null){
            return Double.valueOf(s1);
        }
        if (s != null && s1 == null){
            return Double.valueOf(s);
        }
        if (s == null && s1 == null){
            return 0.0;
        }
        if (s !=null && s1 != null){
            Double i = Double.valueOf(s);
            Double i1 = Double.valueOf(s1);
            return i+i1;
        }
        return null;
    }
    //用户有几个模型
    @GetMapping("numMoXing")
    @ResponseBody
    public List<FinishedGoodsListTb> numMoXing (Integer accountId){
       return finishedGoodsListTbMapper.numMoXing(accountId);
    }
}

