/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.entity.pv;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;

/**
 * 积分汇总Entity
 * @author 李延明
 * @version 2018-11-01
 */
public class PvTotal extends DataEntity<PvTotal> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 会员编号
	private BigDecimal zhitui;		// 直推奖
	private BigDecimal hezuo;		// 合作奖
	private BigDecimal guanli;		// 管理奖
	private BigDecimal baodan;		// 报单奖
	private BigDecimal kaifa;		// 开发代理店铺奖
	private BigDecimal jinhuo;		// 店铺进货一盒12.5积分
	private BigDecimal total;		// 扣税后奖金总额
    private String fromMember;      //积分来源
	
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

    @Length(min=1, max=100, message="积分来源长度必须介于 1 和 100 之间")
    public String getFromMember() {
        return fromMember;
    }

    public void setFromMember(String fromMember) {
        this.fromMember = fromMember;
    }

    public BigDecimal getZhitui() {
        return zhitui;
    }

    public void setZhitui(BigDecimal zhitui) {
        this.zhitui = zhitui;
    }

    public BigDecimal getHezuo() {
        return hezuo;
    }

    public void setHezuo(BigDecimal hezuo) {
        this.hezuo = hezuo;
    }

    public BigDecimal getGuanli() {
        return guanli;
    }

    public void setGuanli(BigDecimal guanli) {
        this.guanli = guanli;
    }

    public BigDecimal getBaodan() {
        return baodan;
    }

    public void setBaodan(BigDecimal baodan) {
        this.baodan = baodan;
    }

    public BigDecimal getKaifa() {
        return kaifa;
    }

    public void setKaifa(BigDecimal kaifa) {
        this.kaifa = kaifa;
    }

    public BigDecimal getJinhuo() {
        return jinhuo;
    }

    public void setJinhuo(BigDecimal jinhuo) {
        this.jinhuo = jinhuo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}