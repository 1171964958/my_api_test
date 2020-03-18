package com.zk.test;


import com.zk.utils.PropertiesUtils;
import com.zk.utils.ReadExcelUtil;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;


/**
 * 数据驱动加载类
 */
public class ExcelDataHeleper  {
    private static String CasePath= PropertiesUtils.get("casePath");


    /**
     * 数据驱动源  开启多线程用这个注解--> @DataProvider(parallel = true,name ="dataMethod" )
     * @return
     */

//    @DataProvider(parallel = true,name ="dataMethod" )
    @DataProvider(name = "dataMethod")
    public static Object[][] dataMethod() {
        List<Map<String, String>> result = ReadExcelUtil.getExcuteList(CasePath);
        Object[][] files = new Object[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            files[i] = new Object[]{result.get(i)};
        }
        return files;
    }






}
