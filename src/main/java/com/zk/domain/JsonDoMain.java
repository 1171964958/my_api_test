package com.zk.domain;


import lombok.Data;

/**
 * json校验的实体类
 */
@Data
public class JsonDoMain {

    private  double successKey;
    private  double failKey;
    //json相似度
    private  double likeJSON;
    private  String likeJSONStr;
    private  String msg;


}
