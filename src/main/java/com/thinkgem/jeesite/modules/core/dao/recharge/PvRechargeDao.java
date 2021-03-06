/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.dao.recharge;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.core.entity.recharge.PvRecharge;

import java.util.List;

/**
 * 充值DAO接口
 * @author li
 * @version 2018-11-05
 */
@MyBatisDao
public interface PvRechargeDao extends CrudDao<PvRecharge> {
	public List<PvRecharge> getConfirmRecharge(PvRecharge pvRecharge);
	public void confirmRecharge(PvRecharge pvRecharge);
}