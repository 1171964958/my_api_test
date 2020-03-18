package com.zk.utils;

import java.io.InputStream;
import java.util.Properties;


/**
 * 读取properties配置文件工具类
 *
 * @author Logan
 */
public class PropertiesUtils {
    private static Properties property = new Properties();

    static {
        try (
                InputStream in = PropertiesUtils.class.getResourceAsStream("/config/file.properties");
        ) {
            property.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return property.getProperty(key);
    }

    public static Integer getInteger(String key) {
        String value = get(key);
        return null == value ? null : Integer.valueOf(value);
    }

    public static Boolean getBoolean(String key) {
        String value = get(key);
        return null == value ? null : Boolean.valueOf(value);
    }


}