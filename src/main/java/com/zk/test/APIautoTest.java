package com.zk.test;

import com.alibaba.fastjson.JSONObject;
import com.zk.domain.ResponseBody;
import com.zk.utils.HttpsUtils;
import com.zk.utils.PropertiesUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIautoTest  {
    private static Map<String,List> map=new HashMap<>();
    private static List<String> responseList=new ArrayList<>();

    /**
     * 报告是否输出预期结果和实际结果json
     */
    public static boolean testLog = PropertiesUtils.getBoolean("testLog");
    /**
     * 报告生成路径
     */
    public static String testOutPutPath = PropertiesUtils.get("testOutPutPath");

    public static String method=PropertiesUtils.get("RequestMethod");

    public JSONObject post(String URL, JSONObject params) {
        long start = System.currentTimeMillis();
        JSONObject jsonObject=new JSONObject();
        ResponseBody responseBody=null;
        List<String> list=new ArrayList<>();
        if("post".equalsIgnoreCase(method)){

            responseBody = HttpsUtils.doPost(URL, params);
            jsonObject=responseBody.getResponseJSON();
        }else if("get".equalsIgnoreCase(method)){
            jsonObject = HttpsUtils.doGet(URL, params);
        }else {
            System.err.println("目前暂不支持"+method+"方法");
            AssertUtil.fail("目前暂不支持"+method+"方法");
            return null;
        }
        responseList.add(responseBody.getResponseStr());
        long end = System.currentTimeMillis();
        try {
            int code = responseBody.getResponseCode();
            ReportUtil.log("接口响应Code:" + code);
            ReportUtil.log("接口查询耗时:" + (end - start) + "ms");

            if (code==200) {
                if (testLog) {

                    ReportUtil.log("服务器返回(实际结果Actual):" + jsonObject);
                }
                if(jsonObject.getJSONObject("data").getIntValue("code")!=200){
                    AssertUtil.fail("接口出错了,请检查接口");
                }
                return  jsonObject;
            } else {
                ReportUtil.log("服务器返回:" + jsonObject);
                AssertUtil.fail("接口出错了,请检查接口");
            }
        } catch (Exception e) {
            ReportUtil.log("接口查询耗时------" + (end - start) + "ms," + "服务器返回:" + jsonObject);
            AssertUtil.fail("接口出错了,请检查接口");
        }finally {

        }
        return null;
    }




    @BeforeClass
    public void beforeClass() {
        System.out.println("----------------------测试开始-----------------------");
    }


    /**
     * 测试数据驱动
     */
    @Test(dataProvider = "dataMethod", dataProviderClass = ExcelDataHeleper.class,retryAnalyzer = MyRetryAnalyzer.class)
    public void test1(Map<String,String> Data) {


        String URL = (String) Data.get("url");
        if(URL==null||"".equals(URL)){
            URL=PropertiesUtils.get("casehost");
        }
        String params = (String) Data.get("parames");
        String 预期结果 = (String) Data.get("预期结果");
        ReportUtil.log("执行case:" + Data.get("case"));
        ReportUtil.log("执行URL:" + URL);
        ReportUtil.log("执行parames:" + params);
        String json1 = 预期结果.replace(" ", "");
        if (testLog) {

            ReportUtil.log("预期结果Expected:" + json1);
        }


        JSONObject jsonObject2 = post(URL, JSONObject.parseObject(params));//服务器的返回

        String json2 = jsonObject2.toString().replace(" ", "");


        AssertUtil.checkJSON(json1, json2);


    }
    @Test
    public void test2(){
        System.out.println("2");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("----------------------测试已完成-----------------------");
        System.out.println("报告生成路径存放在:" + testOutPutPath);
        map.put("预期结果",responseList);
//        ReadExcelUtil.writeExcel2(responseList,"D:\\ideaProject\\crocodile\\src\\main\\resources\\case.xlsx","D:\\ideaProject\\crocodile\\src\\main\\resources\\case.xlsx");
    }
}
