package com.lit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lit.pojo.User;

public interface UserService extends IService<User> {
    User findByUsername(String username);
}
