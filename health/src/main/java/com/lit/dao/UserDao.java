package com.lit.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lit.pojo.CheckItem;
import com.lit.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
    //根据用户名查询数据库获取用户信息和关联的角色信息，同时需要查询角色关联的权限信息
     User findByUsername(String username);
}
