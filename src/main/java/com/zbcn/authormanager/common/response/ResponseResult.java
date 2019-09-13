package com.zbcn.authormanager.common.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @Description: http 返回值封装
 * @Author: zbcn8
 * @Date: 2019/7/21 10:10
 */
@Data
@AllArgsConstructor
public class ResponseResult<T> {

    private static final long serialVersionUID = -1590228459158445573L;

    private int code;

    private String message;

    private T data;

    private boolean success;


    /**
     * 成功返回 数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult success(T data){
        return new ResponseResult(HttpStatus.OK.value(),null,data,true);
    }

    public static <T> ResponseResult success(HttpStatus status, T data){
        return new ResponseResult(status.value(), status.getReasonPhrase(), data,true);
    }

    /**
     * 成功返回数据
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseResult success(String message){
        return new ResponseResult(HttpStatus.OK.value(),message,null,true);
    }

    /**
     * 返回失败
     * @param status
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(HttpStatus status,String message){

        return new ResponseResult(status.value(),message,null,false);
    }

    /**
     * 默认错误码为400 ，（bad request）
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(String message) {
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(),message,null,false);
    }

    /**
     * 返回错误
     * @param status
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(HttpStatus status){
        return new ResponseResult(HttpStatus.BAD_REQUEST.value(),status.getReasonPhrase(),null,false);
    }

    public String toJsonString(){
        return JSON.toJSONString(this, SerializerFeature.IgnoreNonFieldGetter);
    }

}
