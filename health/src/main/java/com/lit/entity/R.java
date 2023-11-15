package com.lit.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装返回结果
 */
@Data
public class R implements Serializable {
    private boolean flag;//执行结果，true为执行成功 false为执行失败
    private Object data;//返回数据
    private String message;//返回结果信息，主要用于页面提示信息

    public R(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }
    public R(Boolean flag) {
        this.flag = flag;
    }

    public R(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public R(Boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public R(String message) {
        this.flag = false;
        this.message = message;
    }
}
