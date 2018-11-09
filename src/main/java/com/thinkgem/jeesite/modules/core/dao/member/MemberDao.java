/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.dao.member;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.core.entity.member.Member;
import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.List;

/**
 * 会员DAO接口
 * @author 李延明
 * @version 2018-10-30
 */
@MyBatisDao
public interface MemberDao extends CrudDao<Member> {
    public List<Member> getMemberNet(User user);
    public List<Member> getMemberRecommend(User user);
    public Member getMemberByLoginName(String loginName);
    public List<Member> getMemberContacts(String loginName);
    public Member getMemberA(String loginName);
    public List<Member> getRealMember(Member member);
    public List<Member> realMemberManager(Member member);
    public List<Member> getActivateMember(Member member);
    public void updateMember(Member member);
    public void insertRole(User user);
}