package com.zk.service;

import com.zk.utils.MD5Utils;
import com.zk.utils.PropertiesUtils;
import com.zk.utils.ReadExcelUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成case业务层
 */
public class CreateCaseService {
    private static String params=PropertiesUtils.get("Params");
    private static String casePath=CreateCaseService.class.getResource(PropertiesUtils.get("casePath")).getPath().replaceFirst("/","");
    private static String caseRealPath=PropertiesUtils.get("caseRealPath");
    private static String imgStrList=PropertiesUtils.get("imgList");
    private static String  cardType=PropertiesUtils.get("cardType");

    /**
     * 处理sign和图片和type
     * @return
     */
    public static List<String> getNewParams(List<String> imgList,String Type){
        List<String> paramsList=new ArrayList<>();
        for (String imgUrl : imgList) {

            String url=imgUrl;
            String img="";
            String userId="duguangTB_jack_004e1a3398df4911ae846b7cae21832f";
            String type=Type;
            String s=url+img+userId+type;
            String sign=MD5Utils.getMD5(s);

            String newParams= params.replace("${img}",imgUrl).replace("${type}",Type).replace("${sign}",sign);

            paramsList.add(newParams);
        }
        return paramsList;
    }

    public static void createCaseForParams(List<String> imgList,String Type){
        Map<String,List> map=new HashMap<>();
        if (imgList==null||imgList.size()==0){
            imgList=new ArrayList<String>();
            String[] split = imgStrList.split(",");
            for (String s : split) {
                if(StringUtils.isNotBlank(s)){

                    imgList.add(s);
                }
            }
        }
        if(StringUtils.isBlank(Type)){
            Type=cardType;
        }

        List<String> paramsList=getNewParams(imgList,Type);
        map.put("接口参数(Parames)",paramsList);
        ReadExcelUtil.writeExcel(map,caseRealPath,caseRealPath);

    }

    /**
     * 修改参数
     * @param args
     */


    public static void main(String[] args) {
//        createCaseForParams(null,null);

//        ReadExcelUtil.readRow("D:\\ideaProject\\crocodile\\src\\main\\resources\\config\\case1.xlsx","D:\\ideaProject\\crocodile\\src\\main\\resources\\config\\case1.xlsx");
    }


}
