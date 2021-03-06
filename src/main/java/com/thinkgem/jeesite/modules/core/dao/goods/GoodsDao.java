/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.dao.goods;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.core.entity.goods.Goods;

import java.util.List;

/**
 * 商品信息DAO接口
 * @author li
 * @version 2018-11-07
 */
@MyBatisDao
public interface GoodsDao extends CrudDao<Goods> {

    public void publish(Goods goods);

    public List<Goods> shopList(Goods goods);
}