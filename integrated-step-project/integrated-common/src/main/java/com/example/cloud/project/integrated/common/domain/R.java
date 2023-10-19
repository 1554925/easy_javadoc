package com.example.cloud.project.integrated.common.domain;

import com.example.cloud.project.integrated.common.domain.constant.BizExceptionEnum;
import lombok.Data;

/**
 * @author gys
 * @version 1.0
 * @date 2023/10/18 16:16
 */
@Data
public class R<T> {
    private int code;
    private String message;
    private T data;

    /**
     * 成功
     */
    public static final int SUCCESS = BizExceptionEnum.SUCCESS.code();
    /**
     * 失败
     */
    public static final int FAIL = BizExceptionEnum.SYSTEM_ERROR.code();

    public static  <T>  R<T> ok() {
        return newInstance(null, SUCCESS, "success");
    }

    public static <T> R<T> ok(T data) {
        return newInstance(data, SUCCESS, "success");
    }

    public static <T> R<T> ok(T data, String message) {
        return newInstance(data, SUCCESS, message);
    }

    public static <T>  R<T> error() {
        return newInstance(null, FAIL, "error");
    }

    public static  <T>  R<T> error(String message) {
        return newInstance(null, FAIL, message);
    }

    public static <T> R<T> error(BizExceptionEnum bizExceptionEnum) {
        return newInstance(null, bizExceptionEnum.code(), bizExceptionEnum.message());
    }

    public static <T> R<T> error(int code, String message) {
        return newInstance(null, code, message);
    }
    public static <T> R<T> error(int code, String message, T data) {
        return newInstance(data, code, message);
    }

    public static <T> R<T> error(BizExceptionEnum bizExceptionEnum, T data) {
        return newInstance(data, bizExceptionEnum.code(), bizExceptionEnum.message());
    }

    private static <T> R<T> newInstance(T data, int code, String message) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setData(data);
        r.setMessage(message);
        return r;
    }

    public boolean isSuccess() {
        return SUCCESS == this.code;
    }
}
