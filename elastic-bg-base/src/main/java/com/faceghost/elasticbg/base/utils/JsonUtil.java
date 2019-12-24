package com.faceghost.elasticbg.base.utils;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {



    /**
     * 对象转换为json
     * @param obj
     * @return
     */
    public static String toJSON(Object obj) {
        return JSONObject.toJSONString(obj, SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }

}
