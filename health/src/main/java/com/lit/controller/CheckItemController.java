package com.lit.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lit.constant.MessageConstant;
import com.lit.entity.R;
import com.lit.pojo.CheckItem;
import com.lit.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
检查项管理
 */
@RestController
@RequestMapping("/checkitems")
public class CheckItemController {
    @Autowired
    private CheckItemService checkItemService;

    //新增检查项
    @PostMapping
    public R add(@RequestBody CheckItem checkItem) {
        boolean flag = checkItemService.save(checkItem);
        return new R(flag, flag ? MessageConstant.ADD_CHECKITEM_SUCCESS : MessageConstant.ADD_CHECKITEM_FAIL);
    }

    //按id查询检查项
    @GetMapping("{id}")
    public R getById(@PathVariable Integer id) {
        return new R(true, checkItemService.getById(id));
    }
    //查询全部检查项
    @GetMapping()
    public R getAll() {
        return new R(true, checkItemService.list());
    }
    //更新检查项
    @PutMapping()
    public R update(@RequestBody CheckItem checkItem) {
        return new R(checkItemService.updateById(checkItem));
    }
    //删除检查项
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        return new R(checkItemService.removeById(id));
    }
    //分页查询查询
    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize) {
        IPage<CheckItem> page = checkItemService.getPage(currentPage, pageSize);
        //如果当前页码值大于总页码，那吗重新执行查询操作，使用最大页码作为当前页码值
        if (currentPage > page.getPages()) {
            page = checkItemService.getPage((int) page.getPages(), pageSize);
        }
        return new R(true, page);
    }
    //分页条件查询
//    @GetMapping("{currentPage}/{pageSize}")
//    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, CheckItem checkItem) {
//        System.out.println(checkItem);
//        IPage<CheckItem> page = checkItemService.getPage(currentPage, pageSize, checkItem);
//        //如果当前页码值大于总页码，那吗重新执行查询操作，使用最大页码作为当前页码值
//        if (currentPage > page.getPages()) {
//            page = checkItemService.getPage((int) page.getPages(), pageSize, checkItem);
//        }
//        return new R(true, page);
//    }
}
