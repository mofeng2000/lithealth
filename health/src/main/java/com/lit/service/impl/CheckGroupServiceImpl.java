package com.lit.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lit.dao.CheckGroupDao;
import com.lit.pojo.CheckGroup;
import com.lit.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查组服务
 */
@Service
@Transactional
public class CheckGroupServiceImpl extends ServiceImpl<CheckGroupDao, CheckGroup> implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    //新增检查组，同时需要要让检查组关联检查项
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //新增检查组，操作t_checkgroup表
        // checkGroupDao.insert(checkGroup);
        checkGroupDao.add(checkGroup);
        //设置检查组与检查项的多对多服务关联关系， 操作t_checkgroup+checkitem表
        Integer checkGroupId = checkGroup.getId();
        this.setCheckGroupAndCheckItem(checkGroupId, checkitemIds);
    }

    @Override
    //根据检查组id查询关联的检查项id
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    //处理检查组id和关联的检查项id
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //更新检查组基本信息
        checkGroupDao.updateById(checkGroup);
        //根据检查组id删除中间表数据（清理原有关联关系）
        checkGroupDao.deleteAssociation(checkGroup.getId());
        //向中间表(t_checkgroup_checkitem)插入数据（建立检查组和检查项关联关系）
        this.setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
    }

    /**
     * //设置检查组与检查项的多对多服务关联关系， 操作t_checkgroup+checkitem表
     *
     * @param checkGroupId
     * @param checkitemIds
     */
    public void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds) {
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("checkGroupId", checkGroupId);
                map.put("checkitemId", checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }

    @Override
    public IPage<CheckGroup> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage, pageSize);
        checkGroupDao.selectPage(page, null);
        return page;
    }

    @Override
    public void deleteById(Integer id) {
        //根据检查组id删除中间表数据（清理原有关联关系）
        //再删除检查组内容
        checkGroupDao.deleteAssociation(id);
    }

}
