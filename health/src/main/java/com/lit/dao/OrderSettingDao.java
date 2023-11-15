package com.lit.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lit.pojo.OrderSetting;
import org.apache.ibatis.annotations.Mapper;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderSettingDao extends BaseMapper<OrderSetting> {
    void add(OrderSetting orderSetting);
    void editNumberByOrderDate(OrderSetting orderSetting);
    long findCountByOrderDate(Date orderDate);
    public List<OrderSetting> getOrderSettingByMonth(Map date);

}
