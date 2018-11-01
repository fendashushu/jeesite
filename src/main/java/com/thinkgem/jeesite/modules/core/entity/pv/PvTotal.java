/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.entity.pv;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 积分汇总Entity
 * @author 李延明
 * @version 2018-11-01
 */
public class PvTotal extends DataEntity<PvTotal> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 会员编号
	private String zhitui;		// 直推奖
	private String hezuo;		// 合作奖
	private String guanli;		// 管理奖
	private String baodan;		// 报单奖
	private String kaifa;		// 开发代理店铺奖
	private String jinhuo;		// 店铺进货一盒12.5积分
	private String total;		// 扣税后奖金总额
	
	public PvTotal() {
		super();
	}

	public PvTotal(String id){
		super(id);
	}

	@Length(min=1, max=100, message="会员编号长度必须介于 1 和 100 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getZhitui() {
		return zhitui;
	}

	public void setZhitui(String zhitui) {
		this.zhitui = zhitui;
	}
	
	public String getHezuo() {
		return hezuo;
	}

	public void setHezuo(String hezuo) {
		this.hezuo = hezuo;
	}
	
	public String getGuanli() {
		return guanli;
	}

	public void setGuanli(String guanli) {
		this.guanli = guanli;
	}
	
	public String getBaodan() {
		return baodan;
	}

	public void setBaodan(String baodan) {
		this.baodan = baodan;
	}
	
	public String getKaifa() {
		return kaifa;
	}

	public void setKaifa(String kaifa) {
		this.kaifa = kaifa;
	}
	
	public String getJinhuo() {
		return jinhuo;
	}

	public void setJinhuo(String jinhuo) {
		this.jinhuo = jinhuo;
	}
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
}