package com.lit.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lit.pojo.CheckItem;
import com.lit.pojo.Member;

import java.util.List;

public interface MemberService extends IService<Member> {
    //根据手机号查询会员
    Member findByTelephone(String telephone);
    //添加会员
    void add(Member member);
    List<Integer> findMemberCountByMonths(List<String> months);
    //实现分页查询
    IPage<Member> getPage(int currentPage, int pageSize);

}
