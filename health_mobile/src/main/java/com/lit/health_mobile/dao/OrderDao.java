package com.lit.health_mobile.dao;

import com.lit.health_mobile.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface OrderDao {
     void add(Order order);
     List<Order> findByCondition(Order order);
     Map findById4Detail(Integer id);
     Integer findOrderCountByDate(String date);
     Integer findOrderCountAfterDate(String date);
     Integer findVisitsCountByDate(String date);
     Integer findVisitsCountAfterDate(String date);
    //List<Map> findHotSetmeal();

}
