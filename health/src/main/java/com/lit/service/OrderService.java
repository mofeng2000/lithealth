package com.lit.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lit.pojo.Order;
public interface OrderService extends IService<Order> {
    IPage<Order> getPage(int currentPage, int pageSize);
}
