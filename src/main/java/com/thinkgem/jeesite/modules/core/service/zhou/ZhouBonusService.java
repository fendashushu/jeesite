/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.service.zhou;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.core.entity.zhou.ZhouBonus;
import com.thinkgem.jeesite.modules.core.dao.zhou.ZhouBonusDao;

/**
 * 周封顶Service
 * @author li
 * @version 2018-11-29
 */
@Service
@Transactional(readOnly = true)
public class ZhouBonusService extends CrudService<ZhouBonusDao, ZhouBonus> {
    @Autowired
    private ZhouBonusDao zhouBonusDao;

	public ZhouBonus get(String id) {
		return super.get(id);
	}
	
	public List<ZhouBonus> findList(ZhouBonus zhouBonus) {
		return super.findList(zhouBonus);
	}
	
	public Page<ZhouBonus> findPage(Page<ZhouBonus> page, ZhouBonus zhouBonus) {
		return super.findPage(page, zhouBonus);
	}
	
	@Transactional(readOnly = false)
	public void save(ZhouBonus zhouBonus) {
		super.save(zhouBonus);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZhouBonus zhouBonus) {
		super.delete(zhouBonus);
	}

	public ZhouBonus getByLoginNameAndDate(ZhouBonus zhouBonus){
	    return zhouBonusDao.getByLoginNameAndDate(zhouBonus);
    }
}