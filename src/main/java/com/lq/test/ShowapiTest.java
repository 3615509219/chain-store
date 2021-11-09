
package com.lq.test;

import com.lq.ShowApiRequest;

public class ShowapiTest {


    public static void main(String[] args)  {
        //更多说明请访问仓库地址：https://github.com/showapi-public/showapi_sdk_java
        //这里需要替换为你自己的showapi_appid和showapi_sign，你可以在这里找到 https://www.showapi.com/console#/myApp
        String res=new ShowApiRequest("http://route.showapi.com/64-19","691709","dafba4849ef343fd9bd3f7385603acdf")
                .addTextPara("com","auto")
                .addTextPara("nu","75481886698131")
                .post();

        System.out.println(res);

    }

}
