package com.lit.utils;

import com.lit.entity.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//作为Springmvc的异常拦截器
@RestControllerAdvice
public class ProjectExceptionAdvice {
    //拦截所有的异常现象
    @ExceptionHandler(Exception.class)
    public R doException(Exception ex){
        //记录日志
        //通知运维
        //通知开发
        ex.printStackTrace();
        return new R("服务器异常，请稍后再试");
    }
}
