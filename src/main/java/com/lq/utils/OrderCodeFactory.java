package com.lq.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 订单编码码生成器，生成32位数字编码，
 * @生成规则 1位单号类型+17位时间戳+14位(用户id加密&随机数)
 */
public class OrderCodeFactory {
    /** 普通客户类别头 */
    private static final String ORDER_CODE = "LQI";
    /** 企业级类别头 */
    private static final String RETURN_ORDER = "LQC";
    /** 补货类别头 */
    private static final String REPLENISHMENT_ORDER = "LQE";
    private static final String CustomOrder = "LQSO";

    private static final String CustomOrder1 = "LQPR";

    private static final String advancePayment = "PR";
    /** 随即编码 */
    private static final int[] r = new int[]{7, 9, 6, 2, 8 , 1, 3, 0, 5, 4};
    /** 用户id和随机数总长度 */
    private static final int maxLength = 1;

    /**
     * 更具id进行加密+加随机数组成固定长度编码
     */
    private static String toCode(Integer id) {
        String idStr = id.toString();
        StringBuilder idsbs = new StringBuilder();
        for (int i = idStr.length() - 1 ; i >= 0; i--) {
            idsbs.append(r[idStr.charAt(i)-'0']);
        }
        return idsbs.append(getRandom(maxLength - idStr.length())).toString();
    }

    /**
     * 生成时间戳
     */
    private static String getDateTime(){
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHSSS");
        return sdf.format(new Date());
    }

    /**
     * 生成固定长度随机码
     * @param n    长度
     */
    private static long getRandom(Integer n) {
        long min = 1,max = 9;
        for (int i = 1; i < n; i++) {
            min *= 10;
            max *= 10;
        }
        long rangeLong = (((long) (new Random().nextDouble() * (max - min)))) + min ;
        return rangeLong;
    }

    /**
     * 生成不带类别标头的编码
     * @param userId
     */
    private synchronized String getCode(Integer userId){
        userId = userId == null ? 10000 : userId;
        return getDateTime() + toCode(userId);
    }

    /**
     * 生成普通客户单号编码
     * @param userId
     */
    public String getOrderCode(Integer userId){
        return ORDER_CODE + getCode(userId);
    }

    /**
     * 生成企业级单号编码
     * @param userId
     */
    public String getReturnCode(Integer userId){
        return RETURN_ORDER + getCode(userId);
    }

    /**
     * 生成补货单号编码
     * @param userId
     */
    public String getReplenishmentCode(Integer userId){
        return REPLENISHMENT_ORDER + getCode(userId);
    }

    public String getCustomOrder(Integer userId){
        return CustomOrder + getCode(userId);
    }

    public String getAdvancePayment(Integer userId){
        return advancePayment + getCode(userId);
    }

    public String getCustomOrder1(Integer userId){
        return CustomOrder1 + getCode(userId);
    }

}
