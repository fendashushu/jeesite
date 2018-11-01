/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.service.pv;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.core.entity.pv.PvTotal;
import com.thinkgem.jeesite.modules.core.dao.pv.PvTotalDao;

/**
 * 积分汇总Service
 * @author 李延明
 * @version 2018-11-01
 */
@Service
@Transactional(readOnly = true)
public class PvTotalService extends CrudService<PvTotalDao, PvTotal> {

	public PvTotal get(String id) {
		return super.get(id);
	}
	
	public List<PvTotal> findList(PvTotal pvTotal) {
		return super.findList(pvTotal);
	}
	
	public Page<PvTotal> findPage(Page<PvTotal> page, PvTotal pvTotal) {
		return super.findPage(page, pvTotal);
	}
	
	@Transactional(readOnly = false)
	public void save(PvTotal pvTotal) {
		super.save(pvTotal);
	}
	
	@Transactional(readOnly = false)
	public void delete(PvTotal pvTotal) {
		super.delete(pvTotal);
	}
	
}