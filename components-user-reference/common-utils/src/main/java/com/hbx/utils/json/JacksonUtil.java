package com.hbx.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

/**
 * jackson 工具类
 */
@UtilityClass
public class JacksonUtil {
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转 Json
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public static String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Json 转化为实体类
     * @param json
     * @param clazz
     * @return
     * @param <T>
     * @throws JsonProcessingException
     */
    public static <T>T toObject(String json,Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json,clazz);
    }


}
