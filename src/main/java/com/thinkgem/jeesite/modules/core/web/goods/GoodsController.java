/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.web.goods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.core.entity.orders.Orders;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.core.entity.goods.Goods;
import com.thinkgem.jeesite.modules.core.service.goods.GoodsService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品信息Controller
 * @author li
 * @version 2018-11-07
 */
@Controller
@RequestMapping(value = "${adminPath}/core/goods/goods")
public class GoodsController extends BaseController {

	@Autowired
	private GoodsService goodsService;
	
	@ModelAttribute
	public Goods get(@RequestParam(required=false) String id) {
		Goods entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = goodsService.get(id);
		}
		if (entity == null){
			entity = new Goods();
		}
		return entity;
	}
	
	@RequiresPermissions("core:goods:goods:view")
	@RequestMapping(value = {"list", ""})
	public String list(Goods goods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Goods> page = goodsService.findPage(new Page<Goods>(request, response), goods);
		model.addAttribute("page", page);
		return "modules/core/goods/goodsList";
	}

	@RequiresPermissions("core:goods:goods:view")
	@RequestMapping(value = {"shop"})
	public String shop(Goods goods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Goods> page = goodsService.shopList(new Page<Goods>(request, response), goods);
		model.addAttribute("page", page);
		return "modules/core/goods/goodsShop";
	}

	@RequiresPermissions("core:goods:goods:edit")
	@RequestMapping(value = "publish")
    @ResponseBody
	public Map publish(Goods goods, Model model, HttpServletRequest request, HttpServletResponse response) {
	    Map map = new HashMap();
	    String id = request.getParameter("id");
	    goods.setStatus("1");
	    goods.setId(id);
	    goods.setPublishDate(new Date());
	    try {
            goodsService.publish(goods);
	        map.put("result",true);
	        map.put("msg","发布成功！");
        }catch (Exception e){
            map.put("result",false);
            map.put("msg","发布失败！");
        }
		return map;
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


	@RequiresPermissions("core:goods:goods:view")
	@RequestMapping(value = "form")
	public String form(Goods goods, Model model) {
		model.addAttribute("goods", goods);
		return "modules/core/goods/goodsForm";
	}

	@RequiresPermissions("core:goods:goods:edit")
	@RequestMapping(value = "save")
	public String save(Goods goods, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, goods)){
			return form(goods, model);
		}
		if(goods.getStatus() == null || "".equals(goods.getStatus())){
		    goods.setStatus("0");
        }
		goodsService.save(goods);
		addMessage(redirectAttributes, "保存商品信息成功");
		return "redirect:"+Global.getAdminPath()+"/core/goods/goods/?repage";
	}
	
	@RequiresPermissions("core:goods:goods:edit")
	@RequestMapping(value = "delete")
	public String delete(Goods goods, RedirectAttributes redirectAttributes) {
		goodsService.delete(goods);
		addMessage(redirectAttributes, "删除商品信息成功");
		return "redirect:"+Global.getAdminPath()+"/core/goods/goods/?repage";
	}

}