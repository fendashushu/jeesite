/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.dao.orders;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.core.entity.orders.Orders;

/**
 * 订单DAO接口
 * @author li
 * @version 2018-11-20
 */
@MyBatisDao
public interface OrdersDao extends CrudDao<Orders> {
	
}