package com.zk.utils;

public class StringUtil {
    public static boolean isNotEmpty(String str) {
        return null != str && !"".equals(str);
    }

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * @param sourceStr  待替换字符串
     * @param matchStr   匹配字符串
     * @param replaceStr 目标替换字符串
     * @return
     */
    public static String replaceFirst(String sourceStr, String matchStr, String replaceStr) {
        int index = sourceStr.indexOf(matchStr);
        int matLength = matchStr.length();
        int sourLength = sourceStr.length();
        String beginStr = sourceStr.substring(0, index);
        String endStr = sourceStr.substring(index + matLength, sourLength);
        sourceStr = beginStr + replaceStr + endStr;
        return sourceStr;
    }


    /**
     * 正则提取器  by zk
     * @param Str
     * @param before
     * @param after
     * @return
     */
    public static String getWant(String Str,String before,String after){
        int begin = Str.indexOf(before)+before.length();
        int end= Str.replace(Str.substring(0,begin),"").indexOf(after)+begin;
        return Str.substring(begin,end);

    }
}
