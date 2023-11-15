package com.lit.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lit.dao.CheckItemDao;
import com.lit.pojo.CheckItem;
import com.lit.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckItemServiceImpl extends ServiceImpl<CheckItemDao, CheckItem> implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public IPage<CheckItem> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage, pageSize);
        checkItemDao.selectPage(page, null);
        return page;
    }

//    @Override
//    public IPage<CheckItem> getPage(Integer currentPage, Integer pageSize, CheckItem checkItem) {
//        IPage iPage = new Page(currentPage,pageSize);
//        LambdaQueryWrapper<CheckItem> lqw=new LambdaQueryWrapper<>();
//
//        return null;
//    }
}
