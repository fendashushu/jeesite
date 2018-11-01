/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.service.bouns;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.core.entity.bouns.BonusTotal;
import com.thinkgem.jeesite.modules.core.dao.bouns.BonusTotalDao;

/**
 * 业绩Service
 * @author 李延明
 * @version 2018-11-01
 */
@Service
@Transactional(readOnly = true)
public class BonusTotalService extends CrudService<BonusTotalDao, BonusTotal> {

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
	
}