package com.lit.controller;

import com.lit.constant.MessageConstant;
import com.lit.entity.R;
import com.lit.pojo.OrderSetting;
import com.lit.service.OrderSettingService;
import com.lit.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 预约设置
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Autowired
    private OrderSettingService orderSettingService;
    //查询预约人数
    @PostMapping("/getOrderSettingByMonth/{date}")
    public R getPage(@PathVariable String date) {
        try{
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            //获取预约设置数据成功
            return new R(true,MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //获取预约设置数据失败
            return new R(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }
    @PostMapping("/editNumberByDate")
    public R editNumberByDate(@RequestBody OrderSetting orderSetting){
        try{
            orderSettingService.editNumberByDate(orderSetting);
            //预约设置成功
            return new R(true,MessageConstant.ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //预约设置失败
            return new R(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }
    //文件上传,实现   预约设置数据批量导入
    @PostMapping("/upload")
    public R upload(@RequestParam("excelFile") MultipartFile excelFile) {
        try {
            //读取Excel文件数据
            List<String[]> list = POIUtils.readExcel(excelFile);
            if (list != null && list.size() > 0) {
                List<OrderSetting> orderSettingList = new ArrayList<>();
                for (String[] strings : list) {
                    OrderSetting orderSetting =
                            new OrderSetting(new Date(strings[0]), Integer.parseInt(strings[1]));
                    orderSettingList.add(orderSetting);
                }
                orderSettingService.add(orderSettingList);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new R(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        return new R(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }
}
