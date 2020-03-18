package com.zk.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatUtils {
    /**
     * @param time
     * @throws
     * @Title: longToString
     * @Description:把13位的毫秒值转化为String类型格式化的时间格式
     * @return:
     */
    public static String longToString(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDate = sdf.format(date);
        return newDate;

    }

    /**
     * @param time
     * @throws
     * @Title: DateToString
     * @Description:
     * @return:
     */
    public static String DateToString(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newDate = sdf.format(time);
        return newDate;
    }

    public static String DateToString(Date time, String formatter) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        String newDate = sdf.format(time);
        return newDate;
    }

    /**
     * @param stringTime
     * @throws
     * @Title: parseDate
     * @Description: 把String解析格式的时间转化为date
     * @return:
     */
    public static Date parseDate(String stringTime) {
        Date date = null;
        if (StringUtils.isNotBlank(stringTime)) {
            String[] pattern = new String[]{"yyyy年MM月dd日", "yyyy年MM月dd",
                    "yyyy-MM", "yyyy年MM月dd日", "yyyyMM", "yyyy/MM", "yyyyMMdd",
                    "yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMddHHmmss",
                    "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
                    "yyyy/MM/dd HH:mm:ss",
                    "MM月dd日", "MM月dd",
                    "MM", "MM月dd日", "MM", "MMdd",
                    "MM-dd", "/MM/dd", "MMddHHmmss",
                    "MM-dd HH:mm:ss", "MM-dd HH:mm",
                    "MM/dd HH:mm:ss",
            };
            try {
                date = DateUtils.parseDate(stringTime, pattern);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;

    }

    /**
     * @param stringTime
     * @throws
     * @Description: 把String格式的时间转化为date
     * @return:
     */
    public static Date formatDate(String stringTime) {
        Date date = null;
        if (StringUtils.isNotBlank(stringTime)) {
            String[] pattern = new String[]{"yyyy年MM月dd日HH时mm分ss秒",
                    "yyyy年MM月dd日 HH时mm分ss秒", "yyyy年MM月dd日HH时mm分",
                    "yyyy年MM月dd日 HH时mm分", "yyyy年MM月dd日 HH时", "yyyy年MM月dd日HH时",
                    "yyyy年MM月dd日", "yyyy年MM月dd日HH:mm:ss",
                    "yyyy年MM月dd日 HH:mm:ss", "yyyy年MM月dd日HH:mm",
                    "yyyy年MM月dd日 HH:mm", "yyyy年MM月dd日 HH", "yyyy年MM月dd日HH",
                    "yyyy-MM-dd HH时mm分ss秒", "yyyy-MM-dd HH时mm分",
                    "yyyy-MM-dd HH时", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss",
                    "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH",
                    "yyyy/MM/dd HH时mm分ss秒", "yyyy/MM/dd HH时mm分",
                    "yyyy/MM/dd HH时", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
                    "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH", "yyyy.MM.dd HH:mm:ss",
                    "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH", "yyyy.MM.dd",
                    "yyyyMMdd",};
            try {
                date = DateUtils.parseDate(stringTime, pattern);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;

    }

    /**
     * @param stringTime
     * @throws
     * @Description: 从字符串中截取出正确的时间
     * @return:
     */
    public static Date cutDate(String stringTime) {
        String regs[] = {"\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}年\\d{2}月\\d{2}日\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}时\\d{2}分",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}时\\d{2}分",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{1}时\\d{2}分",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}时\\d{2}分",
                "\\d{4}年\\d{2}月\\d{2}日\\d{2}时\\d{2}分",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}时",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{1}时",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}时",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{1}时",
                "\\d{4}年\\d{2}月\\d{2}日\\d{2}时", "\\d{4}年\\d{2}月\\d{2}日",
                "\\d{4}年\\d{2}月\\d{1}日", "\\d{4}年\\d{1}月\\d{2}日",
                "\\d{4}年\\d{1}月\\d{1}日",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}:\\d{1}:\\d{2}",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}:\\d{1}:\\d{2}",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}:\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{1}:\\d{2}",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}:\\d{2}",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{1}:\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\d{2}:\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}",
                "\\d{4}年\\d{2}月\\d{2}日\\s\\d{1}",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{2}",
                "\\d{4}年\\d{1}月\\d{2}日\\s\\d{1}",
                "\\d{4}年\\d{2}月\\d{2}日\\d{2}",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}", "\\d{4}-\\d{2}-\\d{2}",
                "\\d{4}-\\d{2}-\\d{1}", "\\d{4}-\\d{1}-\\d{2}",
                "\\d{4}-\\d{1}-\\d{1}",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}-\\d{1}-\\d{1}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{2}时",
                "\\d{4}-\\d{2}-\\d{2}\\s\\d{1}时",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{2}时",
                "\\d{4}-\\d{1}-\\d{2}\\s\\d{1}时", "\\d{4}.\\d{2}.\\d{2}",
                "\\d{4}.\\d{2}.\\d{1}", "\\d{4}.\\d{1}.\\d{2}",
                "\\d{4}.\\d{1}.\\d{1}",
                "\\d{4}.\\d{2}.\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}.\\d{2}.\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}.\\d{1}.\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}.\\d{1}.\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}.\\d{1}.\\d{1}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}.\\d{2}.\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{4}.\\d{2}.\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{4}.\\d{1}.\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{4}.\\d{1}.\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{4}.\\d{2}.\\d{2}\\s\\d{2}",
                "\\d{4}.\\d{2}.\\d{2}\\s\\d{1}",
                "\\d{4}.\\d{1}.\\d{2}\\s\\d{2}",
                "\\d{4}.\\d{1}.\\d{2}\\s\\d{1}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}时",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{1}时",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{2}时",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{1}时",
                "\\d{4}/\\d{2}/\\d{2}",
                "\\d{4}/\\d{2}/\\d{1}",
                "\\d{4}/\\d{1}/\\d{2}",
                "\\d{4}/\\d{1}/\\d{1}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{2}",
                "\\d{4}/\\d{2}/\\d{2}\\s\\d{1}",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{2}",
                "\\d{4}/\\d{1}/\\d{2}\\s\\d{1}",
                "\\d{2}月\\d{2}日\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{2}月\\d{2}日\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{1}月\\d{2}日\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{1}月\\d{2}日\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{2}月\\d{2}日\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{2}月\\d{2}日\\s\\d{2}时\\d{2}分",
                "\\d{1}月\\d{2}日\\s\\d{2}时\\d{2}分",
                "\\d{1}月\\d{2}日\\s\\d{1}时\\d{2}分",
                "\\d{1}月\\d{2}日\\s\\d{2}时\\d{2}分",
                "\\d{2}月\\d{2}日\\d{2}时\\d{2}分",
                "\\d{2}月\\d{2}日\\s\\d{2}时",
                "\\d{2}月\\d{2}日\\s\\d{1}时",
                "\\d{1}月\\d{2}日\\s\\d{2}时",
                "\\d{1}月\\d{2}日\\s\\d{1}时",
                "\\d{2}月\\d{2}日\\d{2}时", "\\d{4}年\\d{2}月\\d{2}日",
                "\\d{2}月\\d{1}日", "\\d{4}年\\d{1}月\\d{2}日",
                "\\d{1}月\\d{1}日",
                "\\d{2}月\\d{2}日\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{2}月\\d{2}日\\s\\d{2}:\\d{1}:\\d{2}",
                "\\d{1}月\\d{2}日\\s\\d{2}:\\d{1}:\\d{2}",
                "\\d{1}月\\d{2}日\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{2}月\\d{2}日\\d{2}:\\d{2}:\\d{2}",
                "\\d{2}月\\d{2}日\\s\\d{2}:\\d{2}",
                "\\d{2}月\\d{2}日\\s\\d{1}:\\d{2}",
                "\\d{1}月\\d{2}日\\s\\d{2}:\\d{2}",
                "\\d{1}月\\d{2}日\\s\\d{1}:\\d{2}",
                "\\d{2}月\\d{2}日\\d{2}:\\d{2}",
                "\\d{2}月\\d{2}日\\s\\d{2}",
                "\\d{2}月\\d{2}日\\s\\d{1}",
                "\\d{1}月\\d{2}日\\s\\d{2}",
                "\\d{1}月\\d{2}日\\s\\d{1}",
                "\\d{2}月\\d{2}日\\d{2}",
                "\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{2}-\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{1}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{1}-\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{2}-\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{2}-\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{2}-\\d{2}\\s\\d{2}", "\\d{4}-\\d{2}-\\d{2}",
                "\\d{2}-\\d{1}", "\\d{4}-\\d{1}-\\d{2}",
                "\\d{1}-\\d{1}",
                "\\d{2}-\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{2}-\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{1}-\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{1}-\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{1}-\\d{1}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{2}-\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{2}-\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{1}-\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{1}-\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{2}-\\d{2}\\s\\d{2}时",
                "\\d{2}-\\d{2}\\s\\d{1}时",
                "\\d{1}-\\d{2}\\s\\d{2}时",
                "\\d{1}-\\d{2}\\s\\d{1}时", "\\d{4}.\\d{2}.\\d{2}",
                "\\d{2}.\\d{1}", "\\d{4}.\\d{1}.\\d{2}",
                "\\d{1}.\\d{1}",
                "\\d{2}.\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{2}.\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{1}.\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{1}.\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{1}.\\d{1}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{2}.\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{2}.\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{1}.\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{1}.\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{2}.\\d{2}\\s\\d{2}",
                "\\d{2}.\\d{2}\\s\\d{1}",
                "\\d{1}.\\d{2}\\s\\d{2}",
                "\\d{1}.\\d{2}\\s\\d{1}",
                "\\d{2}/\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{2}/\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{1}/\\d{2}\\s\\d{2}时\\d{2}分\\d{2}秒",
                "\\d{1}/\\d{2}\\s\\d{1}时\\d{2}分\\d{2}秒",
                "\\d{2}/\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{2}/\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{1}/\\d{2}\\s\\d{2}时\\d{2}分",
                "\\d{1}/\\d{2}\\s\\d{1}时\\d{2}分",
                "\\d{2}/\\d{2}\\s\\d{2}时",
                "\\d{2}/\\d{2}\\s\\d{1}时",
                "\\d{1}/\\d{2}\\s\\d{2}时",
                "\\d{1}/\\d{2}\\s\\d{1}时",
                "\\d{2}/\\d{2}",
                "\\d{2}/\\d{1}",
                "\\d{1}/\\d{2}",
                "\\d{1}/\\d{1}",
                "\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{2}/\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{1}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}",
                "\\d{1}/\\d{2}\\s\\d{1}:\\d{2}:\\d{2}",
                "\\d{2}/\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{2}/\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{1}/\\d{2}\\s\\d{2}:\\d{2}",
                "\\d{1}/\\d{2}\\s\\d{1}:\\d{2}",
                "\\d{2}/\\d{2}\\s\\d{2}",
                "\\d{2}/\\d{2}\\s\\d{1}",
                "\\d{1}/\\d{2}\\s\\d{2}",
                "\\d{1}/\\d{2}\\s\\d{1}",
        };

        String str = "";
        Date date = null;
        for (String reg : regs) {
            String temp = match(reg, stringTime);
            if (temp.length() > str.length()) {
                str = temp;
                if (!"".equals(str)) {
                    // System.err.println(reg);
                    date = formatDate(str);
                }
            }

        }
        return date;

    }

    public static String match(String reg, String stringTime) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(stringTime);
        String s = "";
        if (m.find()) {
            s += m.group();
        }
        return s;
    }

    public static String addMonth(String time) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = parseDate(time);
        date = DateUtils.addDays(date, 1);
        String first = df.format(date);
        return first;
    }


    public static void main(String[] args) {
        // String time = "20131101";
        // System.out.println(formatDate(time));
        // long timeLong = formatDate(time).getTime();
        // System.out.println(timeLong/1000);
        // System.err.println(new Date(1482328437000l));
        // System.err.println(cutDate("2016-10-6 "));
        // System.err.println(parseDate("2016年03月01日"));
        // System.err.println(cutDate("2016/08/15").getTime());
        // String targetId = "http://news.sohu.com/20160323/n441639453.shtml";
        // if (targetId.contains("/n") && targetId.contains(".shtml")) {
        // String newsId = targetId.substring(targetId.lastIndexOf("/n") + 2,
        // targetId.lastIndexOf(".shtml"));
        // System.err.println(newsId);
        // }
        // Date now = new Date();
        // long endTime = now.getTime() + 7 * 24 * 60 * 60 * 1000;
        String stringTime = "1995-01-20 00:00:00.0";
        String s = getYHtime(stringTime);
        System.out.println(s);
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }


    public static String getYHtime(String stringTime) {
        String[] split = new String[]{};
        if (stringTime != null && !"".equals(stringTime)) {

            if (stringTime.contains("-")) {

                split = stringTime.split("-");
                return split[0] + "." + split[1];

            } else {
                return stringTime;
            }

        }
        return stringTime;


    }

}
