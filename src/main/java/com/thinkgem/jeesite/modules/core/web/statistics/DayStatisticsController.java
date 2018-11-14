/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.web.statistics;

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
import com.thinkgem.jeesite.modules.core.entity.statistics.DayStatistics;
import com.thinkgem.jeesite.modules.core.service.statistics.DayStatisticsService;

/**
 * 统计Controller
 * @author li
 * @version 2018-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/core/statistics/dayStatistics")
public class DayStatisticsController extends BaseController {

	@Autowired
	private DayStatisticsService dayStatisticsService;
	
	@ModelAttribute
	public DayStatistics get(@RequestParam(required=false) String id) {
		DayStatistics entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dayStatisticsService.get(id);
		}
		if (entity == null){
			entity = new DayStatistics();
		}
		return entity;
	}
	
	@RequiresPermissions("core:statistics:dayStatistics:view")
	@RequestMapping(value = {"list", ""})
	public String list(DayStatistics dayStatistics, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DayStatistics> page = dayStatisticsService.findPage(new Page<DayStatistics>(request, response), dayStatistics); 
		model.addAttribute("page", page);
		return "modules/core/statistics/dayStatisticsList";
	}

	@RequiresPermissions("core:statistics:dayStatistics:view")
	@RequestMapping(value = "form")
	public String form(DayStatistics dayStatistics, Model model) {
		model.addAttribute("dayStatistics", dayStatistics);
		return "modules/core/statistics/dayStatisticsForm";
	}

	@RequiresPermissions("core:statistics:dayStatistics:edit")
	@RequestMapping(value = "save")
	public String save(DayStatistics dayStatistics, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, dayStatistics)){
			return form(dayStatistics, model);
		}
		dayStatisticsService.save(dayStatistics);
		addMessage(redirectAttributes, "保存统计成功");
		return "redirect:"+Global.getAdminPath()+"/core/statistics/dayStatistics/?repage";
	}
	
	@RequiresPermissions("core:statistics:dayStatistics:edit")
	@RequestMapping(value = "delete")
	public String delete(DayStatistics dayStatistics, RedirectAttributes redirectAttributes) {
		dayStatisticsService.delete(dayStatistics);
		addMessage(redirectAttributes, "删除统计成功");
		return "redirect:"+Global.getAdminPath()+"/core/statistics/dayStatistics/?repage";
	}

}