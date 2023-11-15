package com.lit.health_mobile.controller;

import com.lit.health_mobile.constant.MessageConstant;
import com.lit.health_mobile.constant.RedisMessageConstant;
import com.lit.health_mobile.entity.R;
import com.lit.health_mobile.pojo.Order;
import com.lit.health_mobile.pojo.Setmeal;
import com.lit.health_mobile.service.OrderService;
import com.lit.health_mobile.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 体检预约处理
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private OrderService orderService;

    //在线体检预约
    @PostMapping
    public R submit(@RequestBody Map map) {
        //从redis获取验证码
        String telephone = (String) map.get("telephone");
        String validateCodeInRedis = stringRedisTemplate.opsForValue().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);//
        String validateCode = (String) map.get("validateCode");//获取前端输入验证码
        System.out.println("redis"+validateCodeInRedis);
        System.out.println("获取前端输入验证码"+validateCode);
        //将用户输入的验证码和redis验证码进行比对
        if (validateCodeInRedis != null && validateCode != null && validateCode.equals(validateCodeInRedis)) {
            //如果比对成功，调用服务成功预约业务处理
            //设置预约类型
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            R result = null;
            try {
                result = orderService.order(map);
            } catch (Exception e) {
                e.printStackTrace();
                return result;
            }
            if(result.isFlag()){
                //预约成功,发送短信
                try {
                    SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE,telephone,(String) map.get("orderDate"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        } else {
            //如果比对不成功，返回给页面
            return new R(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }
    //根据id查询预约信息，包括套餐信息和会员信息
    @GetMapping("/{id}")
    public R findById(@PathVariable Integer id) {
        try {
            Map map = orderService.findById(id);
            //查询预约信息成功
            return new R(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            //查询预约信息失败
            return new R(false,MessageConstant.QUERY_ORDER_FAIL);
        }

    }
}
