package com.lit.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lit.pojo.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface SetMealDao extends BaseMapper<Setmeal> {
   void add(Setmeal setmeal);//添加数据
//
    void setMealAndCheckGroup(Map map);//绑定检查项和检查组关系
//
    List<Integer> findCheckGroupIdBySetMealId(Integer id);//绑定检查项和检查组关系
//
    void deleteAssociation(Integer id);//删除检查项和检查组的关联关系

    List<Map<String,Object>> findSetmealCount();
}
