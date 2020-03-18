package com.zk;

import com.zk.utils.PropertiesUtils;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

/**
 * 主程序入口
 */
public class Test1 {

    private static TestNG testNG;
    private static String testngXMLPath = PropertiesUtils.get("testng-xml");
    /**
     * 配置testng
     */
    static {

        testNG = new TestNG();
        List<String> suites = new ArrayList<String>();
        System.out.println("resource:"+testngXMLPath);
        suites.add(testngXMLPath);//此处为xml的绝对路径
        testNG.setTestSuites(suites);
    }

    public static void main(String[] args) {
//        CreateCaseService.createCaseForParams(null,null);
        testNG.run();



    }

}
