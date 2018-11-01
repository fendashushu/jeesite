/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.entity.bouns;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 业绩Entity
 * @author 李延明
 * @version 2018-11-01
 */
public class BonusTotal extends DataEntity<BonusTotal> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 会员编号
	private String apvTotal;		// A区累计
	private String bpvTotal;		// B区累计
	private String apv;		// A区当前
	private String bpv;		// B区当前
	private String bonusTotal;		// 奖金累计
	private String bonusCurrent;		// 奖金剩余
	private String jinhuopv;		// 店铺进货一盒12.5积分，积分累计
	
	public BonusTotal() {
		super();
	}

	public BonusTotal(String id){
		super(id);
	}

	@Length(min=1, max=100, message="会员编号长度必须介于 1 和 100 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getApvTotal() {
		return apvTotal;
	}

	public void setApvTotal(String apvTotal) {
		this.apvTotal = apvTotal;
	}
	
	public String getBpvTotal() {
		return bpvTotal;
	}

	public void setBpvTotal(String bpvTotal) {
		this.bpvTotal = bpvTotal;
	}
	
	public String getApv() {
		return apv;
	}

	public void setApv(String apv) {
		this.apv = apv;
	}
	
	public String getBpv() {
		return bpv;
	}

	public void setBpv(String bpv) {
		this.bpv = bpv;
	}
	
	public String getBonusTotal() {
		return bonusTotal;
	}

	public void setBonusTotal(String bonusTotal) {
		this.bonusTotal = bonusTotal;
	}
	
	public String getBonusCurrent() {
		return bonusCurrent;
	}

	public void setBonusCurrent(String bonusCurrent) {
		this.bonusCurrent = bonusCurrent;
	}
	
	public String getJinhuopv() {
		return jinhuopv;
	}

	public void setJinhuopv(String jinhuopv) {
		this.jinhuopv = jinhuopv;
	}
	
}