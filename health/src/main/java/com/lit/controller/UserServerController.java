package com.lit.controller;

import com.lit.constant.MessageConstant;
import com.lit.entity.R;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.liveconnect.SecurityContextHelper;

@RestController
@RequestMapping("/user")
public class UserServerController {
    @GetMapping("/getUsername")
    public R getUsername() {
        //
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user != null) {
            String username = user.getUsername();
            return new R(true, MessageConstant.GET_USERNAME_SUCCESS, username);
        }
        return new R(false, MessageConstant.GET_USERNAME_FAIL);
    }
}
