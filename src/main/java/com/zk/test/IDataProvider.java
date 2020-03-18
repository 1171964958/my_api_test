package com.zk.test;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * 读取jsonCase数据类
 */
public class IDataProvider {



    public static Iterator<Object[]> loadFile(File file) {

        JSONReader reader = null;
        JSONArray array = null;
        List<Object[]> list = new ArrayList<Object[]>();
        try {
            reader = new JSONReader(new FileReader(file));
            String jsonString = reader.readString();
            array = JSONArray.parseArray(jsonString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (array == null) {
            System.err.println("指定的文件不存在，或者json格式有误！");
            return null;
        }

        for (Object n : array) {
            Object[] objects = {n};
            list.add(objects);
        }
        return list.iterator();

    }

    public static List<Map<String, JSONObject>> loadFile2(File file) {

        JSONReader reader = null;
        JSONArray array = null;
        List<Map<String,JSONObject>> list = new ArrayList<>();
        Map<String,JSONObject> map=new HashMap();
        try {
            reader = new JSONReader(new FileReader(file));
            String jsonString = reader.readString();
            array = JSONArray.parseArray(jsonString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (array == null) {
            System.err.println("指定的文件不存在，或者json格式有误！");
            return null;
        }


        for (int i = 0; i < array.size(); i++) {
            Object n=array.get(i);
            map.put("case"+(i+1) ,(JSONObject) n);

            list.add(map);
            map=new HashMap<>();
        }


        return list;

    }


    /**
     * 判断文件是否存在
     * @param file
     * @return
     */
    static boolean validFile(File file) {
        return file != null && file.exists() && !file.isDirectory() && file.canRead();
    }


    public static void main(String[] args) {
        File file = new File("D:\\ideaProject\\media-private-regress\\src\\test\\resources\\testres\\api\\TaskAdd_test.json");
        if(validFile(file)){
            Iterator<Object[]> iterator = loadFile(file);
           while (iterator.hasNext()){
               System.out.println( iterator.next());
           }

        }else {
            System.err.println("文件不存在");
        }
    }



}
