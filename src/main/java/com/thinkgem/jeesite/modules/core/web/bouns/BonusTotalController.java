/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.web.bouns;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.core.entity.bouns.BonusTotal;
import com.thinkgem.jeesite.modules.core.service.bouns.BonusTotalService;

/**
 * 业绩Controller
 * @author 李延明
 * @version 2018-11-01
 */
@Controller
@RequestMapping(value = "${adminPath}/core/bouns/bonusTotal")
public class BonusTotalController extends BaseController {

	@Autowired
	private BonusTotalService bonusTotalService;
	
	@ModelAttribute
	public BonusTotal get(@RequestParam(required=false) String id) {
		BonusTotal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bonusTotalService.get(id);
		}
		if (entity == null){
			entity = new BonusTotal();
		}
		return entity;
	}
	
	@RequiresPermissions("core:bouns:bonusTotal:view")
	@RequestMapping(value = {"list", ""})
	public String list(BonusTotal bonusTotal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BonusTotal> page = bonusTotalService.findPage(new Page<BonusTotal>(request, response), bonusTotal); 
		model.addAttribute("page", page);
		return "modules/core/bouns/bonusTotalList";
	}

	@RequiresPermissions("core:bouns:bonusTotal:view")
	@RequestMapping(value = "form")
	public String form(BonusTotal bonusTotal, Model model) {
		model.addAttribute("bonusTotal", bonusTotal);
		return "modules/core/bouns/bonusTotalForm";
	}

	@RequiresPermissions("core:bouns:bonusTotal:edit")
	@RequestMapping(value = "save")
	public String save(BonusTotal bonusTotal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bonusTotal)){
			return form(bonusTotal, model);
		}
		bonusTotalService.save(bonusTotal);
		addMessage(redirectAttributes, "保存业绩汇总成功");
		return "redirect:"+Global.getAdminPath()+"/core/bouns/bonusTotal/?repage";
	}
	
	@RequiresPermissions("core:bouns:bonusTotal:edit")
	@RequestMapping(value = "delete")
	public String delete(BonusTotal bonusTotal, RedirectAttributes redirectAttributes) {
		bonusTotalService.delete(bonusTotal);
		addMessage(redirectAttributes, "删除业绩汇总成功");
		return "redirect:"+Global.getAdminPath()+"/core/bouns/bonusTotal/?repage";
	}

}