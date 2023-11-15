package com.lit.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lit.pojo.CheckItem;
import com.lit.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface OrderDao extends BaseMapper<Order> {
    Integer findOrderCountByDate(String date);
    Integer findOrderCountAfterDate(String date);
    Integer findVisitsCountByDate(String date);
    Integer findVisitsCountAfterDate(String date);
    List<Map> findHotSetmeal();
}
