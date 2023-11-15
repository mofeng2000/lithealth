package com.lit.health_mobile.controller;
import com.lit.health_mobile.constant.MessageConstant;
import com.lit.health_mobile.constant.RedisMessageConstant;
import com.lit.health_mobile.entity.R;
import com.lit.health_mobile.utils.SMSUtils;
import com.lit.health_mobile.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //用户体检预约发送验证码
    @PostMapping("/send/{telephone}")
    public R sendsOrder(@PathVariable String telephone) {
        //随机生成4位数字验证码
        Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
        System.out.println("随机生成4位数字验证码"+validateCode);
        //给用户发送验证码
        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, validateCode.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new R(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //将验证码保存到redis(5分钟)
        //向redis里存入验证码和设置缓存时间
        stringRedisTemplate.opsForValue().set(telephone + RedisMessageConstant.SENDTYPE_ORDER, validateCode.toString(), 60 * 5, TimeUnit.SECONDS);
        return new R(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
    //用户登录发送验证码
    @PostMapping("/login/{telephone}")
    public R login(@PathVariable String telephone) {
        //随机生成4位数字验证码
        Integer validateCode = ValidateCodeUtils.generateValidateCode(6);
        //System.out.println("随机生成4位数字验证码"+validateCode);
        //给用户发送验证码
        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, validateCode.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new R(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //将验证码保存到redis(5分钟)
        //向redis里存入验证码和设置缓存时间
        stringRedisTemplate.opsForValue().set(telephone + RedisMessageConstant.SENDTYPE_LOGIN, validateCode.toString(), 60 * 5, TimeUnit.SECONDS);
        return new R(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
