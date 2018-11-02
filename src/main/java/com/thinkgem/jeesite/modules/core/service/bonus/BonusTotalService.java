/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.service.bonus;

import java.math.BigDecimal;
import java.util.List;

import com.thinkgem.jeesite.modules.core.dao.member.MemberDao;
import com.thinkgem.jeesite.modules.core.entity.member.Member;
import com.thinkgem.jeesite.modules.core.entity.pv.PvTotal;
import com.thinkgem.jeesite.modules.core.entity.setting.MemberSetting;
import com.thinkgem.jeesite.modules.core.service.member.MemberService;
import com.thinkgem.jeesite.modules.core.service.pv.PvTotalService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.core.entity.bonus.BonusTotal;
import com.thinkgem.jeesite.modules.core.dao.bonus.BonusTotalDao;

/**
 * 业绩Service
 * @author 李延明
 * @version 2018-11-01
 */
@Service
@Transactional(readOnly = true)
public class BonusTotalService extends CrudService<BonusTotalDao, BonusTotal> {

    @Autowired
    private BonusTotalDao bonusTotalDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private PvTotalService pvTotalService;

	public BonusTotal get(String id) {
		return super.get(id);
	}
	
	public List<BonusTotal> findList(BonusTotal bonusTotal) {
		return super.findList(bonusTotal);
	}
	
	public Page<BonusTotal> findPage(Page<BonusTotal> page, BonusTotal bonusTotal) {
		return super.findPage(page, bonusTotal);
	}
	
	@Transactional(readOnly = false)
	public void save(BonusTotal bonusTotal) {
		super.save(bonusTotal);
	}
	
	@Transactional(readOnly = false)
	public void delete(BonusTotal bonusTotal) {
		super.delete(bonusTotal);
	}

	//注册会员后开始计算奖金
    @Transactional(readOnly = false)
    public void excuteBonus(User user, Member member,MemberSetting memberSetting){
        zhitui(member,memberSetting);
        hezuo(member,memberSetting);

       /* PvTotal pvTotal = new PvTotal();
        pvTotal.setLoginName(refree);
        pvTotal.setZhitui(zhitui);
        pvTotal.setFromMember(user.getLoginName());
        pvTotalService.save(pvTotal);*/
    }

    public void hezuo(Member member,MemberSetting memberSetting) {
        Integer pv1 = memberSetting.getPv1();//1、2、3级代理奖金级别
        Integer pv2 = memberSetting.getPv2();
        Integer pv3 = memberSetting.getPv3();

        Integer hezuo1 = memberSetting.getHezuo1();//直推奖比例1、2、3级
        Integer hezuo2 = memberSetting.getHezuo2();
        Integer hezuo3 = memberSetting.getHezuo3();

        String memberLevel = member.getMemberlevel();//注册会员级别
        String area = member.getArea();
        String contact = member.getContact();//接点人编号
        Member contactM = memberDao.getMemberByLoginName(contact);
        String contactLevel = contactM.getMemberlevel();//接点人级别
        while (!"0".equals(contact.trim())){
            BigDecimal bonus = BigDecimal.ZERO;
            if(!"0".equals(memberLevel) && !"0".equals(contactLevel)) {//普通会员不计算
                if("1".equals(memberLevel)) {//pv1
                    bonus = BigDecimal.valueOf(pv1);
                }else if("2".equals(memberLevel)){
                    bonus = BigDecimal.valueOf(pv2);
                }else{
                    bonus = BigDecimal.valueOf(pv3);
                }
            }
            setPv(contact,area,bonus);
            contact = contactM.getContact();
            contactM = memberDao.getMemberByLoginName(contact);
        }

    }

    //接点人及父级接点人添加pv
    public void  setPv(String contact,String area,BigDecimal bonus){
        BonusTotal bonusTotal = bonusTotalDao.getBonusTotalByLoginName(contact);//接点人奖金表
        if("A".equals(area)){
            bonusTotal.setApvTotal(bonusTotal.getApvTotal().add(bonus));
            bonusTotal.setApv(bonusTotal.getApv().add(bonus));
        }else {
            bonusTotal.setBpvTotal(bonusTotal.getBpvTotal().add(bonus));
            bonusTotal.setBpv(bonusTotal.getBpv().add(bonus));
        }
        bonusTotalDao.updateBouns(bonusTotal);
    }

    public void zhitui(Member member,MemberSetting memberSetting){
        BigDecimal bonus = BigDecimal.ZERO;
        Integer pv1 = memberSetting.getPv1();//1、2、3级代理奖金级别
        Integer pv2 = memberSetting.getPv2();
        Integer pv3 = memberSetting.getPv3();
        //直推奖，推荐人奖金=注册人级别pv*推荐人级别zhuitui
        Integer zhitui1 = memberSetting.getZhitui1();//直推奖比例1、2、3级
        Integer zhitui2 = memberSetting.getZhitui2();
        Integer zhitui3 = memberSetting.getZhitui3();

        String memberLevel = member.getMemberlevel();//注册会员级别
        String refree = member.getReferee();//推荐人编号
        Member refreeM = memberDao.getMemberByLoginName(refree);
        String refreeLevel = refreeM.getMemberlevel();//推荐人级别

        if(!"0".equals(memberLevel) && !"0".equals(refreeLevel)){//普通会员不计算
            if("1".equals(memberLevel)){//pv1
                if("1".equals(refreeLevel)){//zhitui1
                    bonus = BigDecimal.valueOf(pv1*zhitui1).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                }else if ("2".equals(refreeLevel)){//zhitui2
                    bonus = BigDecimal.valueOf(pv1*zhitui2).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                }else{
                    bonus = BigDecimal.valueOf(pv1*zhitui3).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                }
            }else if("2".equals(memberLevel)){//pv2
                if("1".equals(refreeLevel)){//zhitui1
                    bonus = BigDecimal.valueOf(pv2*zhitui1).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                }else if ("2".equals(refreeLevel)){//zhitui2
                    bonus = BigDecimal.valueOf(pv2*zhitui2).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                }else{
                    bonus = BigDecimal.valueOf(pv2*zhitui3).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                }
            }else{
                if("1".equals(refreeLevel)){//zhitui1
                    bonus = BigDecimal.valueOf(pv3*zhitui1).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                }else if ("2".equals(refreeLevel)){//zhitui2
                    bonus = BigDecimal.valueOf(pv3*zhitui2).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                }else{
                    bonus = BigDecimal.valueOf(pv3*zhitui3).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                }
            }
        }
        bonus = bonus.multiply(new BigDecimal(0.95));
        BonusTotal bonusTotal = bonusTotalDao.getBonusTotalByLoginName(refree);//推荐人奖金表
        bonusTotal.setBonusTotal(bonusTotal.getBonusTotal().add(bonus));
        bonusTotal.setBonusCurrent(bonusTotal.getBonusCurrent().add(bonus));
        bonusTotalDao.updateBouns(bonusTotal);
    }
}