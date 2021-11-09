package com.lq.service.impl;

import com.lq.dao.PersonRegisterTbMapper;
import com.lq.pojo.PersonRegisterBankTb;
import com.lq.pojo.PointsTb;
import com.lq.dao.PointsTbMapper;
import com.lq.service.PersonRegisterBankTbService;
import com.lq.service.PointsTbService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tooLate
 * @since 2021-05-08
 */
@Service
public class PointsTbServiceImpl extends ServiceImpl<PointsTbMapper, PointsTb> implements PointsTbService {

    @Resource
    private PersonRegisterBankTbService personRegisterBankTbService;
    @Resource
    private PointsTbService pointsTbService;
    @Resource
    private PointsTbMapper pointsTbMapper;
    @Resource
    private PersonRegisterTbMapper personRegisterTbMapper;
    //兑换积分
    @Override
    public void exchange(Integer bankId, Integer points) {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PointsTb pointsTb = new PointsTb();
        PersonRegisterBankTb byId = personRegisterBankTbService.getById(bankId);
        pointsTb.setAccountId(byId.getAccountId());
        pointsTb.setName(byId.getName());
        pointsTb.setPoints(points);
        pointsTb.setBankName(byId.getBankName());
        pointsTb.setCardId(byId.getCardId());
        pointsTb.setCardName(byId.getCardName());
        pointsTb.setProvince(byId.getProvince());
        pointsTb.setCity(byId.getCity());
        pointsTb.setTel(byId.getTel());
        pointsTb.setHandle("待处理");
        pointsTb.setTime(df.format(time));
        int points1 = personRegisterTbMapper.getPoints(byId.getAccountId());
        personRegisterTbMapper.updatePoints(points1-points,byId.getAccountId());
        pointsTbService.save(pointsTb);
    }

    //查询用户兑换的积分
    @Override
    public List<PointsTb> allPoints(Integer accountId, String handle) {
        return pointsTbMapper.allPoints(accountId, handle);
    }


    //总积分
    @Override
    public int point() {
        return pointsTbMapper.point();
    }

    //个人总积分
    @Override
    public int personalPoints(Integer accountId) {
        return pointsTbMapper.personalPoints(accountId);
    }
}
