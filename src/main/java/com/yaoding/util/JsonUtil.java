package com.yaoding.util;

import jodd.json.JsonSerializer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: yaodingw
 * Date: 2018-09-14
 * Time: 10:35
 */
public class JsonUtil {
    public static String toJson(Object obj){
        JsonSerializer jsonSerializer=new JsonSerializer();
        String result=jsonSerializer.deep(true).excludeNulls(true).serialize(obj);
        return result;
    }



}
