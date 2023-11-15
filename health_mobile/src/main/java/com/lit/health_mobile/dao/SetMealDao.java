package com.lit.health_mobile.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lit.health_mobile.pojo.Setmeal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetMealDao extends BaseMapper<Setmeal> {
    Setmeal findById(Integer id);//绑定检查项和检查组关系
}
