package com.lit.health_mobile.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lit.health_mobile.pojo.OrderSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderSettingDao extends BaseMapper<OrderSetting> {
    void add(OrderSetting orderSetting);
    void editNumberByOrderDate(OrderSetting orderSetting);
    long findCountByOrderDate(Date orderDate);
     List<OrderSetting> getOrderSettingByMonth(Map date);
    OrderSetting findByOrderDate(Date orderDate);
    //更新已预约人数
     void editReservationsByOrderDate(OrderSetting orderSetting);
}
