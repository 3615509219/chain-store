package com.lq.controller;


import com.lq.dao.PersonRegisterBankTbMapper;
import com.lq.pojo.PersonRegisterBankTb;
import com.lq.service.PersonRegisterBankTbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tooLate
 * @since 2021-05-08
 */
@Controller
@RequestMapping("/personRegisterBankTb")
public class PersonRegisterBankTbController {

    @Resource
    private PersonRegisterBankTbService personRegisterBankTbService;
    @Resource
    private PersonRegisterBankTbMapper personRegisterBankTbMapper;

    //添加银行卡
    @PostMapping("addBankS")
    @ResponseBody
    public void addBankS (PersonRegisterBankTb personRegisterBankTb){
        personRegisterBankTbService.save(personRegisterBankTb);
    }

    //查询他的银行卡
    @GetMapping("allBankS")
    @ResponseBody
    public List<PersonRegisterBankTb> allBankS(Integer accountId){
        return personRegisterBankTbMapper.allBank(accountId);
    }
}

