/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.entity.recharge;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 充值Entity
 * @author li
 * @version 2018-11-05
 */
public class PvRecharge extends DataEntity<PvRecharge> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 会员编号
	private String name;		// 会员
	private String amount;		// 金额
	private String amountType;		// 货币类型
	private String status;		// 发放状态;0:未发放；1：已发放
	private String reateBy;		// 创建者
	
	public PvRecharge() {
		super();
	}

	public PvRecharge(String id){
		super(id);
	}

	@Length(min=1, max=100, message="会员编号长度必须介于 1 和 100 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=1, max=100, message="会员长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=2, message="货币类型长度必须介于 0 和 2 之间")
	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}
	
	@Length(min=0, max=2, message="发放状态;0:未发放；1：已发放长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=64, message="创建者长度必须介于 1 和 64 之间")
	public String getReateBy() {
		return reateBy;
	}

	public void setReateBy(String reateBy) {
		this.reateBy = reateBy;
	}
	
}