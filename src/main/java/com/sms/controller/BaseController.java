package com.sms.controller;

import com.sms.utils.RepData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {
    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    /**
     * 反馈到前端的
     */
    public static final String RESULT_TRUE = "true";
    public static final String RESULT_FALSE = "false";
    public static final String ERROR = "error";
    public static final String CODE_ERROR = "code_error";
    public static final String OPRATION_ERROR = "操作失败！";
    public static final String ADD_ERROR = "添加失败！";
    public static final String DELETE_ERROR = "删除失败！";
    public static final String UPDATE_ERROR = "修改失败！";
    public static final String PASSWORD_ERROR = "密码错误！";
    public static final String PARAMETER_ERROR = "参数错误！";

    /**
     * 用户类型
     */
    public static final String USER = "user";
    public static final String ADMIN = "admin";
    public static final String TEACHER = "teacher";
    public static final String STUDENT = "student";

    public static final String VERIFY_CODE = "verifyCode";

    /**
     * 成功返回
     *
     * @param data
     * @return
     */
    public <T> RepData<T> success(T data) {
        return RepData.success(data);
    }

    public <T> RepData<T> success(T data,int count) {
        return RepData.success(data,count);
    }

    public RepData<String> success(String msg) {
        return RepData.success(msg);
    }

    public RepData<Boolean> success() {
        return RepData.success();
    }

    /**
     * 失败返回
     *
     * @param msg
     * @return
     */
    public <T> RepData<T> fail(T data, String msg) {
        return RepData.fail(data, msg);
    }

    public <T> RepData<T> fail(String msg) {
        return RepData.fail(msg);
    }
    public <T> RepData<T> fail(T data,int count) {
        return RepData.fail(data,count);
    }


    /**
     * 失败返回
     *
     * @param msg
     * @return
     */
    public <T> RepData<T> fail(String msg, Object... args) {
        return RepData.fail(msg, args);
    }


}
