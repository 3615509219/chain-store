package com.lq;

import com.alibaba.druid.support.json.JSONUtils;
import com.lq.dao.FinishedGoodsListTbMapper;
import com.lq.dao.PersonRegisterTbMapper;
import com.lq.dao.ProductTbMapper;
import com.lq.dao.PurchaseOrderTbMapper;
import com.lq.pojo.ProductTb;
import com.lq.utils.FastDFSClient;
import com.lq.utils.OrderCodeFactory;
import org.assertj.core.util.Lists;

import javax.annotation.Resource;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class demo {

    //入口函数
    public static void main(String[] args) {
        String i = "12345678911";
        String i1 = "22";
        if (i.length() == 11){
            System.out.println(i.substring(7));
        }else {
            System.out.println(i1);
        }
//        Date time=new Date();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd "+"00:00:00");
//        System.out.println(df.format(time));
//        Double d = 100.01;
//        int i = 100;
//        System.out.println(new Double(d*i).intValue());
//        String minDateStr = "";
//        Calendar calc =Calendar.getInstance();
//        try {
//            calc.setTime(df.parse(df.format(time)));
//            calc.add(calc.DATE, +45);
//            Date minDate = calc.getTime();
//            minDateStr = df.format(minDate);
//            System.out.println(minDateStr);
//        } catch (ParseException e1) {
//            e1.printStackTrace();
//        }
    }
//        String s = "gkm";
//        String s1 = "Gkm";
//        if (s.equals(s1)){
//            System.out.println("一样");
//        }else{
//            System.out.println("不一样");
//        }
//    }
//        String s = "gkm";
//        int i = Integer.parseInt(s);
//        System.out.println(i);
//
//        OrderCodeFactory orderCodeFactory = new OrderCodeFactory();

//        System.out.println(orderCodeFactory.getOrderCode(219));
//        System.out.println(orderCodeFactory.getReturnCode(200));

//        List<String> list = new ArrayList<String>();
//        list.add("aaa");
//        list.add("bbb");
//        list.add("ccc");
//        for (String l: list
//             ) {
//            System.out.println(l);
//        }
    //输出打印
//        System.out.println(1.254999999/1.58779999);
//        Double d = ((58.00)-10)/10;
//        int i = (int) ;
//        long i1 = java.lang.Math.round(d);
//        int i2 = (int) (i1*10);
//        System.out.println(d);
//        System.out.println(i1);
//        System.out.println(i2);
//        System.out.println(java.lang.Math.round(d));
//        System.out.println(new Double (3.6*((30.00)-10)*3).intValue());
//        System.out.println(new Double (3.6*()));
//        DecimalFormat obj = new DecimalFormat();
//        System.out.println(obj .format(d));
//        System.out.println(new Double ((31.00)-10)/10);
//        System.out.println(new Double (3.6*(((31.00)-10)/10*10)).intValue());
//        System.out.println("姓名：万山木子");
//        System.out.println("性别：男");
//        System.out.println("年龄：18");
//        System.out.println("爱好：女人");
//        double d= 1.7;
//        int i = new Double(d).intValue();
//        double q =0.6 * 18;
//        int i = new Double(q).intValue();
//        System.out.println(q);
//        System.out.println(i);
//        String str = null;
//        if(str==null || str.length()<=0){
//            System.out.println("string为空！");
//        }
//        if(str != null && str.length()!= 0){
//            System.out.println("String不为空！");
//        }
//        List<String> list = Lists.newArrayList();
//        list.add("测试1");
//        list.add("测试2");
//        list.add("测试3");
//        String join = String.join(",", list);
//        System.out.println(join);

//        System.out.println(df.format(time));
//        System.out.println("LQI"+df.format(time)+1001);
//
//        String s = "LQI2021040110001";
//        int result = Integer.valueOf(s.substring(s.length()-5));
//        System.out.println(result);


}
