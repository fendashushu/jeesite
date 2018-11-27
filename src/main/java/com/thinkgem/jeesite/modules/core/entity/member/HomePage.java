package com.thinkgem.jeesite.modules.core.entity.member;

import java.math.BigDecimal;

public class HomePage {

    private Integer dayMembers;//日新增会员
    private Integer monthMembers;//月新增会员
    private Integer yearMembers;//年新增会员
    private Integer needActivateMembers;//待激活会员
    private Integer activateMembers;//已激活会员
    private Integer needRecharge;//待处理充值
    private Integer needTransfer;//待处理转账
    private Integer needOrders;//待处理订单

    private BigDecimal dayBonus;//日新增收入
    private BigDecimal monthBonus;//月新增收入
    private BigDecimal yearBonus;//你那新增收入

    public Integer getDayMembers() {
        return dayMembers;
    }

    public void setDayMembers(Integer dayMembers) {
        this.dayMembers = dayMembers;
    }

    public Integer getMonthMembers() {
        return monthMembers;
    }

    public void setMonthMembers(Integer monthMembers) {
        this.monthMembers = monthMembers;
    }

    public Integer getYearMembers() {
        return yearMembers;
    }

    public void setYearMembers(Integer yearMembers) {
        this.yearMembers = yearMembers;
    }

    public Integer getNeedActivateMembers() {
        return needActivateMembers;
    }

    public void setNeedActivateMembers(Integer needActivateMembers) {
        this.needActivateMembers = needActivateMembers;
    }

    public Integer getActivateMembers() {
        return activateMembers;
    }

    public void setActivateMembers(Integer activateMembers) {
        this.activateMembers = activateMembers;
    }

    public Integer getNeedRecharge() {
        return needRecharge;
    }

    public void setNeedRecharge(Integer needRecharge) {
        this.needRecharge = needRecharge;
    }

    public Integer getNeedTransfer() {
        return needTransfer;
    }

    public void setNeedTransfer(Integer needTransfer) {
        this.needTransfer = needTransfer;
    }

    public Integer getNeedOrders() {
        return needOrders;
    }

    public void setNeedOrders(Integer needOrders) {
        this.needOrders = needOrders;
    }

    public BigDecimal getDayBonus() {
        return dayBonus;
    }

    public void setDayBonus(BigDecimal dayBonus) {
        this.dayBonus = dayBonus;
    }

    public BigDecimal getMonthBonus() {
        return monthBonus;
    }

    public void setMonthBonus(BigDecimal monthBonus) {
        this.monthBonus = monthBonus;
    }

    public BigDecimal getYearBonus() {
        return yearBonus;
    }

    public void setYearBonus(BigDecimal yearBonus) {
        this.yearBonus = yearBonus;
    }
}
