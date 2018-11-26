/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.service.orders;

import java.math.BigDecimal;
import java.util.List;

import com.thinkgem.jeesite.modules.core.entity.bonus.BonusTotal;
import com.thinkgem.jeesite.modules.core.entity.goods.Goods;
import com.thinkgem.jeesite.modules.core.entity.member.Member;
import com.thinkgem.jeesite.modules.core.service.bonus.BonusTotalService;
import com.thinkgem.jeesite.modules.core.service.goods.GoodsService;
import com.thinkgem.jeesite.modules.core.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.core.entity.orders.Orders;
import com.thinkgem.jeesite.modules.core.dao.orders.OrdersDao;

/**
 * 订单Service
 * @author li
 * @version 2018-11-20
 */
@Service
@Transactional(readOnly = true)
public class OrdersService extends CrudService<OrdersDao, Orders> {
    @Autowired
    private OrdersDao ordersDao;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BonusTotalService bonusTotalService;

	public Orders get(String id) {
		return super.get(id);
	}
	
	public List<Orders> findList(Orders orders) {
		return super.findList(orders);
	}
	
	public Page<Orders> findPage(Page<Orders> page, Orders orders) {
		return super.findPage(page, orders);
	}

	public Page<Orders> myOrders(Page<Orders> page, Orders orders) {
        orders.setPage(page);
        page.setList(ordersDao.myOrders(orders));
        return page;
	}

	public Page<Orders> deliverGoodsList(Page<Orders> page, Orders orders) {
        orders.setPage(page);
        page.setList(ordersDao.deliverGoodsList(orders));
        return page;
	}
	
	@Transactional(readOnly = false)
	public void save(Orders orders) {
		super.save(orders);
	}


	@Transactional(readOnly = false)
	public void buy(Orders orders) {
		super.save(orders);
        Goods goods = goodsService.get(orders.getGoodsId());
        goods.setSaleNum((goods.getSaleNum()==null?0:goods.getSaleNum())+orders.getGoodsCount());
        goodsService.save(goods);
        Member member = memberService.getMemberByLoginName(orders.getLoginName());
        String isStore = member.getIsstore();
        BonusTotal bonusTotal = bonusTotalService.getBonusByLoginName(orders.getLoginName());
        BigDecimal normal = orders.getGoodsPrice().multiply(new BigDecimal(orders.getGoodsCount()));
        BigDecimal vip = orders.getVipPrice().multiply(new BigDecimal(orders.getGoodsCount()));
        if("1".equals(isStore)){
            BigDecimal total = new BigDecimal(orders.getGoodsCount()).multiply(goods.getPv());
            if(total.compareTo(BigDecimal.ZERO)>0){
                bonusTotalService.jinhuo(member,total);
            }
            bonusTotal.setBonusCurrent(bonusTotal.getBonusCurrent().subtract(vip));
        }else{
            bonusTotal.setBonusCurrent(bonusTotal.getBonusCurrent().subtract(normal));
        }
        bonusTotalService.updateBouns(bonusTotal);
	}

	@Transactional(readOnly = false)
	public void delete(Orders orders) {
		super.delete(orders);
	}
	
}