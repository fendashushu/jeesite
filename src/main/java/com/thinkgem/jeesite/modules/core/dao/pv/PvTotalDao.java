/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.dao.pv;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.core.entity.pv.PvTotal;

/**
 * 积分汇总DAO接口
 * @author 李延明
 * @version 2018-11-01
 */
@MyBatisDao
public interface PvTotalDao extends CrudDao<PvTotal> {
	
}