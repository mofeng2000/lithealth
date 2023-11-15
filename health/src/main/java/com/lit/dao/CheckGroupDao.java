package com.lit.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lit.pojo.CheckGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CheckGroupDao extends BaseMapper<CheckGroup> {
    void add(CheckGroup checkGroup);//添加数据

    void setCheckGroupAndCheckItem(Map map);//绑定检查项和检查组关系

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);//绑定检查项和检查组关系

    void deleteAssociation(Integer id);//删除检查项和检查组的关联关系
    //根据检查组id删除检查组内容和关联表内容
    //boolean delById(Integer id);
}
