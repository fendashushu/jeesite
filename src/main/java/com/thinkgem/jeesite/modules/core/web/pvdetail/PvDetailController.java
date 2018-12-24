/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.web.pvdetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.core.entity.pvdetail.PvDetail;
import com.thinkgem.jeesite.modules.core.service.pvdetail.PvDetailService;

import java.util.Date;
import java.util.List;

/**
 * 积分详情Controller
 * @author li
 * @version 2018-11-02
 */
@Controller
@RequestMapping(value = "${adminPath}/core/pvdetail/pvDetail")
public class PvDetailController extends BaseController {

	@Autowired
	private PvDetailService pvDetailService;
	
	@ModelAttribute
	public PvDetail get(@RequestParam(required=false) String id) {
		PvDetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pvDetailService.get(id);
		}
		if (entity == null){
			entity = new PvDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("core:pvdetail:pvDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(PvDetail pvDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
        //pvDetail.setLoginName(UserUtils.getUser().getLoginName());
        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        if(beginDate != null && !"".equals(beginDate)){
            pvDetail.setBeginDate(beginDate);
        }
        if(endDate != null && !"".equals(endDate)){
            pvDetail.setEndDate(endDate);
        }
		Page<PvDetail> page = pvDetailService.findPage(new Page<PvDetail>(request, response), pvDetail); 
		model.addAttribute("page", page);
		return "modules/core/pvdetail/pvDetailList";
	}

	@RequiresPermissions("core:pvdetail:pvDetail:view")
	@RequestMapping(value = {"seven"})
	public String seven(PvDetail pvDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
        //pvDetail.setLoginName(UserUtils.getUser().getLoginName());
		Page<PvDetail> page = pvDetailService.sevenPage(new Page<PvDetail>(request, response), pvDetail);
		model.addAttribute("page", page);
		return "modules/core/pvdetail/pvDetailSeven";
	}

	@RequiresPermissions("core:pvdetail:pvDetail:view")
	@RequestMapping(value = {"detail"})
	public String detail(PvDetail pvDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
        String createDate = request.getParameter("createDate");
        String flag = request.getParameter("flag");
        String name = request.getParameter("name");
        if("detail".equals(flag)){
            pvDetail.setFromName(name);
            model.addAttribute("flag", "detail");
        }else if("allDetail".equals(flag)){
            model.addAttribute("flag", "detail");
        }else{
            pvDetail.setPvDate(createDate);
            pvDetail.setLoginName(UserUtils.getUser().getLoginName());
        }
        Page<PvDetail> page = pvDetailService.getDetails(new Page<PvDetail>(request, response), pvDetail);
        model.addAttribute("page", page);
        model.addAttribute("createDate", createDate);
		return "modules/core/pvdetail/pvDetailDetail";
	}

	@RequiresPermissions("core:pvdetail:pvDetail:view")
	@RequestMapping(value = "form")
	public String form(PvDetail pvDetail, Model model) {
		model.addAttribute("pvDetail", pvDetail);
		return "modules/core/pvdetail/pvDetailForm";
	}

	@RequiresPermissions("core:pvdetail:pvDetail:edit")
	@RequestMapping(value = "save")
	public String save(PvDetail pvDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pvDetail)){
			return form(pvDetail, model);
		}
		pvDetailService.save(pvDetail);
		addMessage(redirectAttributes, "保存积分详情成功");
		return "redirect:"+Global.getAdminPath()+"/core/pvdetail/pvDetail/?repage";
	}
	
	@RequiresPermissions("core:pvdetail:pvDetail:edit")
	@RequestMapping(value = "delete")
	public String delete(PvDetail pvDetail, RedirectAttributes redirectAttributes) {
		pvDetailService.delete(pvDetail);
		addMessage(redirectAttributes, "删除积分详情成功");
		return "redirect:"+Global.getAdminPath()+"/core/pvdetail/pvDetail/?repage";
	}

}