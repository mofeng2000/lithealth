package com.lit.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lit.constant.MessageConstant;
import com.lit.entity.R;
import com.lit.pojo.CheckGroup;
import com.lit.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
检查项管理
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    //@Reference
    @Autowired
    private CheckGroupService checkGroupService;

    //新增检查组
    @PostMapping("{checkitemIds}")
    public R add(@RequestBody CheckGroup checkGroup, @PathVariable Integer[] checkitemIds) {
        try {
            checkGroupService.add(checkGroup, checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new R(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new R(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    //按id查询检查项
    @GetMapping("{id}")
    public R getById(@PathVariable Integer id) {
        findCheckItemIdsByCheckGroupId(id);
        return new R(true, checkGroupService.getById(id));
    }

    //按id查询检查项和检查组
    @GetMapping("/findCheckItemIdsByCheckGroupId/{id}")
    public R findCheckItemIdsByCheckGroupId(@PathVariable Integer id) {
        List<Integer> checkitemIds = checkGroupService.findCheckItemIdsByCheckGroupId(id);
        return new R(true, checkitemIds);
    }
    //查询全部检查项
    @GetMapping()
    public R getAll() {
        return new R(true, checkGroupService.list());
    }
//
    //更新检查组内容
    @PutMapping("{checkitemIds}")
    public R edit(@RequestBody CheckGroup checkGroup, @PathVariable Integer[] checkitemIds) {
        try {
            checkGroupService.edit(checkGroup, checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new R(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new R(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    //删除检查项
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        //先删关联表
        checkGroupService.deleteById(id);
        //再删检查组表
        return new R(checkGroupService.removeById(id));
    }

    //分页查询查询
    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize) {
        IPage<CheckGroup> page = checkGroupService.getPage(currentPage, pageSize);
        //如果当前页码值大于总页码，那吗重新执行查询操作，使用最大页码作为当前页码值
        if (currentPage > page.getPages()) {
            page = checkGroupService.getPage((int) page.getPages(), pageSize);
        }
        return new R(true, page);
    }

    //分页条件查询
//    @GetMapping("{currentPage}/{pageSize}")
//    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, CheckItem checkItem) {
//        System.out.println(checkItem);
//        IPage<CheckItem> page = checkGroupService.getPage(currentPage, pageSize, checkItem);
//        //如果当前页码值大于总页码，那吗重新执行查询操作，使用最大页码作为当前页码值
//        if (currentPage > page.getPages()) {
//            page = checkGroupService.getPage((int) page.getPages(), pageSize, checkItem);
//        }
//        return new R(true, page);
//    }
}
