/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.service.pvdetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.core.entity.pvdetail.PvDetail;
import com.thinkgem.jeesite.modules.core.dao.pvdetail.PvDetailDao;

/**
 * 积分详情Service
 * @author li
 * @version 2018-11-02
 */
@Service
@Transactional(readOnly = true)
public class PvDetailService extends CrudService<PvDetailDao, PvDetail> {
    @Autowired
    private PvDetailDao pvDetailDao;

	public PvDetail get(String id) {
		return super.get(id);
	}
	
	public List<PvDetail> findList(PvDetail pvDetail) {
		return super.findList(pvDetail);
	}

    public Page<PvDetail> getDetails(Page<PvDetail> page,PvDetail pvDetail) {
        pvDetail.setPage(page);
        page.setList(pvDetailDao.getDetails(pvDetail));
        return page;
    }
	
	public Page<PvDetail> findPage(Page<PvDetail> page, PvDetail pvDetail) {
		return super.findPage(page, pvDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(PvDetail pvDetail) {
		super.save(pvDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(PvDetail pvDetail) {
		super.delete(pvDetail);
	}
	
}