package com.lq.service.impl;

import com.lq.dao.PersonRegisterBankTbMapper;
import com.lq.dao.ReceiverTbMapper;
import com.lq.pojo.HistoricalUserTb;
import com.lq.dao.HistoricalUserTbMapper;
import com.lq.pojo.PersonRegisterTb;
import com.lq.service.HistoricalUserTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lq.service.PersonRegisterTbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-10
 */
@Service
public class HistoricalUserTbServiceImpl extends ServiceImpl<HistoricalUserTbMapper, HistoricalUserTb> implements HistoricalUserTbService {

    @Resource
    private PersonRegisterTbService personRegisterTbService;
    @Resource
    private HistoricalUserTbService historicalUserTbService;
    @Resource
    private PersonRegisterBankTbMapper personRegisterBankTbMapper;
    @Resource
    private ReceiverTbMapper receiverTbMapper;
    //注销
    @Override
    public void cancellation(Integer accountID) {
        //查找当前用户
        PersonRegisterTb personRegisterTb = personRegisterTbService.getById(accountID);
        //创建新的对象
        HistoricalUserTb historicalUserTb = new HistoricalUserTb();
        historicalUserTb.setAccountId(accountID);
        historicalUserTb.setAccountCode(personRegisterTb.getAccountCode());
        historicalUserTb.setAccountName(personRegisterTb.getAccountName());
        historicalUserTb.setCallService(personRegisterTb.getCallService());
        historicalUserTb.setAccountPoints(personRegisterTb.getAccountPoints());
        historicalUserTb.setBuyerCompanyName(personRegisterTb.getBuyerCompanyName());
        historicalUserTb.setBuyerCompanyTaxNumber(personRegisterTb.getBuyerCompanyTaxNumber());
        historicalUserTb.setBuyerPerson(personRegisterTb.getBuyerPerson());
        historicalUserTb.setCity(personRegisterTb.getCity());
        historicalUserTb.setEmailAddr(personRegisterTb.getEmailAddr());
        historicalUserTb.setPersonRegisterDate(personRegisterTb.getPersonRegisterDate());
        historicalUserTb.setPhoneNumber(personRegisterTb.getPhoneNumber());
        historicalUserTb.setState(personRegisterTb.getState());
        //添加进历史用户表
        historicalUserTbService.save(historicalUserTb);
        personRegisterBankTbMapper.delBank(accountID);
        receiverTbMapper.delReceiver(accountID);
        personRegisterTbService.removeById(accountID);
    }
}
