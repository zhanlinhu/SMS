package com.sms.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class RepData<T> {
    public static final String DEF_ERROR_MESSAGE = "系统繁忙，请稍候再试";
    public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";

    @JSONField(ordinal = 1)
    private int code = 0;

    /**
     * 结果消息，如果调用成功，消息通常为空T
     */
    @JSONField(ordinal = 2)
    private String msg = "ok";

    /*
     * 表单数据长度
     */
    @JSONField(ordinal = 3)
    private int count = 0;

    /**
     * 调用结果
     */
    @JSONField(ordinal = 4)
    private T data;

    public RepData(int code, String msg, int count, T data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }


    private RepData() {
        super();
    }

    public RepData(T data,String msg) {
        this.data = data;
        this.msg = msg;
    }

    public RepData(T data,String msg,int count) {
        this.data = data;
        this.msg = msg;
        this.count = count;
    }

    public static String getDefErrorMessage() {
        return DEF_ERROR_MESSAGE;
    }

    public static String getHystrixErrorMessage() {
        return HYSTRIX_ERROR_MESSAGE;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <E> RepData<E> result(E data, String msg, int count) {
        return new RepData<>(data, msg, count);
    }

    /**
     * 请求成功消息
     *
     * @param data 结果
     * @return
     */
    public static <E> RepData<E> success(E data) {
        return new RepData<>(data, "true");
    }

    public static <E> RepData<E> success(String msg) {
        return new RepData<>(null, msg);
    }

    public static RepData<Boolean> success() {
        return new RepData<>(true, "ok");
    }

    /**
     * 请求成功方法 ，data返回值，msg提示信息
     *
     * @param data 结果
     * @param msg  消息
     * @return
     */
    public static <E> RepData<E> success(E data, String msg) {
        return new RepData<>(data, msg);
    }

    public static <E> RepData<E> success(E data, int count) {
        return new RepData<>(data,"true",count);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> RepData<E> fail(String msg) {
        return new RepData<>(null, (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg);
    }

    public static <E> RepData<E> fail(String msg, Object... args) {
        String message = (msg == null || msg.isEmpty()) ? DEF_ERROR_MESSAGE : msg;
        return new RepData<>(null,String.format(message, args));
    }

    public static <E> RepData<E> fail(E data, int count) {
        return new RepData<>(data,"false",count);
    }

    /**
     * 请求成功方法 ，data返回值，msg提示信息
     *
     * @param data 结果
     * @param msg  消息
     * @return
     */
    public static <E> RepData<E> fail(E data, String msg) {
        return new RepData<>(data, msg);
    }


    public static <E> RepData<E> timeout() {
        return fail(HYSTRIX_ERROR_MESSAGE);
    }


    /**
     * 逻辑处理是否成功
     *
     * @return 是否成功
     */
//    public Boolean getIsSuccess() {
//        return this.code == 0 || this.code == 200;
//    }

    /**
     * 逻辑处理是否失败
     *
     * @return
     */

//    public Boolean getIsError() {
//        return !getIsSuccess();
//    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
