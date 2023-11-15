package com.lit.health_mobile.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lit.health_mobile.pojo.CheckGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckGroupDao {
   CheckGroup findCheckGroupById();
}
