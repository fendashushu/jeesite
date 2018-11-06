/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.service.transfer;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.core.entity.transfer.Transfer;
import com.thinkgem.jeesite.modules.core.dao.transfer.TransferDao;

/**
 * 积分转账Service
 * @author li
 * @version 2018-11-06
 */
@Service
@Transactional(readOnly = true)
public class TransferService extends CrudService<TransferDao, Transfer> {

	public Transfer get(String id) {
		return super.get(id);
	}
	
	public List<Transfer> findList(Transfer transfer) {
		return super.findList(transfer);
	}
	
	public Page<Transfer> findPage(Page<Transfer> page, Transfer transfer) {
		return super.findPage(page, transfer);
	}
	
	@Transactional(readOnly = false)
	public void save(Transfer transfer) {
		super.save(transfer);
	}
	
	@Transactional(readOnly = false)
	public void delete(Transfer transfer) {
		super.delete(transfer);
	}
	
}