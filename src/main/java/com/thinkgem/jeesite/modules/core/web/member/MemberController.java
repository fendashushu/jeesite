/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.web.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.core.entity.bonus.BonusTotal;
import com.thinkgem.jeesite.modules.core.service.bonus.BonusTotalService;
import com.thinkgem.jeesite.modules.sys.entity.User;
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
import com.thinkgem.jeesite.modules.core.entity.member.Member;
import com.thinkgem.jeesite.modules.core.service.member.MemberService;

import java.math.BigDecimal;

/**
 * 会员Controller
 * @author 李延明
 * @version 2018-10-30
 */
@Controller
@RequestMapping(value = "${adminPath}/core/member/member")
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private BonusTotalService bonusTotalService;

	@ModelAttribute
	public Member get(@RequestParam(required=false) String id) {
		Member entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = memberService.get(id);
		}
		if (entity == null){
			entity = new Member();
		}
		return entity;
	}
	
	@RequiresPermissions("core:member:member:view")
	@RequestMapping(value = {"list", ""})
	public String list(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Member> page = memberService.findPage(new Page<Member>(request, response), member); 
		model.addAttribute("page", page);
		return "modules/core/member/memberList";
	}

	@RequiresPermissions("core:member:member:view")
	@RequestMapping(value = {"realMember"})
	public String realMember(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
	    User user = UserUtils.getUser();
	    member.setStore(user.getLoginName());
		Page<Member> page = memberService.getRealMember(new Page<Member>(request, response), member);
		model.addAttribute("page", page);
		return "modules/core/member/memberReal";
	}

	@RequiresPermissions("core:member:member:view")
	@RequestMapping(value = {"activate"})
	public String activate(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
	    User user = UserUtils.getUser();
	    member.setStore(user.getLoginName());
		BonusTotal bonusTotal = bonusTotalService.getBonusByLoginName(user.getLoginName());
		String msg = "可使用金额：";
		if(bonusTotal == null){
			msg += "0";
		}else {
			BigDecimal current = bonusTotal.getBonusCurrent();
			msg += current;
		}
		Page<Member> page = memberService.getActivateMember(new Page<Member>(request, response), member);
		model.addAttribute("page", page);
		model.addAttribute("msg", msg);
		return "modules/core/member/memberActivate";
	}

	@RequiresPermissions("core:member:member:view")
	@RequestMapping(value = "form")
	public String form(Member member, Model model) {
		model.addAttribute("member", member);
		return "modules/core/member/memberForm";
	}

	@RequiresPermissions("core:member:member:edit")
	@RequestMapping(value = "save")
	public String save(Member member, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, member)){
			return form(member, model);
		}
		memberService.save(member);
		addMessage(redirectAttributes, "保存会员成功");
		return "redirect:"+Global.getAdminPath()+"/core/member/member/?repage";
	}
	
	@RequiresPermissions("core:member:member:edit")
	@RequestMapping(value = "delete")
	public String delete(Member member, RedirectAttributes redirectAttributes) {
		memberService.delete(member);
		addMessage(redirectAttributes, "删除会员成功");
		return "redirect:"+Global.getAdminPath()+"/core/member/member/?repage";
	}

}