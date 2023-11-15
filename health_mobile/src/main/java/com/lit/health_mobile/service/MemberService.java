package com.lit.health_mobile.service;

import com.lit.health_mobile.pojo.Member;

public interface MemberService {
    //根据手机号查询会员
    Member findByTelephone(String telephone);
    //添加会员
    void add(Member member);
}
