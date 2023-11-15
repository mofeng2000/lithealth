package com.lit.health_mobile.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lit.health_mobile.pojo.Setmeal;


//服务接口
public interface SetMealService extends IService<Setmeal> {
//    //添加检查组
//     void add(Setmeal setmeal,Integer[] checkGroupIds);
////
//    //按id查询套餐和检查组
 Setmeal findById(Integer id);
//    void edit(Setmeal setmeal,Integer[] checkGroupIds);
//    //实现分页查询
//    IPage<Setmeal> getPage(int currentPage, int pageSize);
////    //根据检查组id删除套餐内容和关联表内容
//    void deleteById(Integer id);
    //  IPage<CheckItem> getPage(Integer currentPage, Integer pageSize, CheckItem checkItem);
}
