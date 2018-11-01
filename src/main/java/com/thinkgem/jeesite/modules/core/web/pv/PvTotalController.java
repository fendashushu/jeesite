/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.web.pv;

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
import com.thinkgem.jeesite.modules.core.entity.pv.PvTotal;
import com.thinkgem.jeesite.modules.core.service.pv.PvTotalService;

/**
 * 积分汇总Controller
 * @author 李延明
 * @version 2018-11-01
 */
@Controller
@RequestMapping(value = "${adminPath}/core/pv/pvTotal")
public class PvTotalController extends BaseController {

	@Autowired
	private PvTotalService pvTotalService;
	
	@ModelAttribute
	public PvTotal get(@RequestParam(required=false) String id) {
		PvTotal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pvTotalService.get(id);
		}
		if (entity == null){
			entity = new PvTotal();
		}
		return entity;
	}
	
	@RequiresPermissions("core:pv:pvTotal:view")
	@RequestMapping(value = {"list", ""})
	public String list(PvTotal pvTotal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PvTotal> page = pvTotalService.findPage(new Page<PvTotal>(request, response), pvTotal); 
		model.addAttribute("page", page);
		return "modules/core/pv/pvTotalList";
	}

	@RequiresPermissions("core:pv:pvTotal:view")
	@RequestMapping(value = "form")
	public String form(PvTotal pvTotal, Model model) {
		model.addAttribute("pvTotal", pvTotal);
		return "modules/core/pv/pvTotalForm";
	}

	@RequiresPermissions("core:pv:pvTotal:edit")
	@RequestMapping(value = "save")
	public String save(PvTotal pvTotal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pvTotal)){
			return form(pvTotal, model);
		}
		pvTotalService.save(pvTotal);
		addMessage(redirectAttributes, "保存积分汇总成功");
		return "redirect:"+Global.getAdminPath()+"/core/pv/pvTotal/?repage";
	}
	
	@RequiresPermissions("core:pv:pvTotal:edit")
	@RequestMapping(value = "delete")
	public String delete(PvTotal pvTotal, RedirectAttributes redirectAttributes) {
		pvTotalService.delete(pvTotal);
		addMessage(redirectAttributes, "删除积分汇总成功");
		return "redirect:"+Global.getAdminPath()+"/core/pv/pvTotal/?repage";
	}

}