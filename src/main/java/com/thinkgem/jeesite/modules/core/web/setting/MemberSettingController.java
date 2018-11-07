/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.web.setting;

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
import com.thinkgem.jeesite.modules.core.entity.setting.MemberSetting;
import com.thinkgem.jeesite.modules.core.service.setting.MemberSettingService;

/**
 * 会员设置Controller
 * @author 李延明
 * @version 2018-11-01
 */
@Controller
@RequestMapping(value = "${adminPath}/core/setting/memberSetting")
public class MemberSettingController extends BaseController {

	@Autowired
	private MemberSettingService memberSettingService;
	
	@ModelAttribute
	public MemberSetting get(@RequestParam(required=false) String id) {
		MemberSetting entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberSettingService.get(id);
		}
		if (entity == null){
			entity = new MemberSetting();
		}
		return entity;
	}
	
	@RequiresPermissions("core:setting:memberSetting:view")
	@RequestMapping(value = {"list", ""})
	public String list(MemberSetting memberSetting, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MemberSetting> page = memberSettingService.findPage(new Page<MemberSetting>(request, response), memberSetting); 
		model.addAttribute("page", page);
		return "modules/core/setting/memberSettingList";
	}

	@RequiresPermissions("core:setting:memberSetting:view")
	@RequestMapping(value = "form")
	public String form(MemberSetting memberSetting, Model model) {
	    memberSetting = memberSettingService.get("1");
		model.addAttribute("memberSetting", memberSetting);
		return "modules/core/setting/memberSettingForm";
	}

	@RequiresPermissions("core:setting:memberSetting:edit")
	@RequestMapping(value = "save")
	public String save(MemberSetting memberSetting, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, memberSetting)){
			return form(memberSetting, model);
		}
		try {
            memberSettingService.save(memberSetting);
            addMessage(redirectAttributes, "保存成功");
        }catch (Exception e){
            addMessage(redirectAttributes, "保存失败，请检查参数");
        }

		return "redirect:"+Global.getAdminPath()+"/core/setting/memberSetting/form?repage";
	}
	
	@RequiresPermissions("core:setting:memberSetting:edit")
	@RequestMapping(value = "delete")
	public String delete(MemberSetting memberSetting, RedirectAttributes redirectAttributes) {
		memberSettingService.delete(memberSetting);
		addMessage(redirectAttributes, "删除会员设置成功");
		return "redirect:"+Global.getAdminPath()+"/core/setting/memberSetting/?repage";
	}

}