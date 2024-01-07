package com.hbx.project.hnetdiscback.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * http统一返回实体类
 */
@Data
public class AopResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -4671717664013865848L;

    /**
     * 响应状态码
     */
    public String code;
    /**
     * 响应状态信息
     */
    public String msg;

    /**
     * 响应内容
     */
    public T body;

    /**
     * 响应成功模板方法
     * @param body
     * @return
     */
    public AopResponse<T> success(T body){
        AopResponse<T> response = new AopResponse<>();
        response.setCode("200");
        response.setMsg("请求成功");
        response.setBody(body);
        return response;
    }

    public AopResponse<T> success(){
        AopResponse<T> response = new AopResponse<>();
        response.setCode("200");
        response.setMsg("请求成功");
        return response;
    }

    /**
     * 响应失败模板方法
     * @param msg
     * @return
     */public AopResponse<T> fail(String msg){
        AopResponse<T> response = new AopResponse<>();
        response.setCode("999");
        response.setMsg(msg);
        return response;
    }
}
