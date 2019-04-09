package com.proflow.web.form;

import com.proflow.web.constant.ResultCode;

import java.io.Serializable;

/**
 * Created by Leonid on 2018/7/3.
 */
public class ResultForm<T> implements Serializable {

    private Integer code;

    private String message;

    private T result;

//    public static final Integer SUCCESS = 1;
//    public static final Integer ERROR = 0;

    public ResultForm() {

    }

    public ResultForm(Integer code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static <T> ResultForm<T> createSuccess(String message, T result) {
        ResultForm<T> resultForm = new ResultForm<T>(ResultCode.SUCCESS, message, result);
        return resultForm;
    }

    public static <T> ResultForm<T> createError(Integer error, String message) {
        ResultForm<T> resultForm = new ResultForm<T>(error, message, null);
        return resultForm;
    }

    public static <T> ResultForm<T> createError(String message) {
        return createError(ResultCode.ERROR, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
