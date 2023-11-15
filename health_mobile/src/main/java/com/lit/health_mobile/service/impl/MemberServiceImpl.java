package com.lit.health_mobile.service.impl;

import com.lit.health_mobile.dao.MemberDao;
import com.lit.health_mobile.pojo.Member;
import com.lit.health_mobile.service.MemberService;
import com.lit.health_mobile.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//会员服务
@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    //保存会员信息
    public void add(Member member) {
        String password = member.getPassword();
        if(password!=null){
            //使用md5将明文密码加密
             password = MD5Utils.md5(password);
             member.setPassword(password);
        }
        memberDao.add(member);
    }
}
