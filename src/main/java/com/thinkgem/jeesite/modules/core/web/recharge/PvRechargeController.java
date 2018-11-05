/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.web.recharge;

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
import com.thinkgem.jeesite.modules.core.entity.recharge.PvRecharge;
import com.thinkgem.jeesite.modules.core.service.recharge.PvRechargeService;

/**
 * 充值Controller
 * @author li
 * @version 2018-11-05
 */
@Controller
@RequestMapping(value = "${adminPath}/core/recharge/pvRecharge")
public class PvRechargeController extends BaseController {

	@Autowired
	private PvRechargeService pvRechargeService;
	
	@ModelAttribute
	public PvRecharge get(@RequestParam(required=false) String id) {
		PvRecharge entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pvRechargeService.get(id);
		}
		if (entity == null){
			entity = new PvRecharge();
		}
		return entity;
	}
	
	@RequiresPermissions("core:recharge:pvRecharge:view")
	@RequestMapping(value = {"list", ""})
	public String list(PvRecharge pvRecharge, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PvRecharge> page = pvRechargeService.findPage(new Page<PvRecharge>(request, response), pvRecharge); 
		model.addAttribute("page", page);
		return "modules/core/recharge/pvRechargeList";
	}

	@RequiresPermissions("core:recharge:pvRecharge:view")
	@RequestMapping(value = "form")
	public String form(PvRecharge pvRecharge, Model model) {
		model.addAttribute("pvRecharge", pvRecharge);
		return "modules/core/recharge/pvRechargeForm";
	}

	@RequiresPermissions("core:recharge:pvRecharge:edit")
	@RequestMapping(value = "save")
	public String save(PvRecharge pvRecharge, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pvRecharge)){
			return form(pvRecharge, model);
		}
		pvRechargeService.save(pvRecharge);
		addMessage(redirectAttributes, "保存充值成功");
		return "redirect:"+Global.getAdminPath()+"/core/recharge/pvRecharge/?repage";
	}
	
	@RequiresPermissions("core:recharge:pvRecharge:edit")
	@RequestMapping(value = "delete")
	public String delete(PvRecharge pvRecharge, RedirectAttributes redirectAttributes) {
		pvRechargeService.delete(pvRecharge);
		addMessage(redirectAttributes, "删除充值成功");
		return "redirect:"+Global.getAdminPath()+"/core/recharge/pvRecharge/?repage";
	}

}