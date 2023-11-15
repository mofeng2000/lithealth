package com.lit.health_mobile.controller;

import com.alibaba.fastjson.JSON;
import com.lit.health_mobile.constant.MessageConstant;
import com.lit.health_mobile.constant.RedisMessageConstant;
import com.lit.health_mobile.entity.R;
import com.lit.health_mobile.pojo.Member;
import com.lit.health_mobile.pojo.Order;
import com.lit.health_mobile.service.MemberService;
import com.lit.health_mobile.utils.SMSUtils;
import com.lit.health_mobile.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 登录处理
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MemberService memberService;

    //用户登录发送验证码
    @PostMapping("/login")
    public R login(HttpServletResponse response, @RequestBody Map map) {
        //从redis获取验证码
        String telephone = (String) map.get("telephone");
        String validateCodeInRedis = stringRedisTemplate.opsForValue().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);//
        String validateCode = (String) map.get("validateCode");//获取前端输入验证码
        System.out.println("redis" + validateCodeInRedis);
        System.out.println("获取前端输入验证码" + validateCode);
        //将用户输入的验证码和redis验证码进行比对
        if (validateCodeInRedis != null && validateCode != null && validateCode.equals(validateCodeInRedis)) {
            //如果比对成功，调用服务成功预约业务处理

            Member member = memberService.findByTelephone(telephone);
            if (member == null) {
                //不是会员
                member.setRegTime(new Date());
                member.setPhoneNumber(telephone);
                memberService.add(member);

            }
            //向客户端浏览器写入cookie，内容为手机号
            Cookie cookie = new Cookie("login_member_telephone", telephone);
            cookie.setPath("/");//路径
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
            String json = JSON.toJSON(member).toString();
            //向redis里存入验证码和设置缓存时间
            stringRedisTemplate.opsForValue().set(telephone, json, 60 * 30, TimeUnit.SECONDS);
            return new R(true,MessageConstant.LOGIN_SUCCESS);
        } else {
            //如果比对不成功，返回给页面
            return new R(false, MessageConstant.VALIDATECODE_ERROR);
        }
    }
}
