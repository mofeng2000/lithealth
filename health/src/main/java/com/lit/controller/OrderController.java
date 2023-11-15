package com.lit.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lit.entity.R;
import com.lit.pojo.Order;
import com.lit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
检查项管理
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService ;
    //新增检查项
    @PostMapping
    public R add(@RequestBody Order order) {
        boolean flag = orderService.save(order);
        return new R(flag, flag ? "添加预约成功!" : "添加预约失败!");
    }
    //按id查询检查项
    @GetMapping("{id}")
    public R getById(@PathVariable Integer id) {
        return new R(true, orderService.getById(id));
    }
    //查询全部检查项
    @GetMapping()
    public R getAll() {
        return new R(true, orderService.list());
    }
    //更新检查项
    @PutMapping()
    public R update(@RequestBody Order order) {
        return new R(orderService.updateById(order));
    }
    //删除检查项
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        return new R(orderService.removeById(id));
    }
    //分页查询查询
    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize) {
        IPage<Order> page = orderService.getPage(currentPage, pageSize);

        //如果当前页码值大于总页码，那吗重新执行查询操作，使用最大页码作为当前页码值
        if (currentPage > page.getPages()) {
            page = orderService.getPage((int) page.getPages(), pageSize);
        }
        return new R(true, page);
    }
}
