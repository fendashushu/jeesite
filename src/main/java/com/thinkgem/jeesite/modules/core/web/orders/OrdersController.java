/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.web.orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.core.entity.goods.Goods;
import com.thinkgem.jeesite.modules.core.service.goods.GoodsService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.core.entity.orders.Orders;
import com.thinkgem.jeesite.modules.core.service.orders.OrdersService;

/**
 * 订单Controller
 * @author li
 * @version 2018-11-20
 */
@Controller
@RequestMapping(value = "${adminPath}/core/orders/orders")
public class OrdersController extends BaseController {

	@Autowired
	private OrdersService ordersService;
	@Autowired
    private GoodsService goodsService;
	
	@ModelAttribute
	public Orders get(@RequestParam(required=false) String id) {
		Orders entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ordersService.get(id);
		}
		if (entity == null){
			entity = new Orders();
		}
		return entity;
	}
	
	@RequiresPermissions("core:orders:orders:view")
	@RequestMapping(value = {"list", ""})
	public String list(Orders orders, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Orders> page = ordersService.findPage(new Page<Orders>(request, response), orders); 
		model.addAttribute("page", page);
		return "modules/core/orders/ordersList";
	}

    @RequiresPermissions("core:goods:goods:view")
    @RequestMapping(value = "orderDetail")
    public String orderDetail(Goods goods, Model model) {
        goods = goodsService.get(goods.getId());
        Orders orders = new Orders();
        orders.setGoodsId(goods.getId());
        orders.setGoodsPrice(goods.getPrice());
        model.addAttribute("orders", orders);
        model.addAttribute("goods", goods);
        return "modules/core/goods/orderDetail";
    }

	@RequiresPermissions("core:orders:orders:view")
	@RequestMapping(value = "form")
	public String form(Orders orders, Model model) {
		model.addAttribute("orders", orders);
		return "modules/core/orders/ordersForm";
	}

	@RequiresPermissions("core:orders:orders:edit")
	@RequestMapping(value = "save")
	public String save(Orders orders, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orders)){
			return form(orders, model);
		}
		orders.setLoginName(UserUtils.getUser().getLoginName());
        orders.setOrderId(System.currentTimeMillis());
        orders.setStatus("0");
        orders.setOrderType("0");
		ordersService.save(orders);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/core/orders/orders/?repage";
	}
	
	@RequiresPermissions("core:orders:orders:edit")
	@RequestMapping(value = "delete")
	public String delete(Orders orders, RedirectAttributes redirectAttributes) {
		ordersService.delete(orders);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/core/orders/orders/?repage";
	}

}