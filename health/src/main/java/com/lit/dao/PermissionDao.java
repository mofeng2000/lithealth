package com.lit.dao;

import com.lit.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface PermissionDao {
    Set<Permission> findByRoleId(Integer roleId);
}
