/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.dao.transfer;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.core.entity.transfer.Transfer;

/**
 * 积分转账DAO接口
 * @author li
 * @version 2018-11-06
 */
@MyBatisDao
public interface TransferDao extends CrudDao<Transfer> {
	
}