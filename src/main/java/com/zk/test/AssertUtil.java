package com.zk.test;

import com.zk.domain.JsonDoMain;
import com.zk.utils.JSON_Check;
import com.zk.utils.PropertiesUtils;
import org.testng.Assert;

public class AssertUtil {
    /**
     * 要对比的json相似度要达到多少才合格
     */
    final static double checkJsonLike = Double.parseDouble(PropertiesUtils.get("checkJsonLike"));

    public static void contains(String source, String search) {
        Assert.assertTrue(source.contains(search),
                String.format("期待'%s'包含'%s'，实际为不包含.", source, search));
    }

    public static void notContains(String source, String search) {
        Assert.assertFalse(source.contains(search),
                String.format("期待'%s'不包含'%s'，实际为包含.", source, search));
    }

    public static void checkJSON(String json1, String json2) {

        JSON_Check json_check = new JSON_Check();

        JsonDoMain jsonDomain = json_check.campareJsonObject(json1, json2);
        double likeJSON = jsonDomain.getLikeJSON();
        String msg = jsonDomain.getMsg() + jsonDomain.getLikeJSONStr();
        String msg2 = "\n期待JSON相似度为:" + checkJsonLike + "%以上,实际为:" + likeJSON + "%.\n" + msg;
        if (likeJSON >= checkJsonLike) {

            ReportUtil.log(msg2.replace("\n", "<br/>"));
        }


        Assert.assertTrue(likeJSON >= checkJsonLike, "\n期待JSON相似度为:" + checkJsonLike + "%以上,实际为:" + likeJSON + "%.\n" + msg);
    }

    public static void fail(String s) {
        Assert.fail(s);
    }


}
