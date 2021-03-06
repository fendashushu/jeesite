/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.web.recharge;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
import com.thinkgem.jeesite.modules.core.entity.recharge.PvRecharge;
import com.thinkgem.jeesite.modules.core.service.recharge.PvRechargeService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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
	    User user = UserUtils.getUser();
	    pvRecharge.setLoginName(user.getLoginName());
		Page<PvRecharge> page = pvRechargeService.findPage(new Page<PvRecharge>(request, response), pvRecharge); 
		model.addAttribute("page", page);
		return "modules/core/recharge/pvRechargeList";
	}

	@RequiresPermissions("core:recharge:pvRecharge:view")
	@RequestMapping(value = {"confirm"})
	public String confirm(PvRecharge pvRecharge, HttpServletRequest request, HttpServletResponse response, Model model) {
	    //User user = UserUtils.getUser();
	    //pvRecharge.setLoginName(user.getLoginName());
        String name = request.getParameter("name");
        String loginName = request.getParameter("loginName");
        pvRecharge.setLoginName(loginName);
        pvRecharge.setName(name);
		Page<PvRecharge> page = pvRechargeService.getConfirmRecharge(new Page<PvRecharge>(request, response), pvRecharge);
		model.addAttribute("page", page);
		return "modules/core/recharge/pvRechargeConfirm";
	}

	@RequiresPermissions("core:recharge:pvRecharge:edit")
	@RequestMapping(value = {"confirming"})
    @ResponseBody
	public Map confirming(PvRecharge pvRecharge, HttpServletRequest request, HttpServletResponse response, Model model) {
        Map map = new HashMap();
        String id = request.getParameter("id");
        String loginName = request.getParameter("loginName");
        String amount = request.getParameter("amount");
        pvRecharge.setLoginName(loginName);
        pvRecharge.setName(id);
        pvRecharge.setAmount(new BigDecimal(amount));
        pvRecharge.setStatus("1");
        try {
            pvRechargeService.confirmRecharge(pvRecharge);
            map.put("result",true);
            map.put("msg","确认成功！");
        }catch (Exception e){
            map.put("result",true);
            map.put("msg","确认失败！");
        }
		return map;
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
		User user = UserUtils.getUser();
		pvRecharge.setAmountType("1");
		pvRecharge.setLoginName(user.getLoginName());
		pvRecharge.setName(user.getName());
		pvRecharge.setStatus("0");
		pvRechargeService.save(pvRecharge);
		addMessage(redirectAttributes, "充值申请成功");
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