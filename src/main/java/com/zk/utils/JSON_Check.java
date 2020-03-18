package com.zk.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zk.domain.JsonDoMain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * json校验
 */
public class JSON_Check {

    private static StringBuffer sb = new StringBuffer();//成功的
    private static StringBuffer sb1 = new StringBuffer().append("\n");//失败的
    private static double successKey = 0;
    private static double failKey = 0;
    private static double likeJSON = 0;//json相似度
    private static List<String> other = new ArrayList();//排除的key,不做比较

    static {
        other.add("NETZP_YYNL");
//        other.add("WYKSZL");
//        other.add("WYKSCJ");
//        other.add("WYZL");
        other.add("A0177");//身份证号
//        other.add("STATUS");//响应状态
//        other.add("A01106");//政治面貌
        other.add("SFYJS");//招聘类型
//
    }


    /**
     * 
     * @param json1 预期结果
     * @param json2 实际结果
     * @return
     */
    public static JsonDoMain campareJsonObject(String json1, String json2) {
        JsonDoMain json_domain = new JsonDoMain();
        json1 = json1.replace(" ", "");
        json2 = json2.replace(" ", "");
        JSONObject jsonObject = JSONObject.parseObject(json1);
        JSONObject jsonObject1 = JSONObject.parseObject(json2);
        //实际调用方法
        compareJson(jsonObject, jsonObject1, "");

        likeJSON = successKey / (successKey + failKey) * 100;
        json_domain.setLikeJSON(likeJSON);
        json_domain.setLikeJSONStr("\nJSON相似度:" + likeJSON + "%");
        json_domain.setSuccessKey(successKey);
        json_domain.setFailKey(failKey);
        json_domain.setMsg(sb1.toString() + sb.toString());
        sb = new StringBuffer();
        sb1 = new StringBuffer();
        successKey = 0;
        failKey = 0;
        likeJSON = 0;
        return json_domain;
    }


    private static void compareJson(JSONObject json1, JSONObject json2, String key) {

        Iterator<String> i = json1.keySet().iterator();
        String root=key;
        while (i.hasNext()) {
            String newkey = i.next();
            if(other.contains(newkey)){
                continue;
            }
//            if (key.indexOf("[") != -1 && key.indexOf(".") == -1) {
//
//                key = key + "." + newkey;
//
//            } else if (key.indexOf(".") != -1) {
//                key = key.split("\\.")[0] + "." + newkey;
//
//            } else {
//                key = newkey;
//            }

            //优化层级结构输出
            key = ("".equals(key)? key: key+".") + newkey;
            compareJson(json1.get(newkey), json2.get(newkey), key);
            key=root;
        }


    }

    private static void compareJson(Object json1, Object json2, String key) {
        if (json2 == null) {
            json2 = "";
        }
        if (json1 == null) {
            json1 = "";
        }
        if (json1 instanceof JSONObject && json2 instanceof JSONObject) {
            compareJson((JSONObject) json1, (JSONObject) json2, key);
        } else if (json1 instanceof JSONArray && json2 instanceof JSONArray) {
            compareJson((JSONArray) json1, (JSONArray) json2, key);
        } else if (json1 instanceof String) {
            try {

                String json1ToStr = json1.toString();
                String json2ToStr = json2.toString();

                compareJson(json1ToStr, json2ToStr, key);
            } catch (Exception e) {
                e.printStackTrace();
                failKey++;
            }

        } else {
            compareJson(json1.toString(), json2.toString(), key);
        }
    }

    private static void compareJson(String str1, String str2, String key) {
        if (str2 == null) {
            str2 = "";
        }
        if (!str1.equals(str2)) {
            sb1.append("不一致:key:" + key + ",@预期结果Expected:" + str1 + ",@实际结果Actual:" + str2 + "\n");
            failKey++;
        } else {
            sb.append("一致:key:" + key + ",@预期结果Expected:" + str1 + ",@实际结果Actual:" + str2 + "\n");
            successKey++;
        }
    }

    private static void compareJson(JSONArray json1, JSONArray json2, String key) {
        if (json1 != null && json2 != null) {
            Iterator i1 = json1.iterator();
            Iterator i2 = json2.iterator();
            int i = 0;
            while (i1.hasNext()) {
                Object key1 = i1.next();
                Object key2 = null;
                try {
                    key2 = i2.next();
                } catch (Exception e) {

                }
                if (key2 != null) {
                    compareJson(key1, key2, key + "[" + i + "]");
                } else {

                    sb1.append("不一致：key:" + key + "[" + i + "]" + "." + key1 + "  在@实际结果Actual中不存在" + "\n");
                    failKey++;
                }
                i++;
            }


        } else {
            if (json1 == null && json2 == null) {
                sb1.append("不一致：key:" + key + "  在@预期结果Expected和@实际结果Actual中均不存在" + "\n");
                failKey++;
            } else if (json1 == null) {
                sb1.append("不一致：key:" + key + "  在@预期结果Expected中不存在" + "\n");
                failKey++;
            } else if (json2 == null) {
                sb1.append("不一致：key:" + key + "  在@实际结果Actual中不存在" + "\n");
                failKey++;
            } else {
                sb1.append("不一致：key:" + key + "  未知原因" + "\n");
                failKey++;
            }

        }
    }


    public static void main(String[] args) {




        String oldStr = "{\"code\":20,\"data\":{\"code\":200,\"data\":{\"angle\":270,\"data\":{\"发票代码\":\"031001600411\",\"发票号码\":\"34081276\",\"开票日期\":\"2018年01月30日\",\"校验码\":\"82868227744269026228\",\"发票金额\":\"400\",\"大写金额\":\"400\",\"发票税额\":\"0\",\"不含税金额\":\"400.00\",\"受票方名称\":\"深圳招商房地产有限公司\",\"受票方税号\":\"91440300192441811T\",\"受票方地址、电话\":\"\",\"受票方开户行、账号\":\"\",\"销售方名称\":\"星巴克(上海)商业有限公司\",\"销售方税号\":\"91310000088552204P\",\"销售方地址、电话\":\"上海市徐汇区宜山路1009号创新大厦10层05室021-24125888\",\"销售方开户行、账号\":\"花旗银行(中国)有限公司上海分行1782602205\",\"联次\":\"\",\"发票类型\":\"电子普通发票\",\"发票详单\":\"[{\\\"货物或应税劳务、服务名称\\\":\\\"*预付卡销售*星巴克预付费卡销售\\\",\\\"规格型号\\\":\\\"\\\",\\\"单位\\\":\\\"\\\",\\\"数量\\\":\\\"\\\",\\\"单价\\\":\\\"400.00\\\",\\\"金额\\\":\\\"400.00不征税\\\",\\\"税率\\\":\\\"\\\",\\\"税额\\\":\\\"\\\"}]\",\"发票代码解析\":\"{\\\"批次号\\\":\\\"11\\\",\\\"年份\\\":\\\"2016\\\",\\\"税务局代码\\\":\\\"None\\\",\\\"发票行业代码\\\":\\\"None\\\",\\\"金额版\\\":\\\"千万元版\\\",\\\"行政区划代码\\\":\\\"上海市\\\",\\\"发票类别代码\\\":\\\"None\\\"}\"},\"figure\":null,\"height\":2435,\"orgHeight\":3507,\"orgWidth\":2435,\"recquality\":null,\"sid\":\"92eacb08fce2b774eaad8c88119c1a3edad8bf3654cf8575b98c4562dc6bd1e6edf50b98\",\"type\":null,\"width\":3507,\"wordsInfo\":null},\"message\":null,\"successful\":true},\"success\":true,\"message\":\"HSFProviderprocesssucceed.\"}";
        String newStr = "{\"code\":20,\"data\":{\"code\":200,\"data\":{\"data\":{\"受票方税号\":\"91440300192441811T\",\"开票日期\":\"2018年01月30日\",\"销售方名称\":\"星巴克(上海)商业有限公司\",\"联次\":\"\",\"不含税金额\":\"400.00\",\"受票方名称\":\"深圳招商房地产有限公司\",\"销售方税号\":\"91310000088552204P\",\"发票代码解析\":\"{\\\"批次号\\\":\\\"11\\\",\\\"年份\\\":\\\"2016\\\",\\\"税务局代码\\\":\\\"None\\\",\\\"发票行业代码\\\":\\\"None\\\",\\\"金额版\\\":\\\"千万元版\\\",\\\"行政区划代码\\\":\\\"上海市\\\",\\\"发票类别代码\\\":\\\"None\\\"}\",\"校验码\":\"82868227744269026228\",\"发票号码\":\"34081276\",\"受票方开户行、账号\":\"\",\"发票金额\":\"400\",\"销售方地址、电话\":\"上海市徐汇区宜山路1009号创新大厦10层05室021-24125888\",\"发票详单\":\"[{\\\"货物或应税劳务、服务名称\\\":\\\"*预付卡销售*星巴克预付费卡销售\\\",\\\"规格型号\\\":\\\"\\\",\\\"单位\\\":\\\"\\\",\\\"数量\\\":\\\"\\\",\\\"单价\\\":\\\"400.00\\\",\\\"金额\\\":\\\"400.00不征税\\\",\\\"税率\\\":\\\"400.00不征税\\\",\\\"税额\\\":\\\"\\\"}]\",\"大写金额\":\"肆佰圆整\",\"销售方开户行、账号\":\"花旗银行(中国)有限公司上海分行1782602205\",\"发票税额\":\"0\",\"受票方地址、电话\":\"\",\"发票类型\":\"电子普通发票\",\"发票代码\":\"031001600411\"},\"orgWidth\":2435,\"width\":3507,\"angle\":270,\"orgHeight\":3507,\"height\":2435,\"sid\":\"92eacb08fce2b774eaad8c88119c1a3edad8bf3654cf8575b98c4562dc6bd1e6edf50b98\"},\"successful\":true},\"success\":true,\"message\":\"HSF Provider process succeed.\"}";
        JsonDoMain jsonDomain = new JSON_Check().campareJsonObject(oldStr, newStr);

        String msg = jsonDomain.getMsg() + jsonDomain.getLikeJSONStr();
        System.out.println(msg);
        System.out.println("failKey:" + jsonDomain.getFailKey());
        System.out.println("successKey" + jsonDomain.getSuccessKey());
    }


}
