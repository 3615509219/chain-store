package com.lq.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lq.WXPay.*;
import com.lq.dao.*;
import com.lq.pojo.*;
import com.lq.service.AdvancePaymentTbService;
import com.lq.service.PersonRegisterTbService;
import com.lq.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lq.WXPay.WXPayConstants.SUCCESS;

@Controller
@RequestMapping("/WXPay")
public class WXPayController {
    @Resource
    private PurchaseOrderTbMapper purchaseOrderTbMapper;

    @Resource
    private ProductTbMapper productTbMapper;

    @Resource
    private FinishedGoodsListTbMapper finishedGoodsListTbMapper;

    @Resource
    private PersonRegisterTbMapper personRegisterTbMapper;

    @Resource
    private CustomOrderTbMapper customOrderTbMapper;
    @Resource
    private PersonRegisterTbService personRegisterTbService;
    @Resource
    private AdvancePaymentTbService advancePaymentTbService;
    @Resource
    private AdvancePaymentTbMapper advancePaymentTbMapper;

    /*调用支付接口*/
    @RequestMapping("prePay")
    @ResponseBody
    public Map<String, Object> prePay(Integer purchaseOrderNumberId, HttpServletRequest request) {

        // 返回参数
        Map<String, Object> resMap = new HashMap<>();
        //获取请求ip地址
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf(",") != -1) {
            String[] ips = ip.split(",");
            ip = ips[0].trim();
        }
        try {

            System.out.println("id= " + purchaseOrderNumberId);
            // 拼接统一下单地址参数
            Map<String, Object> paraMap = new HashMap<>();
            PurchaseOrderTb order = purchaseOrderTbMapper.selectById(purchaseOrderNumberId);
            String body = order.getOrderHeader();//商品名称
            String orderNum = order.getPurchaseOrderNumber();//订单号
            String openId = purchaseOrderTbMapper.personOpenId(purchaseOrderNumberId);
            Integer money = Integer.valueOf(order.getPurchaseOrderIdTotalPrice());
            System.out.println(money);
//            Integer price = Integer.valueOf(money);
//       Integer price = 1;//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败
            System.out.println("body= " + body);
//       body = new String(body.getBytes("ISO-8859-1"),"UTF-8").toString();
//       System.out.println("body= "+body);
            // 封装11个必需的参数
            paraMap.put("appid", WXPayConstants.APP_ID);
            paraMap.put("mch_id", WXPayConstants.MCH_ID);//商家ID
            paraMap.put("nonce_str", WXPayUtil.generateNonceStr());//获取随机字符串 Nonce Str
            paraMap.put("body", body);     //商品名称
            paraMap.put("out_trade_no", orderNum);//订单号
            paraMap.put("total_fee", money);    //测试改为固定金额
            paraMap.put("spbill_create_ip", ip);
            paraMap.put("notify_url", WXPayConstants.CALLBACK_URL);// 此路径是微信服务器调用支付结果通知路径
            paraMap.put("trade_type", "JSAPI");
            paraMap.put("openid", openId);
            String sign = WXPayUtil.generateSignature(paraMap, WXPayConstants.PATERNER_KEY);//商户密码
            //生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
            paraMap.put("sign", sign);
            //将所有参数(map)转xml格式
            String xml = WXPayUtil.mapToXml(paraMap);
            // 统一下单 https://api.mch.weixin.qq.com/pay/unifiedorder
            String unifiedorder_url = WXPayConstants.UNIFIEDORDER_URL;//统一下单接口
            System.err.println("xml=: " + xml);
            System.out.println("统一下单接口unifiedorder_url:" + unifiedorder_url);
            //发送post请求"统一下单接口"返回预支付id:prepay_id
            String xmlStr = HttpClientUtil.doPostXml(unifiedorder_url, xml);
            System.out.println("xmlStr:" + xmlStr);
            //以下内容是返回前端页面的json数据
            //预支付id
            String prepay_id = "";
            if (xmlStr.indexOf("SUCCESS") != -1) {
                Map<String, Object> map = WXPayUtil.xmlToMap(xmlStr);//XML格式字符串转换为Map
                prepay_id = map.get("prepay_id").toString();
                System.err.println("prepay_id_=  " + prepay_id);
            }
            System.err.println("prepay_id=  " + prepay_id);
            Map<String, Object> payMap = new HashMap<String, Object>();
            // 封装所需6个参数调支付页面
            payMap.put("appId", WXPayConstants.APP_ID);
            payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");//获取当前时间戳，单位秒
            payMap.put("nonceStr", WXPayUtil.generateNonceStr());//获取随机字符串 Nonce Str
            payMap.put("signType", "MD5");
            payMap.put("package", "prepay_id=" + prepay_id);
            //生成带有 sign 的 XML 格式字符串
            String paySign = WXPayUtil.generateSignature(payMap, WXPayConstants.PATERNER_KEY);
            payMap.put("paySign", paySign);
            // 封装正常情况返回数据
            resMap.put("success", true);
            resMap.put("payMap", payMap);
        } catch (Exception e) {
            // 封装异常情况返回数据
            resMap.put("success", false);
            resMap.put("message", "调用统一订单接口错误");
            e.printStackTrace();
        }
        return resMap;
    }

    /*调用定制支付接口*/
    @RequestMapping("customPayment")
    @ResponseBody
    public Map<String, Object> customPayment(Integer customOrderId, HttpServletRequest request) {

        // 返回参数
        Map<String, Object> resMap = new HashMap<>();
        //获取请求ip地址
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf(",") != -1) {
            String[] ips = ip.split(",");
            ip = ips[0].trim();
        }
        try {

            System.out.println("id= " + customOrderId);
            // 拼接统一下单地址参数
            Map<String, Object> paraMap = new HashMap<>();

            CustomOrderTb customOrderTb = customOrderTbMapper.b(customOrderId);
            String body = customOrderTb.getOrderHeader();//商品名称
            String orderNum = customOrderTb.getPurchaseOrderNumber();//订单号
            String openId = customOrderTbMapper.openId(customOrderId);
            Integer money = customOrderTb.getPurchaseOrderIdTotalPrice();
            System.out.println(money);
//            Integer price = Integer.valueOf(money);
//       Integer price = 1;//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败
            System.out.println("body= " + body);
//       body = new String(body.getBytes("ISO-8859-1"),"UTF-8").toString();
//       System.out.println("body= "+body);
            // 封装11个必需的参数
            paraMap.put("appid", WXPayConstants.APP_ID);
            paraMap.put("mch_id", WXPayConstants.MCH_ID);//商家ID
            paraMap.put("nonce_str", WXPayUtil.generateNonceStr());//获取随机字符串 Nonce Str
            paraMap.put("body", body);     //商品名称
            paraMap.put("out_trade_no", orderNum);//订单号
            paraMap.put("total_fee", money);    //测试改为固定金额
            paraMap.put("spbill_create_ip", ip);
            paraMap.put("notify_url", WXPayConstants.CustomizedPaymentCallback);// 此路径是微信服务器调用支付结果通知路径
            paraMap.put("trade_type", "JSAPI");
            paraMap.put("openid", openId);
            String sign = WXPayUtil.generateSignature(paraMap, WXPayConstants.PATERNER_KEY);//商户密码
            //生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
            paraMap.put("sign", sign);
            //将所有参数(map)转xml格式
            String xml = WXPayUtil.mapToXml(paraMap);
            // 统一下单 https://api.mch.weixin.qq.com/pay/unifiedorder
            String unifiedorder_url = WXPayConstants.UNIFIEDORDER_URL;//统一下单接口
            System.err.println("xml=: " + xml);
            System.out.println("统一下单接口unifiedorder_url:" + unifiedorder_url);
            //发送post请求"统一下单接口"返回预支付id:prepay_id
            String xmlStr = HttpClientUtil.doPostXml(unifiedorder_url, xml);
            System.out.println("xmlStr:" + xmlStr);
            //以下内容是返回前端页面的json数据
            //预支付id
            String prepay_id = "";
            if (xmlStr.indexOf("SUCCESS") != -1) {
                Map<String, Object> map = WXPayUtil.xmlToMap(xmlStr);//XML格式字符串转换为Map
                prepay_id = map.get("prepay_id").toString();
                System.err.println("prepay_id_=  " + prepay_id);
            }
            System.err.println("prepay_id=  " + prepay_id);
            Map<String, Object> payMap = new HashMap<String, Object>();
            // 封装所需6个参数调支付页面
            payMap.put("appId", WXPayConstants.APP_ID);
            payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");//获取当前时间戳，单位秒
            payMap.put("nonceStr", WXPayUtil.generateNonceStr());//获取随机字符串 Nonce Str
            payMap.put("signType", "MD5");
            payMap.put("package", "prepay_id=" + prepay_id);
            //生成带有 sign 的 XML 格式字符串
            String paySign = WXPayUtil.generateSignature(payMap, WXPayConstants.PATERNER_KEY);
            payMap.put("paySign", paySign);
            // 封装正常情况返回数据
            resMap.put("success", true);
            resMap.put("payMap", payMap);
        } catch (Exception e) {
            // 封装异常情况返回数据
            resMap.put("success", false);
            resMap.put("message", "调用统一订单接口错误");
            e.printStackTrace();
        }
        return resMap;
    }

    /*定制支付成功回调*/
    @RequestMapping("CustomizedPaymentCallback")
    public JsonResult CustomizedPaymentCallback(HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        System.err.println("微信支付成功,微信发送的advancePaymentCallBack信息,请注意修改订单信息");
        InputStream is = null;
        String resXml = "";
        try {
            is = request.getInputStream();//获取请求的流信息(这里是微信发的xml格式所有只能使用流来读)
            String xml = WXPayUtil.inputStream2String(is);
            Map<String, Object> notifyMap = WXPayUtil.xmlToMap(xml);//将微信发的xml转map
            if (notifyMap.get("return_code").equals("SUCCESS")) {
                System.out.println("进入了if");
                String ordersNum = notifyMap.get("out_trade_no").toString();//商户订单号
                //处理订单状态
                Date time = new Date();
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    System.out.println("订单号" + ordersNum);
                    customOrderTbMapper.updatePay(ordersNum, df2.format(time));
                    CustomOrderTb point = customOrderTbMapper.point(ordersNum);
                    int qty = productTbMapper.qty(ordersNum);
                    int points = personRegisterTbMapper.getPoints(point.getAccountId());
                    personRegisterTbMapper.updatePoints(points+point.getTotalPoints(), point.getAccountId());
                    System.out.println("修改订单完成");
                    result.setData(SUCCESS);
                    result.setData("支付回调成功，修改订单状态为支付成功");
                    //告诉微信服务器收到信息了，不要在调用回调action了========这里很重要回复微信服务器信息用流发送一个xml即可
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    BufferedOutputStream out = new BufferedOutputStream(
                            response.getOutputStream());
                    out.write(resXml.getBytes());
                    out.flush();
                    out.close();
                    System.err.println("返回给微信的值：" + resXml.getBytes());
                    is.close();
                } catch (Exception e) {
                    result.setErrmsg("订单状态修改失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //定制零元
    @PostMapping("CustomizedZeroYuan")
    @ResponseBody
    public void CustomizedZeroYuan(Integer Id) {
        String s = customOrderTbMapper.purchaseOrderNumber1(Id);
        Date time = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        customOrderTbMapper.updatePay(s, df2.format(time));
        CustomOrderTb point = customOrderTbMapper.point(s);
        int qty = productTbMapper.qty(s);
        int points = personRegisterTbMapper.getPoints(point.getAccountId());
        personRegisterTbMapper.updatePoints(points+(point.getTotalPoints() * qty), point.getAccountId());
        System.out.println("修改订单完成");

    }

    /*预付款调用支付接口*/
    @RequestMapping("advancePayment")
    @ResponseBody
    public Map<String, Object> advancePayment(Integer Id, HttpServletRequest request) {


        // 返回参数
        Map<String, Object> resMap = new HashMap<>();
        //获取请求ip地址
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf(",") != -1) {
            String[] ips = ip.split(",");
            ip = ips[0].trim();
        }
        try {
            System.out.println("id= " + Id);
            // 拼接统一下单地址参数
            Map<String, Object> paraMap = new HashMap<>();
            AdvancePaymentTb advancePaymentTb = advancePaymentTbMapper.pppp(Id);
            Integer accountId = advancePaymentTb.getAccountId();
            String body = advancePaymentTb.getOrderHeader();//商品名称
            String orderNum = advancePaymentTb.getAdvancePaymentOrder();//订单号
            System.out.println(advancePaymentTb.getPrice());
//            Integer price = Integer.valueOf(money);
//       Integer price = 1;//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败
            System.out.println("body= " + body);
//       body = new String(body.getBytes("ISO-8859-1"),"UTF-8").toString();
//       System.out.println("body= "+body);
            // 封装11个必需的参数
            PersonRegisterTb personRegisterTb = personRegisterTbMapper.a1(accountId);
            paraMap.put("appid", WXPayConstants.APP_ID);
            paraMap.put("mch_id", WXPayConstants.MCH_ID);//商家ID
            paraMap.put("nonce_str", WXPayUtil.generateNonceStr());//获取随机字符串 Nonce Str
            paraMap.put("body", body);     //商品名称
            paraMap.put("out_trade_no", orderNum);//订单号
            paraMap.put("total_fee", advancePaymentTb.getPrice());    //测试改为固定金额
            paraMap.put("spbill_create_ip", ip);
            paraMap.put("notify_url", WXPayConstants.advancePayment);// 此路径是微信服务器调用支付结果通知路径
            System.out.println(WXPayConstants.advancePayment);
            paraMap.put("trade_type", "JSAPI");
            paraMap.put("openid", personRegisterTb.getPersonOpenId());
            String sign = WXPayUtil.generateSignature(paraMap, WXPayConstants.PATERNER_KEY);//商户密码
            //生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
            paraMap.put("sign", sign);
            //将所有参数(map)转xml格式
            String xml = WXPayUtil.mapToXml(paraMap);
            // 统一下单 https://api.mch.weixin.qq.com/pay/unifiedorder
            String unifiedorder_url = WXPayConstants.UNIFIEDORDER_URL;//统一下单接口
            System.err.println("xml=: " + xml);
            System.out.println("统一下单接口unifiedorder_url:" + unifiedorder_url);
            //发送post请求"统一下单接口"返回预支付id:prepay_id
            String xmlStr = HttpClientUtil.doPostXml(unifiedorder_url, xml);
            System.out.println("xmlStr:" + xmlStr);
            //以下内容是返回前端页面的json数据
            //预支付id
            String prepay_id = "";
            if (xmlStr.indexOf("SUCCESS") != -1) {
                Map<String, Object> map = WXPayUtil.xmlToMap(xmlStr);//XML格式字符串转换为Map
                prepay_id = map.get("prepay_id").toString();
                System.err.println("prepay_id_=  " + prepay_id);
            }
            System.err.println("prepay_id=  " + prepay_id);
            Map<String, Object> payMap = new HashMap<String, Object>();
            // 封装所需6个参数调支付页面
            payMap.put("appId", WXPayConstants.APP_ID);
            payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");//获取当前时间戳，单位秒
            payMap.put("nonceStr", WXPayUtil.generateNonceStr());//获取随机字符串 Nonce Str
            payMap.put("signType", "MD5");
            payMap.put("package", "prepay_id=" + prepay_id);
            //生成带有 sign 的 XML 格式字符串
            String paySign = WXPayUtil.generateSignature(payMap, WXPayConstants.PATERNER_KEY);
            payMap.put("paySign", paySign);
            // 封装正常情况返回数据
            resMap.put("success", true);
            resMap.put("payMap", payMap);
        } catch (Exception e) {
            // 封装异常情况返回数据
            resMap.put("success", false);
            resMap.put("message", "调用统一订单接口错误");
            e.printStackTrace();
        }
        return resMap;
    }

    /*预付款支付成功回调*/
    @RequestMapping("advancePaymentCallBack")
    public JsonResult advancePaymentCallBack(HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        System.err.println("微信支付成功,微信发送的advancePaymentCallBack信息,请注意修改订单信息");
        InputStream is = null;
        String resXml = "";
        try {
            is = request.getInputStream();//获取请求的流信息(这里是微信发的xml格式所有只能使用流来读)
            String xml = WXPayUtil.inputStream2String(is);
            Map<String, Object> notifyMap = WXPayUtil.xmlToMap(xml);//将微信发的xml转map
            if (notifyMap.get("return_code").equals("SUCCESS")) {
                System.out.println("进入了if");
                String ordersNum = notifyMap.get("out_trade_no").toString();//商户订单号
                //处理订单状态
                Date time = new Date();
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    System.out.println("订单号" + ordersNum);
                    advancePaymentTbMapper.updateDeal(df2.format(time), ordersNum);
                    System.out.println("修改订单完成");
                    result.setData(SUCCESS);
                    result.setData("支付回调成功，修改订单状态为支付成功");
                    //告诉微信服务器收到信息了，不要在调用回调action了========这里很重要回复微信服务器信息用流发送一个xml即可
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    BufferedOutputStream out = new BufferedOutputStream(
                            response.getOutputStream());
                    out.write(resXml.getBytes());
                    out.flush();
                    out.close();
                    System.err.println("返回给微信的值：" + resXml.getBytes());
                    is.close();
                } catch (Exception e) {
                    result.setErrmsg("订单状态修改失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*客户端支付成功回调*/
    @RequestMapping("callBack")
    public JsonResult callBack(HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        System.err.println("微信支付成功,微信发送的callback信息,请注意修改订单信息");
        InputStream is = null;
        String resXml = "";
        try {
            is = request.getInputStream();//获取请求的流信息(这里是微信发的xml格式所有只能使用流来读)
            String xml = WXPayUtil.inputStream2String(is);
            Map<String, Object> notifyMap = WXPayUtil.xmlToMap(xml);//将微信发的xml转map
            if (notifyMap.get("return_code").equals("SUCCESS")) {
                System.out.println("进入了if");
                String ordersNum = notifyMap.get("out_trade_no").toString();//商户订单号
                //处理订单状态
                Date time = new Date();
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String orderType = "支付完成";//1支付完成
                try {
                    System.out.println("订单号" + ordersNum);
                    //查询到物品清单
                    List<ProductTb> all = productTbMapper.product(ordersNum);
                    //查询到购买者id
                    int selectAccountId = purchaseOrderTbMapper.selectAccountId(ordersNum);
                    //购买者积分
                    //查询到工厂端id
                    for (ProductTb p : all) {
                        System.out.println("购买者积分" + personRegisterTbMapper.getPoints(selectAccountId));
                        System.out.println("正在修改积分");
                        //查询到上传者id
                        FinishedGoodsListTb finishedGoodsListTb = finishedGoodsListTbMapper.selectFinishedAccountId(p.getPurchaseOrderProductPnId());
                        //上传者积分
                        int uploadPoints = personRegisterTbMapper.getPoints(finishedGoodsListTb.getAccountId());
                        System.out.println("上传者积分" + uploadPoints);
                        Double productWeightRequestCustomer = p.getProductWeightRequestCustomer();
                        System.out.println("物品重量" + productWeightRequestCustomer);
                        //20-29克 重量统一按20计算，30-39克 重量统一按30计算，40-49克 重量统一按40计算，以此类推。
                        if (productWeightRequestCustomer < 20) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                personRegisterTbMapper.updatePoints(uploadPoints + new Double((0.6 * productWeightRequestCustomer) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                System.out.println(uploadPoints + new Double((0.6 * productWeightRequestCustomer) * p.getPurchaseOrderProductTotalQty()).intValue());
                            } else {
                                personRegisterTbMapper.updatePoints(uploadPoints + new Double((0.3 * productWeightRequestCustomer) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                System.out.println(uploadPoints + new Double((0.3 * productWeightRequestCustomer) * p.getPurchaseOrderProductTotalQty()).intValue());
                            }
                            //修改购买者积分
                            personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + 36 * p.getPurchaseOrderProductTotalQty(), selectAccountId);
                            System.out.println(personRegisterTbMapper.getPoints(selectAccountId) + 36 * p.getPurchaseOrderProductTotalQty());
                        }
                        if (productWeightRequestCustomer >= 20 && productWeightRequestCustomer < 50) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 20 && productWeightRequestCustomer < 30) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((20) - 5) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 30 && productWeightRequestCustomer < 40) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((30) - 5) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                    System.out.println(uploadPoints + new Double(0.6 * ((30) - 5) * p.getPurchaseOrderProductTotalQty()).intValue());
                                }
                                if (productWeightRequestCustomer >= 40 && productWeightRequestCustomer < 50) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((40) - 5) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 20 && productWeightRequestCustomer < 30) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((20) - 5) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 30 && productWeightRequestCustomer < 40) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((30) - 5) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                    System.out.println(uploadPoints + new Double(0.3 * ((30) - 5) * p.getPurchaseOrderProductTotalQty()).intValue());
                                }
                                if (productWeightRequestCustomer >= 40 && productWeightRequestCustomer < 50) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((40) - 5) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 20 && productWeightRequestCustomer < 30) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((20) - 10) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 30 && productWeightRequestCustomer < 40) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((30) - 10) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                                System.out.println(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((30) - 10) * p.getPurchaseOrderProductTotalQty()).intValue());
                            }
                            if (productWeightRequestCustomer >= 40 && productWeightRequestCustomer < 50) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((40) - 10) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 50 && productWeightRequestCustomer < 70) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 50 && productWeightRequestCustomer < 60) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((50) - 7) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 60 && productWeightRequestCustomer < 70) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((60) - 7) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 50 && productWeightRequestCustomer < 60) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((50) - 7) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 60 && productWeightRequestCustomer < 70) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((60) - 7) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 50 && productWeightRequestCustomer < 60) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((50) - 20) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 60 && productWeightRequestCustomer < 70) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((60) - 20) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 70 && productWeightRequestCustomer < 90) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 70 && productWeightRequestCustomer < 80) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((70) - 9) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 80 && productWeightRequestCustomer < 90) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((80) - 9) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 70 && productWeightRequestCustomer < 80) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((70) - 9) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 80 && productWeightRequestCustomer < 90) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((80) - 9) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 70 && productWeightRequestCustomer < 80) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((70) - 30) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 80 && productWeightRequestCustomer < 90) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((80) - 30) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 90 && productWeightRequestCustomer < 110) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 90 && productWeightRequestCustomer < 100) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((90) - 11) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 100 && productWeightRequestCustomer < 110) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((100) - 11) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 90 && productWeightRequestCustomer < 100) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((90) - 11) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 100 && productWeightRequestCustomer < 110) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((100) - 11) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 90 && productWeightRequestCustomer < 100) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((90) - 40) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 100 && productWeightRequestCustomer < 110) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((100) - 40) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 110 && productWeightRequestCustomer < 130) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 110 && productWeightRequestCustomer < 120) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((110) - 13) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 120 && productWeightRequestCustomer < 130) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((120) - 13) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 110 && productWeightRequestCustomer < 120) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((110) - 13) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 120 && productWeightRequestCustomer < 130) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((120) - 13) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 110 && productWeightRequestCustomer < 120) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((110) - 50) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 120 && productWeightRequestCustomer < 130) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((120) - 50) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 130 && productWeightRequestCustomer < 150) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 130 && productWeightRequestCustomer < 140) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((130) - 15) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 140 && productWeightRequestCustomer < 150) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((140) - 15) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 130 && productWeightRequestCustomer < 140) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((130) - 15) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 140 && productWeightRequestCustomer < 150) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((140) - 15) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 130 && productWeightRequestCustomer < 140) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((130) - 60) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 140 && productWeightRequestCustomer < 150) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((140) - 60) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 150 && productWeightRequestCustomer < 170) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 150 && productWeightRequestCustomer < 160) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((150) - 17) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 160 && productWeightRequestCustomer < 170) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((160) - 17) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 150 && productWeightRequestCustomer < 160) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((150) - 17) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 160 && productWeightRequestCustomer < 170) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((160) - 17) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 150 && productWeightRequestCustomer < 160) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((150) - 70) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 160 && productWeightRequestCustomer < 170) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((160) - 70) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 170 && productWeightRequestCustomer < 190) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 170 && productWeightRequestCustomer < 180) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((170) - 19) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 180 && productWeightRequestCustomer < 190) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((180) - 19) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 170 && productWeightRequestCustomer < 180) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((170) - 19) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 180 && productWeightRequestCustomer < 190) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((180) - 19) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 170 && productWeightRequestCustomer < 180) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((170) - 80) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 180 && productWeightRequestCustomer < 190) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((180) - 80) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 190 && productWeightRequestCustomer < 210) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 190 && productWeightRequestCustomer < 200) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((190) - 21) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 200 && productWeightRequestCustomer < 210) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((200) - 21) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 190 && productWeightRequestCustomer < 200) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((190) - 21) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 200 && productWeightRequestCustomer < 210) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((200) - 21) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 190 && productWeightRequestCustomer < 200) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((190) - 90) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 200 && productWeightRequestCustomer < 210) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((200) - 90) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 210 && productWeightRequestCustomer < 230) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 210 && productWeightRequestCustomer < 220) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((210) - 23) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 220 && productWeightRequestCustomer < 230) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((220) - 23) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 210 && productWeightRequestCustomer < 220) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((210) - 23) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 220 && productWeightRequestCustomer < 230) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((220) - 23) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 210 && productWeightRequestCustomer < 220) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((210) - 100) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 220 && productWeightRequestCustomer < 230) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((220) - 100) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 230 && productWeightRequestCustomer < 250) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 230 && productWeightRequestCustomer < 240) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((230) - 25) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 240 && productWeightRequestCustomer < 250) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((240) - 25) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 230 && productWeightRequestCustomer < 240) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((230) - 25) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 240 && productWeightRequestCustomer < 250) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((240) - 25) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 230 && productWeightRequestCustomer < 240) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((230) - 110) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 240 && productWeightRequestCustomer < 250) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((240) - 110) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 250 && productWeightRequestCustomer < 270) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 250 && productWeightRequestCustomer < 260) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((250) - 27) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 260 && productWeightRequestCustomer < 270) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((260) - 27) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 250 && productWeightRequestCustomer < 260) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((250) - 27) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 260 && productWeightRequestCustomer < 270) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((260) - 27) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 250 && productWeightRequestCustomer < 260) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((250) - 120) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 260 && productWeightRequestCustomer < 270) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((260) - 120) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 270 && productWeightRequestCustomer < 290) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 270 && productWeightRequestCustomer < 280) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((270) - 29) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 280 && productWeightRequestCustomer < 290) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((280) - 29) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 270 && productWeightRequestCustomer < 280) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((270) - 29) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 280 && productWeightRequestCustomer < 290) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((280) - 29) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 270 && productWeightRequestCustomer < 280) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((270) - 130) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 280 && productWeightRequestCustomer < 290) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((280) - 130) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 290 && productWeightRequestCustomer < 310) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 290 && productWeightRequestCustomer < 300) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((290) - 31) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 300 && productWeightRequestCustomer < 310) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((300) - 31) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 290 && productWeightRequestCustomer < 300) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((290) - 31) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 300 && productWeightRequestCustomer < 310) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((300) - 31) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 290 && productWeightRequestCustomer < 300) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((290) - 140) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 300 && productWeightRequestCustomer < 310) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((300) - 140) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 310 && productWeightRequestCustomer < 330) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 310 && productWeightRequestCustomer < 320) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((310) - 33) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 320 && productWeightRequestCustomer < 330) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((320) - 33) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 310 && productWeightRequestCustomer < 320) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((310) - 33) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 320 && productWeightRequestCustomer < 330) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((320) - 33) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 310 && productWeightRequestCustomer < 320) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((310) - 150) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 320 && productWeightRequestCustomer < 330) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((320) - 150) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 330 && productWeightRequestCustomer < 350) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 330 && productWeightRequestCustomer < 340) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((330) - 35) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 340 && productWeightRequestCustomer < 350) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((340) - 35) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 330 && productWeightRequestCustomer < 340) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((330) - 35) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 340 && productWeightRequestCustomer < 350) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((340) - 35) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 330 && productWeightRequestCustomer < 340) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((330) - 160) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 340 && productWeightRequestCustomer < 350) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((340) - 160) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 350 && productWeightRequestCustomer < 370) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 350 && productWeightRequestCustomer < 360) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((350) - 37) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 360 && productWeightRequestCustomer < 370) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((360) - 37) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 350 && productWeightRequestCustomer < 360) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((350) - 37) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 360 && productWeightRequestCustomer < 370) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((360) - 37) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 350 && productWeightRequestCustomer < 360) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((350) - 170) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 360 && productWeightRequestCustomer < 370) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((360) - 170) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 370 && productWeightRequestCustomer < 390) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 370 && productWeightRequestCustomer < 380) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((370) - 39) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 380 && productWeightRequestCustomer < 390) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((380) - 39) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 370 && productWeightRequestCustomer < 380) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((370) - 39) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 380 && productWeightRequestCustomer < 390) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((380) - 39) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 370 && productWeightRequestCustomer < 380) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((370) - 180) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 380 && productWeightRequestCustomer < 390) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((380) - 180) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 390 && productWeightRequestCustomer < 410) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                if (productWeightRequestCustomer >= 390 && productWeightRequestCustomer < 400) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((390) - 41) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 400 && productWeightRequestCustomer < 410) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((400) - 41) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            } else {
                                if (productWeightRequestCustomer >= 390 && productWeightRequestCustomer < 400) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((390) - 41) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                                if (productWeightRequestCustomer >= 400 && productWeightRequestCustomer < 410) {
                                    personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((400) - 41) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                                }
                            }
                            //修改购买者积分
                            if (productWeightRequestCustomer >= 390 && productWeightRequestCustomer < 400) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((390) - 190) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                            if (productWeightRequestCustomer >= 400 && productWeightRequestCustomer < 410) {
                                personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((400) - 190) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                            }
                        }
                        if (productWeightRequestCustomer >= 410) {
                            if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                                //修改上传者积分
                                personRegisterTbMapper.updatePoints((uploadPoints + 220) * p.getPurchaseOrderProductTotalQty(), finishedGoodsListTb.getAccountId());
                            } else {
                                personRegisterTbMapper.updatePoints((uploadPoints + 110) * p.getPurchaseOrderProductTotalQty(), finishedGoodsListTb.getAccountId());
                            }
                            //修改购买者积分
                            personRegisterTbMapper.updatePoints((personRegisterTbMapper.getPoints(selectAccountId) + 800) * p.getPurchaseOrderProductTotalQty(), selectAccountId);
                        }
                        finishedGoodsListTbMapper.updateTimes(finishedGoodsListTb.getFinishedGoodsPrintTimes() + p.getPurchaseOrderProductTotalQty(), finishedGoodsListTb.getPurchaseOrderProductPnId());
                        System.out.println("原有的打印次数：" + finishedGoodsListTb.getFinishedGoodsPrintTimes());
                        System.out.println("修改过后的打印次数：" + finishedGoodsListTb.getFinishedGoodsPrintTimes() + p.getPurchaseOrderProductTotalQty());
                    }
                    purchaseOrderTbMapper.updatePay(ordersNum, orderType, df2.format(time));
                    System.out.println("修改订单完成");
                    result.setData(SUCCESS);
                    result.setData("支付回调成功，修改订单状态为支付成功");
                    //告诉微信服务器收到信息了，不要在调用回调action了========这里很重要回复微信服务器信息用流发送一个xml即可
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    BufferedOutputStream out = new BufferedOutputStream(
                            response.getOutputStream());
                    out.write(resXml.getBytes());
                    out.flush();
                    out.close();
                    System.err.println("返回给微信的值：" + resXml.getBytes());
                    is.close();
                } catch (Exception e) {
                    result.setErrmsg("订单状态修改失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/getWxUserInfo")
    @ResponseBody
    public static JSONObject getSessionKeyOropenid(String code) {
        //微信端登录code值
        //String wxCode = code;
        //ResourceBundle resource = ResourceBundle.getBundle("weixin");//读取属性文件
        //String requestUrl = resource.getString("url"); //请求地址 https://api.weixin.qq.com/sns/jscode2session
        Map<String, String> requestUrlParam = new HashMap<String, String>();
        requestUrlParam.put("appid", WXConst.appId); //开发者设置中的appId
        requestUrlParam.put("secret", WXConst.appSecret); //开发者设置中的appSecret
        requestUrlParam.put("js_code", code); //小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", WXConst.grantType); //默认参数
        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(UrlUtil.sendPost(WXConst.WxGetOpenIdUrl, requestUrlParam));
        return jsonObject;
    }

    @RequestMapping("/getWxUserInfoM")
    @ResponseBody
    public static JSONObject getMSessionKeyOropenid(String code) {
        //微信端登录code值
        //String wxCode = code;
        //ResourceBundle resource = ResourceBundle.getBundle("weixin");//读取属性文件
        //String requestUrl = resource.getString("url"); //请求地址 https://api.weixin.qq.com/sns/jscode2session
        Map<String, String> requestUrlParam = new HashMap<String, String>();
        requestUrlParam.put("appid", WXConst.appId2); //开发者设置中的appId
        requestUrlParam.put("secret", WXConst.appSecret2); //开发者设置中的appSecret
        requestUrlParam.put("js_code", code); //小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", WXConst.grantType2); //默认参数
        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(UrlUtil.sendPost(WXConst.WxGetOpenIdUrl2, requestUrlParam));
        return jsonObject;
    }

    //零元
    @PostMapping("zeroYuan")
    @ResponseBody
    public void zeroYuan(Integer id) {
        String ordersNum = purchaseOrderTbMapper.id1(id);
        Date time = new Date();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderType = "支付完成";//1支付完成
        try {
            System.out.println("订单号" + ordersNum);
            //查询到物品清单
            List<ProductTb> all = productTbMapper.product(ordersNum);
            //查询到购买者id
            int selectAccountId = purchaseOrderTbMapper.selectAccountId(ordersNum);
            //购买者积分
            //查询到工厂端id
            for (ProductTb p : all) {
                System.out.println("购买者积分" + personRegisterTbMapper.getPoints(selectAccountId));
                System.out.println("正在修改积分");
                //查询到上传者id
                FinishedGoodsListTb finishedGoodsListTb = finishedGoodsListTbMapper.selectFinishedAccountId(p.getPurchaseOrderProductPnId());
                //上传者积分
                int uploadPoints = personRegisterTbMapper.getPoints(finishedGoodsListTb.getAccountId());
                System.out.println("上传者积分" + uploadPoints);
                Double productWeightRequestCustomer = p.getProductWeightRequestCustomer();
                System.out.println("物品重量" + productWeightRequestCustomer);
                //20-29克 重量统一按20计算，30-39克 重量统一按30计算，40-49克 重量统一按40计算，以此类推。
                if (productWeightRequestCustomer < 20) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        personRegisterTbMapper.updatePoints(uploadPoints + new Double((0.6 * productWeightRequestCustomer) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        System.out.println(uploadPoints + new Double((0.6 * productWeightRequestCustomer) * p.getPurchaseOrderProductTotalQty()).intValue());
                    } else {
                        personRegisterTbMapper.updatePoints(uploadPoints + new Double((0.3 * productWeightRequestCustomer) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        System.out.println(uploadPoints + new Double((0.3 * productWeightRequestCustomer) * p.getPurchaseOrderProductTotalQty()).intValue());
                    }
                    //修改购买者积分
                    personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + 36 * p.getPurchaseOrderProductTotalQty(), selectAccountId);
                    System.out.println(personRegisterTbMapper.getPoints(selectAccountId) + 36 * p.getPurchaseOrderProductTotalQty());
                }
                if (productWeightRequestCustomer >= 20 && productWeightRequestCustomer < 50) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 20 && productWeightRequestCustomer < 30) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((20) - 5) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 30 && productWeightRequestCustomer < 40) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((30) - 5) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                            System.out.println(uploadPoints + new Double(0.6 * ((30) - 5) * p.getPurchaseOrderProductTotalQty()).intValue());
                        }
                        if (productWeightRequestCustomer >= 40 && productWeightRequestCustomer < 50) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((40) - 5) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 20 && productWeightRequestCustomer < 30) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((20) - 5) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 30 && productWeightRequestCustomer < 40) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((30) - 5) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                            System.out.println(uploadPoints + new Double(0.3 * ((30) - 5) * p.getPurchaseOrderProductTotalQty()).intValue());
                        }
                        if (productWeightRequestCustomer >= 40 && productWeightRequestCustomer < 50) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((40) - 5) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 20 && productWeightRequestCustomer < 30) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((20) - 10) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 30 && productWeightRequestCustomer < 40) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((30) - 10) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                        System.out.println(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((30) - 10) * p.getPurchaseOrderProductTotalQty()).intValue());
                    }
                    if (productWeightRequestCustomer >= 40 && productWeightRequestCustomer < 50) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((40) - 10) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 50 && productWeightRequestCustomer < 70) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 50 && productWeightRequestCustomer < 60) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((50) - 7) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 60 && productWeightRequestCustomer < 70) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((60) - 7) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 50 && productWeightRequestCustomer < 60) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((50) - 7) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 60 && productWeightRequestCustomer < 70) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((60) - 7) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 50 && productWeightRequestCustomer < 60) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((50) - 20) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 60 && productWeightRequestCustomer < 70) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((60) - 20) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 70 && productWeightRequestCustomer < 90) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 70 && productWeightRequestCustomer < 80) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((70) - 9) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 80 && productWeightRequestCustomer < 90) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((80) - 9) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 70 && productWeightRequestCustomer < 80) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((70) - 9) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 80 && productWeightRequestCustomer < 90) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((80) - 9) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 70 && productWeightRequestCustomer < 80) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((70) - 30) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 80 && productWeightRequestCustomer < 90) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((80) - 30) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 90 && productWeightRequestCustomer < 110) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 90 && productWeightRequestCustomer < 100) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((90) - 11) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 100 && productWeightRequestCustomer < 110) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((100) - 11) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 90 && productWeightRequestCustomer < 100) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((90) - 11) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 100 && productWeightRequestCustomer < 110) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((100) - 11) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 90 && productWeightRequestCustomer < 100) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((90) - 40) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 100 && productWeightRequestCustomer < 110) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((100) - 40) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 110 && productWeightRequestCustomer < 130) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 110 && productWeightRequestCustomer < 120) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((110) - 13) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 120 && productWeightRequestCustomer < 130) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((120) - 13) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 110 && productWeightRequestCustomer < 120) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((110) - 13) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 120 && productWeightRequestCustomer < 130) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((120) - 13) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 110 && productWeightRequestCustomer < 120) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((110) - 50) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 120 && productWeightRequestCustomer < 130) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((120) - 50) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 130 && productWeightRequestCustomer < 150) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 130 && productWeightRequestCustomer < 140) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((130) - 15) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 140 && productWeightRequestCustomer < 150) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((140) - 15) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 130 && productWeightRequestCustomer < 140) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((130) - 15) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 140 && productWeightRequestCustomer < 150) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((140) - 15) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 130 && productWeightRequestCustomer < 140) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((130) - 60) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 140 && productWeightRequestCustomer < 150) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((140) - 60) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 150 && productWeightRequestCustomer < 170) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 150 && productWeightRequestCustomer < 160) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((150) - 17) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 160 && productWeightRequestCustomer < 170) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((160) - 17) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 150 && productWeightRequestCustomer < 160) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((150) - 17) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 160 && productWeightRequestCustomer < 170) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((160) - 17) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 150 && productWeightRequestCustomer < 160) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((150) - 70) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 160 && productWeightRequestCustomer < 170) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((160) - 70) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 170 && productWeightRequestCustomer < 190) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 170 && productWeightRequestCustomer < 180) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((170) - 19) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 180 && productWeightRequestCustomer < 190) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((180) - 19) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 170 && productWeightRequestCustomer < 180) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((170) - 19) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 180 && productWeightRequestCustomer < 190) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((180) - 19) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 170 && productWeightRequestCustomer < 180) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((170) - 80) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 180 && productWeightRequestCustomer < 190) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((180) - 80) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 190 && productWeightRequestCustomer < 210) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 190 && productWeightRequestCustomer < 200) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((190) - 21) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 200 && productWeightRequestCustomer < 210) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((200) - 21) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 190 && productWeightRequestCustomer < 200) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((190) - 21) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 200 && productWeightRequestCustomer < 210) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((200) - 21) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 190 && productWeightRequestCustomer < 200) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((190) - 90) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 200 && productWeightRequestCustomer < 210) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((200) - 90) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 210 && productWeightRequestCustomer < 230) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 210 && productWeightRequestCustomer < 220) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((210) - 23) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 220 && productWeightRequestCustomer < 230) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((220) - 23) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 210 && productWeightRequestCustomer < 220) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((210) - 23) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 220 && productWeightRequestCustomer < 230) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((220) - 23) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 210 && productWeightRequestCustomer < 220) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((210) - 100) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 220 && productWeightRequestCustomer < 230) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((220) - 100) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 230 && productWeightRequestCustomer < 250) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 230 && productWeightRequestCustomer < 240) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((230) - 25) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 240 && productWeightRequestCustomer < 250) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((240) - 25) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 230 && productWeightRequestCustomer < 240) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((230) - 25) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 240 && productWeightRequestCustomer < 250) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((240) - 25) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 230 && productWeightRequestCustomer < 240) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((230) - 110) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 240 && productWeightRequestCustomer < 250) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((240) - 110) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 250 && productWeightRequestCustomer < 270) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 250 && productWeightRequestCustomer < 260) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((250) - 27) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 260 && productWeightRequestCustomer < 270) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((260) - 27) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 250 && productWeightRequestCustomer < 260) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((250) - 27) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 260 && productWeightRequestCustomer < 270) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((260) - 27) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 250 && productWeightRequestCustomer < 260) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((250) - 120) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 260 && productWeightRequestCustomer < 270) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((260) - 120) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 270 && productWeightRequestCustomer < 290) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 270 && productWeightRequestCustomer < 280) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((270) - 29) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 280 && productWeightRequestCustomer < 290) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((280) - 29) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 270 && productWeightRequestCustomer < 280) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((270) - 29) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 280 && productWeightRequestCustomer < 290) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((280) - 29) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 270 && productWeightRequestCustomer < 280) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((270) - 130) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 280 && productWeightRequestCustomer < 290) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((280) - 130) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 290 && productWeightRequestCustomer < 310) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 290 && productWeightRequestCustomer < 300) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((290) - 31) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 300 && productWeightRequestCustomer < 310) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((300) - 31) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 290 && productWeightRequestCustomer < 300) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((290) - 31) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 300 && productWeightRequestCustomer < 310) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((300) - 31) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 290 && productWeightRequestCustomer < 300) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((290) - 140) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 300 && productWeightRequestCustomer < 310) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((300) - 140) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 310 && productWeightRequestCustomer < 330) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 310 && productWeightRequestCustomer < 320) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((310) - 33) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 320 && productWeightRequestCustomer < 330) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((320) - 33) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 310 && productWeightRequestCustomer < 320) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((310) - 33) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 320 && productWeightRequestCustomer < 330) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((320) - 33) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 310 && productWeightRequestCustomer < 320) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((310) - 150) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 320 && productWeightRequestCustomer < 330) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((320) - 150) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 330 && productWeightRequestCustomer < 350) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 330 && productWeightRequestCustomer < 340) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((330) - 35) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 340 && productWeightRequestCustomer < 350) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((340) - 35) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 330 && productWeightRequestCustomer < 340) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((330) - 35) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 340 && productWeightRequestCustomer < 350) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((340) - 35) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 330 && productWeightRequestCustomer < 340) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((330) - 160) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 340 && productWeightRequestCustomer < 350) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((340) - 160) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 350 && productWeightRequestCustomer < 370) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 350 && productWeightRequestCustomer < 360) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((350) - 37) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 360 && productWeightRequestCustomer < 370) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((360) - 37) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 350 && productWeightRequestCustomer < 360) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((350) - 37) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 360 && productWeightRequestCustomer < 370) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((360) - 37) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 350 && productWeightRequestCustomer < 360) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((350) - 170) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 360 && productWeightRequestCustomer < 370) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((360) - 170) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 370 && productWeightRequestCustomer < 390) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 370 && productWeightRequestCustomer < 380) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((370) - 39) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 380 && productWeightRequestCustomer < 390) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((380) - 39) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 370 && productWeightRequestCustomer < 380) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((370) - 39) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 380 && productWeightRequestCustomer < 390) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((380) - 39) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 370 && productWeightRequestCustomer < 380) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((370) - 180) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 380 && productWeightRequestCustomer < 390) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((380) - 180) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 390 && productWeightRequestCustomer < 410) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        if (productWeightRequestCustomer >= 390 && productWeightRequestCustomer < 400) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((390) - 41) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 400 && productWeightRequestCustomer < 410) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.6 * ((400) - 41) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    } else {
                        if (productWeightRequestCustomer >= 390 && productWeightRequestCustomer < 400) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((390) - 41) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                        if (productWeightRequestCustomer >= 400 && productWeightRequestCustomer < 410) {
                            personRegisterTbMapper.updatePoints(uploadPoints + new Double(0.3 * ((400) - 41) * p.getPurchaseOrderProductTotalQty()).intValue(), finishedGoodsListTb.getAccountId());
                        }
                    }
                    //修改购买者积分
                    if (productWeightRequestCustomer >= 390 && productWeightRequestCustomer < 400) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((390) - 190) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                    if (productWeightRequestCustomer >= 400 && productWeightRequestCustomer < 410) {
                        personRegisterTbMapper.updatePoints(personRegisterTbMapper.getPoints(selectAccountId) + new Double(3.6 * ((400) - 190) * p.getPurchaseOrderProductTotalQty()).intValue(), selectAccountId);
                    }
                }
                if (productWeightRequestCustomer >= 410) {
                    if (finishedGoodsListTb.getFinishedGoodsPatent().equals("是")) {
                        //修改上传者积分
                        personRegisterTbMapper.updatePoints((uploadPoints + 220) * p.getPurchaseOrderProductTotalQty(), finishedGoodsListTb.getAccountId());
                    } else {
                        personRegisterTbMapper.updatePoints((uploadPoints + 110) * p.getPurchaseOrderProductTotalQty(), finishedGoodsListTb.getAccountId());
                    }
                    //修改购买者积分
                    personRegisterTbMapper.updatePoints((personRegisterTbMapper.getPoints(selectAccountId) + 800) * p.getPurchaseOrderProductTotalQty(), selectAccountId);
                }
                finishedGoodsListTbMapper.updateTimes(finishedGoodsListTb.getFinishedGoodsPrintTimes() + p.getPurchaseOrderProductTotalQty(), finishedGoodsListTb.getPurchaseOrderProductPnId());
                System.out.println("原有的打印次数：" + finishedGoodsListTb.getFinishedGoodsPrintTimes());
                System.out.println("修改过后的打印次数：" + finishedGoodsListTb.getFinishedGoodsPrintTimes() + p.getPurchaseOrderProductTotalQty());
            }
            purchaseOrderTbMapper.updatePay(ordersNum, "支付完成", df2.format(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
