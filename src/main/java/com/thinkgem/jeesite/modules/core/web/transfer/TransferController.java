/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.web.transfer;

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
import com.thinkgem.jeesite.modules.core.entity.transfer.Transfer;
import com.thinkgem.jeesite.modules.core.service.transfer.TransferService;

/**
 * 积分转账Controller
 * @author li
 * @version 2018-11-06
 */
@Controller
@RequestMapping(value = "${adminPath}/core/transfer/transfer")
public class TransferController extends BaseController {

	@Autowired
	private TransferService transferService;
	
	@ModelAttribute
	public Transfer get(@RequestParam(required=false) String id) {
		Transfer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = transferService.get(id);
		}
		if (entity == null){
			entity = new Transfer();
		}
		return entity;
	}
	
	@RequiresPermissions("core:transfer:transfer:view")
	@RequestMapping(value = {"list", ""})
	public String list(Transfer transfer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Transfer> page = transferService.findPage(new Page<Transfer>(request, response), transfer); 
		model.addAttribute("page", page);
		return "modules/core/transfer/transferList";
	}

	@RequiresPermissions("core:transfer:transfer:view")
	@RequestMapping(value = "form")
	public String form(Transfer transfer, Model model) {
		model.addAttribute("transfer", transfer);
		return "modules/core/transfer/transferForm";
	}

	@RequiresPermissions("core:transfer:transfer:edit")
	@RequestMapping(value = "save")
	public String save(Transfer transfer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, transfer)){
			return form(transfer, model);
		}
		transferService.save(transfer);
		addMessage(redirectAttributes, "保存积分转账成功");
		return "redirect:"+Global.getAdminPath()+"/core/transfer/transfer/?repage";
	}
	
	@RequiresPermissions("core:transfer:transfer:edit")
	@RequestMapping(value = "delete")
	public String delete(Transfer transfer, RedirectAttributes redirectAttributes) {
		transferService.delete(transfer);
		addMessage(redirectAttributes, "删除积分转账成功");
		return "redirect:"+Global.getAdminPath()+"/core/transfer/transfer/?repage";
	}

}