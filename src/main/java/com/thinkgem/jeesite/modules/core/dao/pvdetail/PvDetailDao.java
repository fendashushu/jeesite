/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.dao.pvdetail;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.core.entity.pvdetail.PvDetail;

/**
 * 积分详情DAO接口
 * @author li
 * @version 2018-11-02
 */
@MyBatisDao
public interface PvDetailDao extends CrudDao<PvDetail> {
	
}