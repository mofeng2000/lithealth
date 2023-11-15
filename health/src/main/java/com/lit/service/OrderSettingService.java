package com.lit.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lit.pojo.OrderSetting;
import com.lit.pojo.Setmeal;

import java.util.List;
import java.util.Map;


//服务接口
public interface OrderSettingService extends IService<OrderSetting> {
    //添加预约设置
    void add(List<OrderSetting> list);
    //查询日历预约人数
    List<Map> getOrderSettingByMonth(String date);
    public void editNumberByDate(OrderSetting orderSetting);
}
