package com.ebai.ebai_cloud_service.model.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Response<T> {
    /**
     * 状态码
     */
    private static String successCode = "200";
    private static String successMsg = "执行成功";
    private static String failCode = "500";
    private static String failMsg = "执行失败";

    /**
     * 提示消息
     */
    private String message;

    /**
     * 状态码
     */
    private String code;

    /**
     * 具体返回的数据
     */
    private T data;

    public Response() {
    }

    private Response(String code, String msg) {
        this.message = msg;
        this.code = code;
    }

    private Response(String code, String message, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    /**
     * 返回成功Response对象
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(successCode, successMsg, data);
    }

    public static <T> Response<T> success(String successMessage, T data) {
        return new Response<>(successCode, successMessage, data);
    }

    public static <T> Response<T> success(String code, String successMessage, T data) {
        return new Response<>(code, successMessage, data);
    }

    /**
     * 返回错误Response对象
     */
    public static <T> Response<T> fail(String failMsg) {
        return new Response<>(failCode, failMsg);
    }

    public static <T> Response<T> fail(String failCode, String failMsg) {
        return new Response<>(failCode, failMsg);
    }

    public static <T> Response<T> fail(String failCode, String failMsg, T data) {
        return new Response<>(failCode, failMsg, data);
    }
}
