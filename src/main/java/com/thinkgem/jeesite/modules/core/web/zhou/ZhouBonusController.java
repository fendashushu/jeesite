/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.web.zhou;

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
import com.thinkgem.jeesite.modules.core.entity.zhou.ZhouBonus;
import com.thinkgem.jeesite.modules.core.service.zhou.ZhouBonusService;

/**
 * 周封顶Controller
 * @author li
 * @version 2018-11-29
 */
@Controller
@RequestMapping(value = "${adminPath}/core/zhou/zhouBonus")
public class ZhouBonusController extends BaseController {

	@Autowired
	private ZhouBonusService zhouBonusService;
	
	@ModelAttribute
	public ZhouBonus get(@RequestParam(required=false) String id) {
		ZhouBonus entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zhouBonusService.get(id);
		}
		if (entity == null){
			entity = new ZhouBonus();
		}
		return entity;
	}
	
	@RequiresPermissions("core:zhou:zhouBonus:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZhouBonus zhouBonus, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZhouBonus> page = zhouBonusService.findPage(new Page<ZhouBonus>(request, response), zhouBonus); 
		model.addAttribute("page", page);
		return "modules/core/zhou/zhouBonusList";
	}

	@RequiresPermissions("core:zhou:zhouBonus:view")
	@RequestMapping(value = "form")
	public String form(ZhouBonus zhouBonus, Model model) {
		model.addAttribute("zhouBonus", zhouBonus);
		return "modules/core/zhou/zhouBonusForm";
	}

	@RequiresPermissions("core:zhou:zhouBonus:edit")
	@RequestMapping(value = "save")
	public String save(ZhouBonus zhouBonus, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zhouBonus)){
			return form(zhouBonus, model);
		}
		zhouBonusService.save(zhouBonus);
		addMessage(redirectAttributes, "保存周封顶成功");
		return "redirect:"+Global.getAdminPath()+"/core/zhou/zhouBonus/?repage";
	}
	
	@RequiresPermissions("core:zhou:zhouBonus:edit")
	@RequestMapping(value = "delete")
	public String delete(ZhouBonus zhouBonus, RedirectAttributes redirectAttributes) {
		zhouBonusService.delete(zhouBonus);
		addMessage(redirectAttributes, "删除周封顶成功");
		return "redirect:"+Global.getAdminPath()+"/core/zhou/zhouBonus/?repage";
	}

}