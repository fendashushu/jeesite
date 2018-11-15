/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.service.bonus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.core.dao.member.MemberDao;
import com.thinkgem.jeesite.modules.core.dao.statistics.DayStatisticsDao;
import com.thinkgem.jeesite.modules.core.entity.member.Member;
import com.thinkgem.jeesite.modules.core.entity.pv.PvTotal;
import com.thinkgem.jeesite.modules.core.entity.pvdetail.PvDetail;
import com.thinkgem.jeesite.modules.core.entity.setting.MemberSetting;
import com.thinkgem.jeesite.modules.core.entity.statistics.DayStatistics;
import com.thinkgem.jeesite.modules.core.service.member.MemberService;
import com.thinkgem.jeesite.modules.core.service.pv.PvTotalService;
import com.thinkgem.jeesite.modules.core.service.pvdetail.PvDetailService;
import com.thinkgem.jeesite.modules.core.service.statistics.DayStatisticsService;
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
    private PvDetailService pvDetailService;
    @Autowired
    private DayStatisticsService statisticsService;

	public BonusTotal get(String id) {
		return super.get(id);
	}
	
	public List<BonusTotal> findList(BonusTotal bonusTotal) {
		return super.findList(bonusTotal);
	}
	
	public Page<BonusTotal> findPage(Page<BonusTotal> page, BonusTotal bonusTotal) {
		return super.findPage(page, bonusTotal);
	}

	public BonusTotal getBonusByLoginName(String loginName){
	    return bonusTotalDao.getBonusTotalByLoginName(loginName);
    }
	
	@Transactional(readOnly = false)
	public void save(BonusTotal bonusTotal) {
		super.save(bonusTotal);
	}
	
	@Transactional(readOnly = false)
	public void delete(BonusTotal bonusTotal) {
		super.delete(bonusTotal);
	}

    @Transactional(readOnly = false)
    public void updateBouns(BonusTotal bonusTotal) {
        bonusTotalDao.updateBouns(bonusTotal);
    }

	//激活会员后开始计算奖金
    @Transactional(readOnly = false)
    public void excuteBonus(Member member,MemberSetting memberSetting){

        statistics(member,memberSetting,null);
        zhitui(member,memberSetting);
        hezuo(member,memberSetting,null);
        baodan(member,memberSetting);
    }
	//升级会员后开始计算奖金
    @Transactional(readOnly = false)
    public void upBonus(Member member,MemberSetting memberSetting,String before,String after){
        Integer pv0 = memberSetting.getPv0();//1、2、3级代理奖金级别
        Integer pv1 = memberSetting.getPv1();//1、2、3级代理奖金级别
        Integer pv2 = memberSetting.getPv2();
        Integer pv3 = memberSetting.getPv3();
        Integer pv = 0;
        if("0".equals(before)){
            if("1".equals(after)){
                pv = pv1-pv0;
            }else if("2".equals(after)){
                pv = pv2-pv0;
            }else if("3".equals(after)){
                pv = pv3-pv0;
            }
        }else if("1".equals(before)){
            if("2".equals(after)){
                pv = pv2-pv1;
            }else if("3".equals(after)){
                pv = pv3-pv1;
            }
        }else if("2".equals(before)){
            if("3".equals(after)){
                pv = pv3-pv2;
            }
        }
        statistics(member,memberSetting,pv);
        hezuo(member,memberSetting,pv);
    }

    public void statistics(Member member,MemberSetting memberSetting,Integer pv){
        Integer pv1 = memberSetting.getPv1();//1、2、3级代理奖金级别
        Integer pv2 = memberSetting.getPv2();
        Integer pv3 = memberSetting.getPv3();
        if(pv != null){
            pv1 = pv;
            pv2 = pv;
            pv3 = pv;
        }
        String level = member.getMemberlevel();
        DayStatistics statistics = statisticsService.getByDate(DateUtils.getDate());
        DayStatistics lastest = statisticsService.getLastest();
        if(statistics == null){
            statistics = new DayStatistics();
            statistics.setDataDate(new Date());
            if(pv == null){
                statistics.setNewMembers(1);
            }else{
                statistics.setNewMembers(0);
            }
            if(lastest == null){
                if(pv == null) {
                    statistics.setAllMembers(1);
                }else{
                    statistics.setAllMembers(0);
                }
                statistics.setOutBonus(BigDecimal.ZERO);
                statistics.setOutAllBonus(BigDecimal.ZERO);
                statistics.setBobi(BigDecimal.ZERO);
                statistics.setAllBobi(BigDecimal.ZERO);
            }else{
                if(pv == null) {
                    statistics.setAllMembers(lastest.getAllMembers() + 1);
                }
                statistics.setOutBonus(BigDecimal.ZERO);
                statistics.setOutAllBonus(lastest.getOutAllBonus());
                statistics.setBobi(BigDecimal.ZERO);
                statistics.setAllBobi(lastest.getAllBobi());
            }
            if("1".equals(level)){
                statistics.setNewBonus(BigDecimal.valueOf(pv1));
                if(lastest == null){
                    statistics.setAllBonus(BigDecimal.valueOf(pv1));
                }else {
                    statistics.setAllBonus(lastest.getAllBonus().add(BigDecimal.valueOf(pv1)));
                }
            }else if("2".equals(level)){
                statistics.setNewBonus(BigDecimal.valueOf(pv2));
                if(lastest == null){
                    statistics.setAllBonus(BigDecimal.valueOf(pv2));
                }else {
                    statistics.setAllBonus(lastest.getAllBonus().add(BigDecimal.valueOf(pv2)));
                }
            }else if("3".equals(level)){
                statistics.setNewBonus(BigDecimal.valueOf(pv3));
                if(lastest == null){
                    statistics.setAllBonus(BigDecimal.valueOf(pv3));
                }else {
                    statistics.setAllBonus(lastest.getAllBonus().add(BigDecimal.valueOf(pv3)));
                }
            }

        }else {
            if(pv == null) {
                statistics.setNewMembers(statistics.getNewMembers() + 1);
                statistics.setAllMembers(statistics.getAllMembers() + 1);
            }else{
                statistics.setNewMembers(statistics.getNewMembers());
                statistics.setAllMembers(statistics.getAllMembers());
            }
            if("1".equals(level)){
                statistics.setNewBonus(statistics.getNewBonus().add(BigDecimal.valueOf(pv1)));
                statistics.setAllBonus(statistics.getAllBonus().add(BigDecimal.valueOf(pv1)));
            }else if("2".equals(level)){
                statistics.setNewBonus(statistics.getNewBonus().add(BigDecimal.valueOf(pv2)));
                statistics.setAllBonus(statistics.getAllBonus().add(BigDecimal.valueOf(pv2)));
            }else if("3".equals(level)){
                statistics.setNewBonus(statistics.getNewBonus().add(BigDecimal.valueOf(pv3)));
                statistics.setAllBonus(statistics.getAllBonus().add(BigDecimal.valueOf(pv3)));
            }
        }
        statisticsService.save(statistics);
    }

    public void baodan(Member member,MemberSetting memberSetting){
        Integer pv1 = memberSetting.getPv1();//1、2、3级代理奖金级别
        Integer pv2 = memberSetting.getPv2();
        Integer pv3 = memberSetting.getPv3();
        Integer baodan = memberSetting.getBaodan();
        String store = member.getStore();
        String level = member.getMemberlevel();
        BonusTotal bonusTotal = bonusTotalDao.getBonusTotalByLoginName(store);
        BigDecimal bonus = BigDecimal.ZERO;
        if("1".equals(level)){//baodan1
            bonus = BigDecimal.valueOf(pv1*baodan).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
        }else if ("2".equals(level)){//baodan2
            bonus = BigDecimal.valueOf(pv2*baodan).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
        }else if("3".equals(level)){
            bonus = BigDecimal.valueOf(pv3*baodan).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
        }

        PvDetail pvDetail = new PvDetail();
        pvDetail.setLoginName(store);
        pvDetail.setNote("报单奖");
        pvDetail.setPvTotal(bonus);
        pvDetail.setPvSheng(bonus.multiply(new BigDecimal(0.95)));
        pvDetail.setPvDues(bonus.multiply(new BigDecimal(0.05)));
        pvDetail.setPvtype("4");
        pvDetail.setFromName(member.getLoginName());
        pvDetail.setZhuceName(member.getLoginName());
        pvDetailService.save(pvDetail);

        bonus = bonus.multiply(new BigDecimal(0.95));
        bonusTotal.setBonusTotal(bonusTotal.getBonusTotal().add(bonus));
        bonusTotal.setBonusCurrent(bonusTotal.getBonusCurrent().add(bonus));
        bonusTotalDao.updateBouns(bonusTotal);

        DayStatistics statistics = statisticsService.getByDate(DateUtils.getDate());
        statistics.setOutAllBonus(statistics.getOutAllBonus().add(bonus));
        statistics.setOutBonus(statistics.getOutBonus().add(bonus));
        statistics.setBobi(statistics.getOutBonus().multiply(new BigDecimal("100")).divide(statistics.getNewBonus(),2,BigDecimal.ROUND_HALF_UP));
        statistics.setAllBobi(statistics.getOutAllBonus().multiply(new BigDecimal("100")).divide(statistics.getAllBonus(),2,BigDecimal.ROUND_HALF_UP));
        statisticsService.save(statistics);
    }

    public void hezuo(Member member,MemberSetting memberSetting,Integer pv) {
        Integer pv1 = memberSetting.getPv1();//1、2、3级代理奖金级别
        Integer pv2 = memberSetting.getPv2();
        Integer pv3 = memberSetting.getPv3();
        if(pv != null){
            pv1 = pv;
            pv2 = pv;
            pv3 = pv;
        }
        Integer hezuo1 = memberSetting.getHezuo1();//合作奖比例1、2、3级
        Integer hezuo2 = memberSetting.getHezuo2();
        Integer hezuo3 = memberSetting.getHezuo3();

        Integer guanli1 = memberSetting.getGuanli1();//管理奖比例1、2、3级
        Integer guanli2 = memberSetting.getGuanli2();
        Integer guanli3 = memberSetting.getGuanli3();

        String memberLevel = member.getMemberlevel();//注册会员级别
        String fromName = member.getLoginName();
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
            setPv(contact,area,bonus,hezuo1,hezuo2,hezuo3,guanli1,guanli2,guanli3,member.getLoginName(),fromName);
            contact = contactM.getContact();
            area = contactM.getArea();
            fromName = contactM.getLoginName();
            contactM = memberDao.getMemberByLoginName(contact);
        }

    }

    //接点人及父级接点人添加pv
    public void  setPv(String contact,String area,BigDecimal bonus,Integer hezuo1,Integer hezuo2,Integer hezuo3,Integer guanli1,Integer guanli2,Integer guanli3,String zhuceName,String fromName){
        BonusTotal bonusTotal = bonusTotalDao.getBonusTotalByLoginName(contact);//接点人奖金表
        if("A".equals(area)){
            bonusTotal.setApvTotal(bonusTotal.getApvTotal().add(bonus));
            bonusTotal.setApv(bonusTotal.getApv().add(bonus));
        }else {
            bonusTotal.setBpvTotal(bonusTotal.getBpvTotal().add(bonus));
            bonusTotal.setBpv(bonusTotal.getBpv().add(bonus));
        }
        BigDecimal apv = bonusTotal.getApv();
        BigDecimal bpv = bonusTotal.getBpv();
        Member member = memberDao.getMemberByLoginName(contact);
        String level = member.getMemberlevel();
        BigDecimal small = BigDecimal.ZERO;
        if(apv.compareTo(BigDecimal.ZERO)>0 && bpv.compareTo(BigDecimal.ZERO)>0){
            if(apv.compareTo(bpv)>0){//A>B,按B区算
                small = bpv;
            }else{//B>A,按A区算
                small = apv;
            }
            BigDecimal hezuo = BigDecimal.ZERO;//合作奖
            if (!"0".equals(level) && !"0".equals(member.getStatus())){
                if("1".equals(level)){
                    hezuo = small.multiply(BigDecimal.valueOf(hezuo1)).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                }else if("2".equals(level)){
                    hezuo = small.multiply(BigDecimal.valueOf(hezuo2)).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                }else{
                    hezuo = small.multiply(BigDecimal.valueOf(hezuo3)).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                }
                PvDetail pvDetail = new PvDetail();
                pvDetail.setLoginName(contact);
                pvDetail.setNote("合作奖");
                pvDetail.setPvTotal(hezuo);
                pvDetail.setPvSheng(hezuo.multiply(new BigDecimal(0.95)));
                pvDetail.setPvDues(hezuo.multiply(new BigDecimal(0.05)));
                pvDetail.setPvtype("2");
                pvDetail.setFromName(fromName);
                pvDetail.setZhuceName(zhuceName);
                pvDetailService.save(pvDetail);

                hezuo = hezuo.multiply(new BigDecimal(0.95));
                bonusTotal.setBonusTotal(bonusTotal.getBonusTotal().add(hezuo));
                bonusTotal.setBonusCurrent(bonusTotal.getBonusCurrent().add(hezuo));
                bonusTotal.setApv(bonusTotal.getApv().subtract(small));
                bonusTotal.setBpv(bonusTotal.getBpv().subtract(small));

                DayStatistics statistics = statisticsService.getByDate(DateUtils.getDate());
                statistics.setOutAllBonus(statistics.getOutAllBonus().add(hezuo));
                statistics.setOutBonus(statistics.getOutBonus().add(hezuo));
                statistics.setBobi(statistics.getOutBonus().multiply(new BigDecimal("100")).divide(statistics.getNewBonus(),2,BigDecimal.ROUND_HALF_UP));
                statistics.setAllBobi(statistics.getOutAllBonus().multiply(new BigDecimal("100")).divide(statistics.getAllBonus(),2,BigDecimal.ROUND_HALF_UP));
                statisticsService.save(statistics);
            }
            //管理奖，推荐人拿合作奖的n%
            String referee = member.getReferee();
            Member memberR = memberDao.getMemberByLoginName(referee);//当前碰对的直推人
            if(!"0".equals(referee)){
                String levelR = member.getMemberlevel();
                BonusTotal bonusTotalR = bonusTotalDao.getBonusTotalByLoginName(referee);//直推人的奖金表
                BigDecimal guanli = BigDecimal.ZERO;
                if(!"0".equals(levelR) && !"0".equals(memberR.getStatus())){
                    if("1".equals(levelR)){
                        guanli = hezuo.multiply(BigDecimal.valueOf(guanli1)).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                    }else if("2".equals(levelR)){
                        guanli = hezuo.multiply(BigDecimal.valueOf(guanli2)).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                    }else{
                        guanli = hezuo.multiply(BigDecimal.valueOf(guanli3)).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
                    }
                    PvDetail pvDetail = new PvDetail();
                    pvDetail.setLoginName(referee);
                    pvDetail.setNote("管理奖");
                    pvDetail.setPvTotal(guanli);
                    pvDetail.setPvSheng(guanli.multiply(new BigDecimal(0.95)));
                    pvDetail.setPvDues(guanli.multiply(new BigDecimal(0.05)));
                    pvDetail.setPvtype("3");
                    pvDetail.setFromName(contact);
                    pvDetail.setZhuceName(zhuceName);
                    pvDetailService.save(pvDetail);

                    guanli = guanli.multiply(new BigDecimal(0.95));
                    bonusTotalR.setBonusTotal(bonusTotalR.getBonusTotal().add(guanli));
                    bonusTotalR.setBonusCurrent(bonusTotalR.getBonusCurrent().add(guanli));
                    bonusTotalDao.updateBouns(bonusTotalR);

                    DayStatistics statistics = statisticsService.getByDate(DateUtils.getDate());
                    statistics.setOutAllBonus(statistics.getOutAllBonus().add(guanli));
                    statistics.setOutBonus(statistics.getOutBonus().add(guanli));
                    statistics.setBobi(statistics.getOutBonus().multiply(new BigDecimal("100")).divide(statistics.getNewBonus(),2,BigDecimal.ROUND_HALF_UP));
                    statistics.setAllBobi(statistics.getOutAllBonus().multiply(new BigDecimal("100")).divide(statistics.getAllBonus(),2,BigDecimal.ROUND_HALF_UP));
                    statisticsService.save(statistics);
                }

            }
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

        if(!"0".equals(memberLevel) && !"0".equals(refreeLevel) && !"0".equals(refreeM.getStatus())){//普通会员不计算
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
            PvDetail pvDetail = new PvDetail();
            pvDetail.setLoginName(refree);
            pvDetail.setNote("直推奖");
            pvDetail.setPvTotal(bonus);
            pvDetail.setPvSheng(bonus.multiply(new BigDecimal(0.95)));
            pvDetail.setPvDues(bonus.multiply(new BigDecimal(0.05)));
            pvDetail.setPvtype("1");
            pvDetail.setFromName(member.getLoginName());
            pvDetail.setZhuceName(member.getLoginName());
            pvDetailService.save(pvDetail);

            bonus = bonus.multiply(new BigDecimal(0.95));
            BonusTotal bonusTotal = bonusTotalDao.getBonusTotalByLoginName(refree);//推荐人奖金表
            bonusTotal.setBonusTotal(bonusTotal.getBonusTotal().add(bonus));
            bonusTotal.setBonusCurrent(bonusTotal.getBonusCurrent().add(bonus));
            bonusTotalDao.updateBouns(bonusTotal);

            DayStatistics statistics = statisticsService.getByDate(DateUtils.getDate());
            statistics.setOutAllBonus(statistics.getOutAllBonus().add(bonus));
            statistics.setOutBonus(statistics.getOutBonus().add(bonus));
            statistics.setBobi(statistics.getOutBonus().multiply(new BigDecimal("100")).divide(statistics.getNewBonus(),2,BigDecimal.ROUND_HALF_UP));
            statistics.setAllBobi(statistics.getOutAllBonus().multiply(new BigDecimal("100")).divide(statistics.getAllBonus(),2,BigDecimal.ROUND_HALF_UP));
            statisticsService.save(statistics);
        }
    }
}