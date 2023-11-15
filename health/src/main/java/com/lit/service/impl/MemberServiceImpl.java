package com.lit.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lit.dao.CheckItemDao;
import com.lit.dao.MemberDao;
import com.lit.pojo.CheckItem;
import com.lit.pojo.Member;
import com.lit.service.MemberService;
import com.lit.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

//会员服务
@Service
@Transactional
public class MemberServiceImpl extends ServiceImpl<MemberDao, Member> implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    //保存会员信息
    public void add(Member member) {
        String password = member.getPassword();
        if (password != null) {
            //使用md5将明文密码加密
            password = MD5Utils.md5(password);
            member.setPassword(password);
        }
        memberDao.add(member);
    }

    //根据月份统计会员数量
    public List<Integer> findMemberCountByMonths(List<String> month) {
        List<Integer> list = new ArrayList<>();
        for (String m : month) {
            m = m + ".31";//格式：2019.04.31
            Integer count = memberDao.findMemberCountBeforeDate(m);
            list.add(count);
        }
        return list;
    }
    @Override
    public IPage<Member> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage, pageSize);
        memberDao.selectPage(page, null);
        return page;
    }

}
