package com.lit.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lit.dao.OrderDao;
import com.lit.pojo.Order;
import com.lit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl  extends ServiceImpl<OrderDao, Order> implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public IPage<Order> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage, pageSize);
        orderDao.selectPage(page, null);
        return page;
    }
}
