package com.lq.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lq.WXPay.*;
import com.lq.dao.*;
import com.lq.pojo.FactoryPoTb;
import com.lq.pojo.FinishedGoodsListTb;
import com.lq.pojo.ProductTb;
import com.lq.pojo.PurchaseOrderTb;
import com.lq.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lq.WXPay.WXPayConstants.SUCCESS;

@Controller
@RequestMapping("/WXPayManufacture")
public class WXPayManufactureController {

    @Resource
    private FactoryPoTbMapper factoryPoTbMapper;

    /*调用支付接口*/
    @RequestMapping("manufacturePay")
    @ResponseBody
    public Map<String, Object> manufacturePay(Integer factoryPoId, HttpServletRequest request) {
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

            System.out.println("id= " + factoryPoId);
            // 拼接统一下单地址参数
            Map<String, Object> paraMap = new HashMap<>();
//            PurchaseOrderTb order = purchaseOrderTbMapper.selectById(purchaseOrderNumberId);
            FactoryPoTb factoryPoTb = factoryPoTbMapper.selectById(factoryPoId);
            String body = factoryPoTb.getOrderStatus();//商品名称
            String orderNum = factoryPoTb.getFactoryPo();//订单号
            String openId = factoryPoTbMapper.manufactureOpenId(factoryPoId);
            Integer money = Integer.valueOf(factoryPoTb.getFactoryPrice());
//            Integer price = Integer.valueOf(money);
//       Integer price = 1;//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败
            System.out.println("body= " + body);
//       body = new String(body.getBytes("ISO-8859-1"),"UTF-8").toString();
//       System.out.println("body= "+body);
            // 封装11个必需的参数
            paraMap.put("appid", WXPayConstants.MANUFACTURE_APP_ID);
            paraMap.put("mch_id", WXPayConstants.MANUFACTURE_MCH_ID);//商家ID
            paraMap.put("nonce_str", WXPayUtil.generateNonceStr());//获取随机字符串 Nonce Str
            paraMap.put("body", body);     //商品名称
            paraMap.put("out_trade_no", orderNum);//订单号
            paraMap.put("total_fee", money);    //测试改为固定金额
            paraMap.put("spbill_create_ip", ip);
            paraMap.put("notify_url", WXPayConstants.MANUFACTURE_CALLBACK_URL);// 此路径是微信服务器调用支付结果通知路径
            paraMap.put("trade_type", "JSAPI");
            paraMap.put("openid", openId);
            String sign = WXPayUtil.generateSignature(paraMap, WXPayConstants.MANUFACTURE_PATERNER_KEY);//商户密码
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
                prepay_id =  map.get("prepay_id").toString();
                System.err.println("prepay_id_=  "+prepay_id);
            }
            System.err.println("prepay_id=  " + prepay_id);
            Map<String, Object> payMap = new HashMap<String, Object>();
            // 封装所需6个参数调支付页面
            payMap.put("appId", WXPayConstants.MANUFACTURE_APP_ID);
            payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp() + "");//获取当前时间戳，单位秒
            payMap.put("nonceStr", WXPayUtil.generateNonceStr());//获取随机字符串 Nonce Str
            payMap.put("signType", "MD5");
            payMap.put("package", "prepay_id=" + prepay_id);
            //生成带有 sign 的 XML 格式字符串
            String paySign = WXPayUtil.generateSignature(payMap, WXPayConstants.MANUFACTURE_PATERNER_KEY);
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
    /*客户端支付成功回调*/
    @RequestMapping("manufactureCallBack")
    public JsonResult manufactureCallBack(HttpServletRequest request, HttpServletResponse response){
        JsonResult result = new JsonResult();
        System.err.println("微信支付成功,微信发送的callback信息,请注意修改订单信息");
        InputStream is = null;
        String resXml = "";
        try {
            is = request.getInputStream();//获取请求的流信息(这里是微信发的xml格式所有只能使用流来读)
            String xml = WXPayUtil.inputStream2String(is);
            Map<String, Object> notifyMap = WXPayUtil.xmlToMap(xml);//将微信发的xml转map
            if(notifyMap.get("return_code").equals("SUCCESS")){
                System.out.println("进入了if");
                String ordersNum = notifyMap.get("out_trade_no").toString();//商户订单号
                //处理订单状态
                Date time = new Date();
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String orderType = "支付完成";//1支付完成
                try {
                    System.out.println("订单号"+ordersNum);
                    factoryPoTbMapper.updatePay(ordersNum,orderType,df2.format(time));
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
                    System.err.println("返回给微信的值："+resXml.getBytes());
                    is.close();
                }catch (Exception e){
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
    public static JSONObject getSessionKeyOropenid(String code){
        //微信端登录code值
        //String wxCode = code;
        //ResourceBundle resource = ResourceBundle.getBundle("weixin");//读取属性文件
        //String requestUrl = resource.getString("url"); //请求地址 https://api.weixin.qq.com/sns/jscode2session
        Map<String,String> requestUrlParam = new HashMap<String,String>();
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
    public static JSONObject getMSessionKeyOropenid(String code){
        //微信端登录code值
        //String wxCode = code;
        //ResourceBundle resource = ResourceBundle.getBundle("weixin");//读取属性文件
        //String requestUrl = resource.getString("url"); //请求地址 https://api.weixin.qq.com/sns/jscode2session
        Map<String,String> requestUrlParam = new HashMap<String,String>();
        requestUrlParam.put("appid", WXConst.appId2); //开发者设置中的appId
        requestUrlParam.put("secret", WXConst.appSecret2); //开发者设置中的appSecret
        requestUrlParam.put("js_code", code); //小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", WXConst.grantType2); //默认参数
        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(UrlUtil.sendPost(WXConst.WxGetOpenIdUrl2, requestUrlParam));
        return jsonObject;
    }
}
