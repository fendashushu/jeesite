/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.service.member;

import java.math.BigDecimal;
import java.util.List;

import com.thinkgem.jeesite.modules.core.dao.bonus.BonusTotalDao;
import com.thinkgem.jeesite.modules.core.entity.bonus.BonusTotal;
import com.thinkgem.jeesite.modules.core.entity.orders.Orders;
import com.thinkgem.jeesite.modules.core.entity.setting.MemberSetting;
import com.thinkgem.jeesite.modules.core.service.bonus.BonusTotalService;
import com.thinkgem.jeesite.modules.core.service.orders.OrdersService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.core.entity.member.Member;
import com.thinkgem.jeesite.modules.core.dao.member.MemberDao;

/**
 * 会员Service
 * @author 李延明
 * @version 2018-10-30
 */
@Service
@Transactional(readOnly = true)
public class MemberService extends CrudService<MemberDao, Member> {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private BonusTotalService bonusTotalService;
    @Autowired
    private OrdersService ordersService;

	public Member get(String id) {
		return super.get(id);
	}
	
	public List<Member> findList(Member member) {
		return super.findList(member);
	}

	public List<Member> getMemberNet(User user) {
		return memberDao.getMemberNet(user);
	}

	public List<Member> getMemberRecommend(User user) {
		return memberDao.getMemberRecommend(user);
	}

	public Member getMemberByLoginName(String loginName) {
		return memberDao.getMemberByLoginName(loginName);
	}

	public Member getMemberA(String loginName){
	    return memberDao.getMemberA(loginName);
    }

    public List<Member> getMemberContacts(String loginName) {
        return memberDao.getMemberContacts(loginName);
    }

	public Page<Member> findPage(Page<Member> page, Member member) {
		return super.findPage(page, member);
	}

	public Page<Member> getRealMember(Page<Member> page, Member member) {
        member.setPage(page);
        page.setList(memberDao.getRealMember(member));
        return page;
	}

	public Page<Member> realMemberManager(Page<Member> page, Member member) {
        member.setPage(page);
        page.setList(memberDao.realMemberManager(member));
        return page;
	}

	public Page<Member> storeManage(Page<Member> page, Member member) {
        member.setPage(page);
        page.setList(memberDao.storeManage(member));
        return page;
	}

	public Page<Member> baodan(Page<Member> page, Member member) {
        member.setPage(page);
        page.setList(memberDao.baodan(member));
        return page;
	}

	public Page<Member> getActivateMember(Page<Member> page, Member member) {
        member.setPage(page);
        page.setList(memberDao.getActivateMember(member));
        return page;
	}

	public Page<Member> getUpMember(Page<Member> page, Member member) {
        member.setPage(page);
        page.setList(memberDao.getUpMember(member));
        return page;
	}

	@Transactional(readOnly = false)
	public void save(Member member) {
		super.save(member);
        BonusTotal bonusTotal = new BonusTotal();
        bonusTotal.setLoginName(member.getLoginName());
        bonusTotal.setApv(BigDecimal.ZERO);
        bonusTotal.setBpv(BigDecimal.ZERO);
        bonusTotal.setApvTotal(BigDecimal.ZERO);
        bonusTotal.setBpvTotal(BigDecimal.ZERO);
        bonusTotal.setBonusCurrent(BigDecimal.ZERO);
        bonusTotal.setBonusTotal(BigDecimal.ZERO);
        bonusTotal.setMoneyCurrent(BigDecimal.ZERO);
        bonusTotal.setMoneyTotal(BigDecimal.ZERO);
        bonusTotal.setJinhuopv(BigDecimal.ZERO);
        bonusTotalService.save(bonusTotal);
	}
	
	@Transactional(readOnly = false)
	public void delete(Member member) {
		super.delete(member);
	}

	@Transactional(readOnly = false)
	public void lockOrUnlock(User user) {
		memberDao.lockOrUnlock(user);
	}

    @Transactional(readOnly = false)
    public void updateMember(Member member, BonusTotal bonusTotal, User user, MemberSetting memberSetting,String before,String after,BigDecimal need,String text) {
        memberDao.updateMember(member);

        if(bonusTotal != null){
            bonusTotalService.updateBouns(bonusTotal);
        }
        if (user != null){
            memberDao.insertRole(user);
            bonusTotalService.kaifaStore(member,null);
        }
        if(memberSetting != null && before == null){
            bonusTotalService.excuteBonus(member,memberSetting);
        }
        if(before != null && after != null){
            bonusTotalService.upBonus(member,memberSetting,before,after);
        }

        //产生订单
        Orders orders = new Orders();
        orders.setGoodsId("0");
        orders.setLoginName(member.getLoginName());
        orders.setTakeName(member.getLoginName());
        orders.setTakeAddress(member.getAddress());
        orders.setTakePhone(member.getPhone());
        orders.setGoodsCount(1);
        orders.setGoodsName(text + "：" + member.getLoginName()+"("+need+")");
        orders.setGoodsPrice(need);
        orders.setVipPrice(need);
        orders.setTotal(need);
        orders.setVipTotal(need);
        orders.setOrderId(System.currentTimeMillis());
        orders.setStatus("0");
        orders.setOrderType("0");
        ordersService.save(orders);
    }
}