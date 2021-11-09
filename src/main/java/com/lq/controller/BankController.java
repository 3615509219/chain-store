package com.lq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;

@Controller
@RequestMapping("/bank")
public class BankController {

    @GetMapping("getBank")
    @ResponseBody
    public void doPost(HttpServletRequest request, HttpServletResponse response,String bank) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        String appkey = "7451FE3318640025A82F56B3BBE23577";
        String secret = "XJm1nq9VcM7oTstFKXzqNQqdcHI5RTpVL0ghuNW7Xdg=";
        String timestamp = String.valueOf(getLongTimes());

        //银行检测的
        String cardid = bank;
        String parameter = "appkey" + appkey + "cardid" + cardid + "timestamp" + timestamp + "secret" + secret;
        String sign = encryptMd5(parameter);
        String postdata = "appkey=" + appkey + "&cardid=" + cardid + "&timestamp=" + timestamp + "&sign=" + sign + "";
        String postResult = post("https://open.flagyun.com/querybank.ashx", postdata);
        response.getWriter().write( postResult);
    }

    public static String post(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static String encryptMd5(String dataStr) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    public static long getLongTimes() {
        long timeNew = System.currentTimeMillis(); // 13位数的时间戳
        return timeNew;
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response,String bank) throws ServletException, IOException {
        doPost(request, response,bank);
    }


}
