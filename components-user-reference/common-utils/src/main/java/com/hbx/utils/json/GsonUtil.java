package com.hbx.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import lombok.experimental.UtilityClass;

/**
 * Gson 工具了
 */
@UtilityClass
public class GsonUtil {
    private Gson gson = new Gson();

    /**
     * 对象转 Json
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * Json 转化为实体类
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }


}
