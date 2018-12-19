/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.web.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.core.entity.bonus.BonusTotal;
import com.thinkgem.jeesite.modules.core.entity.setting.MemberSetting;
import com.thinkgem.jeesite.modules.core.service.bonus.BonusTotalService;
import com.thinkgem.jeesite.modules.core.service.setting.MemberSettingService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
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
import com.thinkgem.jeesite.modules.core.entity.member.Member;
import com.thinkgem.jeesite.modules.core.service.member.MemberService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
	@Autowired
    private MemberSettingService memberSettingService;
    @Autowired
    private SystemService systemService;

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

    /**
     * 服务中心查看正式会员
     * @param member
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("core:member:member:view")
	@RequestMapping(value = {"realMember"})
	public String realMember(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
	    User user = UserUtils.getUser();
	    member.setStore(user.getLoginName());
		Page<Member> page = memberService.getRealMember(new Page<Member>(request, response), member);
		model.addAttribute("page", page);
		return "modules/core/member/memberReal";
	}

    /**
     * 管理员查看所有正式会员
     * @param member
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("core:member:member:view")
	@RequestMapping(value = {"realMemberManager"})
	public String realMemberManager(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
        String name = request.getParameter("name");
        if(name != null && !"".equals(name)){
            member.setName(name);
            model.addAttribute("my",true);
            model.addAttribute("name",name);
        }else{
            model.addAttribute("my",false);
        }
		Page<Member> page = memberService.realMemberManager(new Page<Member>(request, response), member);
		model.addAttribute("page", page);
		return "modules/core/member/memberRealManage";
	}


    /**
     * 管理员查看所有服务中心
     * @param member
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("core:member:member:view")
	@RequestMapping(value = {"storeManager"})
	public String storeManager(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Member> page = memberService.storeManage(new Page<Member>(request, response), member);
		model.addAttribute("page", page);
		return "modules/core/member/storeManage";
	}


    /**
     * 管理员查看所有服务中心
     * @param member
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("core:member:member:view")
	@RequestMapping(value = {"baodan"})
	public String baodan(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Member> page = memberService.baodan(new Page<Member>(request, response), member);
		model.addAttribute("page", page);
		model.addAttribute("baodan", member.getBaodan());
		return "modules/core/member/memberBaodan";
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
	@RequestMapping(value = {"acitvityUp"})
	public String acitvityUp(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
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
		Page<Member> page = memberService.getUpMember(new Page<Member>(request, response), member);
		model.addAttribute("page", page);
		model.addAttribute("msg", msg);
		return "modules/core/member/memberActivityUp";
	}

    @RequiresPermissions("core:member:member:edit")
    @RequestMapping(value = {"activityUping"})
    @ResponseBody
    public Map activityUping(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map map = new HashMap();
        String loginName = request.getParameter("loginName");
        String level = request.getParameter("level");
        Member member = memberService.getMemberByLoginName(loginName);
        String memberlevel = member.getMemberlevel();
        MemberSetting memberSetting = memberSettingService.get("1");
        Integer oneToTwo = memberSetting.getOneToTwo();
        Integer oneToThree = memberSetting.getOneToThree();
        Integer twoToThree = memberSetting.getTwoToThree();
        BigDecimal need = BigDecimal.ZERO;//升级需要的报单币
        if("0".equals(memberlevel)){
            map.put("result",false);
            map.put("msg","会员不能参与此活动！");
            return map;
        }else if("1".equals(memberlevel)){
            if("2".equals(level)){
                need = BigDecimal.valueOf(oneToTwo);
            }else if("3".equals(level)){
                need = BigDecimal.valueOf(oneToThree);
            }
        }else if("2".equals(memberlevel)){
            need = BigDecimal.valueOf(twoToThree);
        }
        User user = UserUtils.getUser();
        BonusTotal bonusTotal = bonusTotalService.getBonusByLoginName(user.getLoginName());
        BigDecimal bonusCurrent = bonusTotal.getBonusCurrent();
        try {
            if(need.compareTo(bonusCurrent)<=0){
                member.setMemberlevel(level);
                bonusTotal.setBonusCurrent(bonusCurrent.subtract(need));
                memberService.updateMember(member,bonusTotal,null,null,null,null);
                map.put("result",true);
                map.put("msg","升级成功！");
            }else {
                map.put("result",false);
                map.put("msg","金额不足请充值！");
            }
        }catch (Exception e){
            map.put("result",false);
            map.put("msg","升级失败！");
        }
        return map;
    }

	@RequiresPermissions("core:member:member:view")
	@RequestMapping(value = {"up"})
	public String up(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
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
		Page<Member> page = memberService.getUpMember(new Page<Member>(request, response), member);
		model.addAttribute("page", page);
		model.addAttribute("msg", msg);
		return "modules/core/member/memberUp";
	}


    @RequiresPermissions("core:member:member:edit")
    @RequestMapping(value = {"uping"})
    @ResponseBody
    public Map uping(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map map = new HashMap();
        String loginName = request.getParameter("loginName");
        String level = request.getParameter("level");
        Member member = memberService.getMemberByLoginName(loginName);
        String memberlevel = member.getMemberlevel();
        MemberSetting memberSetting = memberSettingService.get("1");
        Integer jion0 = memberSetting.getJion0();
        Integer jion1 = memberSetting.getJion1();
        Integer jion2 = memberSetting.getJion2();
        Integer jion3 = memberSetting.getJion3();
        BigDecimal need = BigDecimal.ZERO;//激活需要的报单币
        if("0".equals(memberlevel)){
            if("1".equals(level)){
                need = BigDecimal.valueOf(jion1).subtract(BigDecimal.valueOf(jion0));
            }else if("2".equals(level)){
                need = BigDecimal.valueOf(jion2).subtract(BigDecimal.valueOf(jion0));
            }else if("3".equals(level)){
                need = BigDecimal.valueOf(jion3).subtract(BigDecimal.valueOf(jion0));
            }
        }else if("1".equals(memberlevel)){
            if("2".equals(level)){
                need = BigDecimal.valueOf(jion2).subtract(BigDecimal.valueOf(jion1));
            }else if("3".equals(level)){
                need = BigDecimal.valueOf(jion3).subtract(BigDecimal.valueOf(jion1));
            }
        }else if("2".equals(memberlevel)){
            need = BigDecimal.valueOf(jion3).subtract(BigDecimal.valueOf(jion2));
        }
        User user = UserUtils.getUser();
        BonusTotal bonusTotal = bonusTotalService.getBonusByLoginName(user.getLoginName());
        BigDecimal bonusCurrent = bonusTotal.getBonusCurrent();
        try {
            if(need.compareTo(bonusCurrent)<=0){
                member.setMemberlevel(level);
                bonusTotal.setBonusCurrent(bonusCurrent.subtract(need));
                memberService.updateMember(member,bonusTotal,null,memberSetting,memberlevel,level);
                map.put("result",true);
                map.put("msg","升级成功！");
            }else {
                map.put("result",false);
                map.put("msg","金额不足请充值！");
            }
        }catch (Exception e){
            map.put("result",false);
            map.put("msg","升级失败！");
        }
        return map;
    }

	@RequiresPermissions("core:member:member:view")
	@RequestMapping(value = {"store"})
	public String store(Member member, HttpServletRequest request, HttpServletResponse response, Model model) {
	    User user = UserUtils.getUser();
        BonusTotal bonusTotal = bonusTotalService.getBonusByLoginName(user.getLoginName());
	    member = memberService.getMemberByLoginName(user.getLoginName());
	    model.addAttribute("member",member);
	    model.addAttribute("bonusTotal",bonusTotal);
		return "modules/core/member/memberStore";
	}

    @RequiresPermissions("core:member:member:edit")
    @RequestMapping(value = "storing")
    public String storing(Member member, Model model, RedirectAttributes redirectAttributes) {
        try {
            BigDecimal money = new BigDecimal(8400);
            BonusTotal bonusTotal = bonusTotalService.getBonusByLoginName(member.getLoginName());
            BigDecimal bonusCurrent = bonusTotal.getBonusCurrent();
            if(money.compareTo(bonusCurrent)>0){
                addMessage(redirectAttributes, "申请服务中心失败，当前可用积分为"+bonusCurrent+"，请先充值");
            }else{
                member = memberService.getMemberByLoginName(member.getLoginName());
                member.setIsstore("1");
                member.setStoreDate(new Date());
                bonusTotal.setBonusTotal(bonusTotal.getBonusTotal().add(new BigDecimal("150")));
                bonusTotal.setBonusCurrent(bonusCurrent.add(new BigDecimal("150")));
                bonusTotal.setBonusCurrent(bonusCurrent.subtract(money));
                User user = UserUtils.getByLoginName(member.getLoginName());
                memberService.updateMember(member,bonusTotal,user,null,null,null);
                addMessage(redirectAttributes, "申请服务中心成功");
            }
        }catch (Exception e){
            addMessage(redirectAttributes, "申请服务中心失败");
        }
        return "redirect:"+Global.getAdminPath()+"/core/member/member/store?repage";
    }

	@RequiresPermissions("core:member:member:edit")
	@RequestMapping(value = {"activating"})
    @ResponseBody
	public Map activating(HttpServletRequest request, HttpServletResponse response, Model model) {
	    Map map = new HashMap();
        String loginName = request.getParameter("loginName");
        Member member = memberService.getMemberByLoginName(loginName);
        String level = member.getMemberlevel();
        MemberSetting memberSetting = memberSettingService.get("1");
        Integer jion0 = memberSetting.getJion0();
        Integer jion1 = memberSetting.getJion1();
        Integer jion2 = memberSetting.getJion2();
        Integer jion3 = memberSetting.getJion3();
        BigDecimal need = BigDecimal.ZERO;//激活需要的报单币
        if("0".equals(level)){
            need = BigDecimal.valueOf(jion0);
        }else if("1".equals(level)){
            need = BigDecimal.valueOf(jion1);
        }else if("2".equals(level)){
            need = BigDecimal.valueOf(jion2);
        }else {
            need = BigDecimal.valueOf(jion3);
        }
        User user = UserUtils.getUser();
        BonusTotal bonusTotal = bonusTotalService.getBonusByLoginName(user.getLoginName());
        BigDecimal bonusCurrent = bonusTotal.getBonusCurrent();
        try{
            if(need.compareTo(bonusCurrent)<=0){
                member.setActivate("1");
                member.setActivateDate(new Date());
                bonusTotal.setBonusCurrent(bonusCurrent.subtract(need));
                memberService.updateMember(member,bonusTotal,null,memberSetting,null,null);
                map.put("result",true);
                map.put("msg","激活成功！");
            }else {
                map.put("result",false);
                map.put("msg","金额不足请充值！");
            }
        }catch (Exception e){
            map.put("result",false);
            map.put("msg","激活失败！");
        }
        return map;
	}

    /**
     * 锁定解锁
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("core:member:member:edit")
	@RequestMapping(value = {"lockOrUnlock"})
    @ResponseBody
	public Map lockOrUnlock(HttpServletRequest request, HttpServletResponse response, Model model) {
	    Map map = new HashMap();
	    String msg = "锁定";
        String loginName = request.getParameter("loginName");
        String status = request.getParameter("status");
        User user = UserUtils.getByLoginName(loginName);
        if("0".equals(status)){
            msg = "解锁";
        }
        try {
            if (user != null){
                if("1".equals(status)){
                    user.setStatus(0);
                }else{
                    user.setStatus(1);
                }
                memberService.lockOrUnlock(user);
                map.put("result",true);
                map.put("msg",msg+"成功！");
            }else{
                map.put("result",false);
                map.put("msg",msg+"失败，会员不存在！");
            }
        }catch (Exception e){
            map.put("result",false);
            map.put("msg",msg+"失败！");
        }
		return map;
	}


    /**
     * 重置密码
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("core:member:member:edit")
	@RequestMapping(value = {"resetPassword"})
    @ResponseBody
	public Map resetPassword(HttpServletRequest request, HttpServletResponse response, Model model) {
	    Map map = new HashMap();
        String loginName = request.getParameter("loginName");
        User user = UserUtils.getByLoginName(loginName);
        try {
            if (user != null){
                user.setPassword(SystemService.entryptPassword("123456"));
                systemService.saveUser(user);
                map.put("result",true);
                map.put("msg","密码重置成功！");
            }else{
                map.put("result",false);
                map.put("msg","密码重置失败，会员不存在！");
            }
        }catch (Exception e){
            map.put("result",false);
            map.put("msg","密码重置失败！");
        }
		return map;
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