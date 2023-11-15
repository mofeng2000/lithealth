package com.lit.health_mobile.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lit.health_mobile.dao.SetMealDao;
import com.lit.health_mobile.pojo.Setmeal;
import com.lit.health_mobile.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 检查组服务
 */
@Service
@Transactional
public class SetMealServiceImpl extends ServiceImpl<SetMealDao, Setmeal> implements SetMealService {
    @Autowired
    private SetMealDao setMealDao;

//    //新增套餐，同时需要要让检查组关联套餐
//    @Override
//    public void add(Setmeal setmeal, Integer[] checkGroupIds) {
//        //新增检查组，操作t_setmeal表
//        setMealDao.add(setmeal);
//        //设置检查组与套餐的多对多服务关联关系， 操作t_setmeal_checkgroup表
//        Integer setmealId = setmeal.getId();
//        this.setMealAndCheckGroup(setmealId, checkGroupIds);
//    }
//
//
    //根据套餐id查询关联的检查组id
    public Setmeal findById(Integer id) {
        return setMealDao.findById(id);
    }
////
//    //处理套餐id和关联的检查组id
//    public void edit(Setmeal setmeal, Integer[] checkGroupIds) {
//        //更新套餐基本信息
//        setMealDao.updateById(setmeal);
//        //根据检查组id删除中间表数据（清理原有关联关系）
//        setMealDao.deleteAssociation(setmeal.getId());
//        //向中间表(t_setmeal_checkgroup)插入数据（建立检查组和套餐关联关系）
//        this.setMealAndCheckGroup(setmeal.getId(), checkGroupIds);
//    }
////
////    /**
////     * //设置检查组与套餐的多对多服务关联关系， 操作t_setmeal_checkgroup表
////     *
////     * @param checkGroupId
////     * @param checkitemIds
////     */
//    public void setMealAndCheckGroup(Integer setMealId, Integer[] checkGroupIds) {
//        if (setMealId != null && checkGroupIds.length > 0) {
//            for (Integer checkGroupId : checkGroupIds) {
//                Map<String, Integer> map = new HashMap<>();
//                map.put("setMealId", setMealId);
//                map.put("checkGroupIds", checkGroupId);
//                setMealDao.setMealAndCheckGroup(map);
//            }
//        }
//    }
////
//    @Override
//    //分页查询
//    public IPage<Setmeal> getPage(int currentPage, int pageSize) {
//        IPage page = new Page(currentPage, pageSize);
//        setMealDao.selectPage(page, null);
//        return page;
//    }
////
//    @Override
//    public void deleteById(Integer id) {
//        //根据检查组id删除中间表数据（清理原有关联关系）
//        //再删除检查组内容
//        setMealDao.deleteAssociation(id);
//    }

}
