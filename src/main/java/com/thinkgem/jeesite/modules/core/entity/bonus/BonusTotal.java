/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.entity.bonus;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;

/**
 * 业绩Entity
 * @author 李延明
 * @version 2018-11-01
 */
public class BonusTotal extends DataEntity<BonusTotal> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 会员编号
	private BigDecimal apvTotal;		// A区累计
	private BigDecimal bpvTotal;		// B区累计
	private BigDecimal apv;		// A区当前
	private BigDecimal bpv;		// B区当前
	private BigDecimal bonusTotal;		// 奖金累计
	private BigDecimal bonusCurrent;		// 奖金剩余
	private BigDecimal jinhuopv;		// 店铺进货一盒12.5积分，积分累计
    private BigDecimal moneyTotal;		// 充值激活等累计
    private BigDecimal moneyCurrent;		// 充值激活等剩余
	
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

    public BigDecimal getApvTotal() {
        return apvTotal;
    }

    public void setApvTotal(BigDecimal apvTotal) {
        this.apvTotal = apvTotal;
    }

    public BigDecimal getBpvTotal() {
        return bpvTotal;
    }

    public void setBpvTotal(BigDecimal bpvTotal) {
        this.bpvTotal = bpvTotal;
    }

    public BigDecimal getApv() {
        return apv;
    }

    public void setApv(BigDecimal apv) {
        this.apv = apv;
    }

    public BigDecimal getBpv() {
        return bpv;
    }

    public void setBpv(BigDecimal bpv) {
        this.bpv = bpv;
    }

    public BigDecimal getBonusTotal() {
        return bonusTotal;
    }

    public void setBonusTotal(BigDecimal bonusTotal) {
        this.bonusTotal = bonusTotal;
    }

    public BigDecimal getBonusCurrent() {
        return bonusCurrent;
    }

    public void setBonusCurrent(BigDecimal bonusCurrent) {
        this.bonusCurrent = bonusCurrent;
    }

    public BigDecimal getJinhuopv() {
        return jinhuopv;
    }

    public void setJinhuopv(BigDecimal jinhuopv) {
        this.jinhuopv = jinhuopv;
    }

    public BigDecimal getMoneyTotal() {
        return moneyTotal;
    }

    public void setMoneyTotal(BigDecimal moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public BigDecimal getMoneyCurrent() {
        return moneyCurrent;
    }

    public void setMoneyCurrent(BigDecimal moneyCurrent) {
        this.moneyCurrent = moneyCurrent;
    }
}