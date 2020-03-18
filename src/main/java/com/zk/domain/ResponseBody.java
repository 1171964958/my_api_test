package com.zk.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class ResponseBody {
    //响应的字符串
    private String responseStr;

    //响应的JSON字符串
    private JSONObject responseJSON;

    //响应码
    private Integer responseCode;

}
