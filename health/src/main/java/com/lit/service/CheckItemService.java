package com.lit.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lit.pojo.CheckItem;


//服务接口
public interface CheckItemService extends IService<CheckItem> {

    //实现分页查询
    IPage<CheckItem> getPage(int currentPage, int pageSize);

  //  IPage<CheckItem> getPage(Integer currentPage, Integer pageSize, CheckItem checkItem);
}
