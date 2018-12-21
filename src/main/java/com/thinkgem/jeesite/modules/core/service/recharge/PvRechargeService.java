/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.service.recharge;

import java.util.List;

import com.thinkgem.jeesite.modules.core.dao.bonus.BonusTotalDao;
import com.thinkgem.jeesite.modules.core.entity.bonus.BonusTotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.core.entity.recharge.PvRecharge;
import com.thinkgem.jeesite.modules.core.dao.recharge.PvRechargeDao;

/**
 * 充值Service
 * @author li
 * @version 2018-11-05
 */
@Service
@Transactional(readOnly = true)
public class PvRechargeService extends CrudService<PvRechargeDao, PvRecharge> {
    @Autowired
    private BonusTotalDao bonusTotalDao;
    @Autowired
    private PvRechargeDao pvRechargeDao;

	public PvRecharge get(String id) {
		return super.get(id);
	}
	
	public List<PvRecharge> findList(PvRecharge pvRecharge) {
		return super.findList(pvRecharge);
	}
	
	public Page<PvRecharge> findPage(Page<PvRecharge> page, PvRecharge pvRecharge) {
		return super.findPage(page, pvRecharge);
	}

	public Page<PvRecharge> getConfirmRecharge(Page<PvRecharge> page, PvRecharge pvRecharge) {
        pvRecharge.setPage(page);
        page.setList(pvRechargeDao.getConfirmRecharge(pvRecharge));
        return page;
	}
	
	@Transactional(readOnly = false)
	public void save(PvRecharge pvRecharge) {
		super.save(pvRecharge);
	}

	@Transactional(readOnly = false)
	public void confirmRecharge(PvRecharge pvRecharge) {
		pvRechargeDao.confirmRecharge(pvRecharge);
        BonusTotal bonusTotal = bonusTotalDao.getBonusTotalByLoginName(pvRecharge.getLoginName());
        if (bonusTotal != null){
            bonusTotal.setMoneyTotal(bonusTotal.getMoneyTotal().add(pvRecharge.getAmount()));
            bonusTotal.setMoneyCurrent(bonusTotal.getMoneyCurrent().add(pvRecharge.getAmount()));
            bonusTotalDao.updateBouns(bonusTotal);
        }
	}
	
	@Transactional(readOnly = false)
	public void delete(PvRecharge pvRecharge) {
		super.delete(pvRecharge);
	}
	
}