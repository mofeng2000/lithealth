package com.lit.dao;

import com.lit.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;
@Mapper
public interface RoleDao {
    Set<Role> findByUserId(Integer userId);
}
