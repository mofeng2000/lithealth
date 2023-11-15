package com.lit.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lit.pojo.CheckGroup;
import com.lit.pojo.CheckItem;

import java.util.List;


//服务接口
public interface CheckGroupService extends IService<CheckGroup> {
    //添加检查组
     void add(CheckGroup checkGroup, Integer[] checkitemIds);

    //按id查询检查项和检查组
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    void edit(CheckGroup checkGroup,Integer[] checkitemIds);
    //实现分页查询
    IPage<CheckGroup> getPage(int currentPage, int pageSize);
    //根据检查组id删除检查组内容和关联表内容
    void deleteById(Integer id);
    //  IPage<CheckItem> getPage(Integer currentPage, Integer pageSize, CheckItem checkItem);
}
