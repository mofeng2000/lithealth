package com.lit.health_mobile.controller;
import com.lit.health_mobile.constant.MessageConstant;
import com.lit.health_mobile.entity.R;
import com.lit.health_mobile.pojo.Setmeal;
import com.lit.health_mobile.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 体检套餐管理
 */
@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    //@Reference
    @Autowired
    private SetMealService setMealService;

    //
//    //新增检查组
//    @PostMapping("{checkgroupIds}")
//    public R add(@RequestBody Setmeal setmeal, @PathVariable Integer[] checkGroupIds) {
//        try {
//            setMealService.add(setmeal, checkGroupIds);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new R(false, MessageConstant.ADD_SETMEAL_FAIL);
//        }
//        return new R(true, MessageConstant.ADD_SETMEAL_SUCCESS);
//    }
//
//    //按id查询检查项
//    @GetMapping("{id}")
//    public R getById(@PathVariable Integer id) {
//        findCheckItemIdsByCheckGroupId(id);
//        return new R(true, setMealService.getById(id));
//    }
//
    //按套餐id查询套餐详情（套餐基本信息，套餐包含的检查组信息，检查组对应的检查项信息）
    @GetMapping("/{id}")
    public R findById(@PathVariable Integer id) {
        try {
            Setmeal setmeal = setMealService.findById(id);
            return new R(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        }catch (Exception e){
            e.printStackTrace();
            return new R(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }

    }

    //
    //查询全部
    @GetMapping()
    public R getAll() {
        return new R(true, setMealService.list());
    }

//    //文件上传
//    @PostMapping("/upload")
//    public R upload(@RequestParam("imgFile") MultipartFile imgFile) {
//        //拿到原始名
//        String originalFilename = imgFile.getOriginalFilename();
//        int index = originalFilename.lastIndexOf(".");
//        String extention = originalFilename.substring(index - 1);
//        //随即生成文件名
//        String fileName = UUID.randomUUID().toString() + extention;
//        try {
//            //使用七牛云上传到服务器
//            QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
//        } catch (IOException e) {
//            e.printStackTrace();
//            //上传失败
//            return new R(false, MessageConstant.PIC_UPLOAD_FAIL);
//        }
//        //上传成功
//        return new R(true, MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
//    }
//
////
////    //更新检查组内容
//    @PutMapping("{checkgroupIds}")
//    public R edit(@RequestBody Setmeal setmeal, @PathVariable Integer[] checkgroupIds) {
//        try {
//            setMealService.edit(setmeal, checkgroupIds);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new R(false, "更新套餐失败!");
//        }
//        return new R(true,"更新套餐成功!");
//    }
//
//    //删除检查项
//    @DeleteMapping("{id}")
//    public R delete(@PathVariable Integer id) {
//        //先删关联表
//        setMealService.deleteById(id);
//        //再删检查组表
//        return new R(setMealService.removeById(id));
//    }
////
//    //分页查询查询
//    @GetMapping("{currentPage}/{pageSize}")
//    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize) {
//        IPage<Setmeal> page = setMealService.getPage(currentPage, pageSize);
//        //如果当前页码值大于总页码，那吗重新执行查询操作，使用最大页码作为当前页码值
//        if (currentPage > page.getPages()) {
//            page = setMealService.getPage((int) page.getPages(), pageSize);
//        }
//        return new R(true, page);
//    }
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
