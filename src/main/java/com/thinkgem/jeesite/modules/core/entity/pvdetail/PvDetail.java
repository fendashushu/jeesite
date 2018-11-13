/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.entity.pvdetail;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;

/**
 * 积分详情Entity
 * @author li
 * @version 2018-11-02
 */
public class PvDetail extends DataEntity<PvDetail> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 会员编号
	private String fromName;		// 积分来源关联会员，产生管理奖的会员
	private String zhuceName;		// 注册会员，产生合作奖，直推奖的会员
	private BigDecimal pvTotal;		// 总积分
	private BigDecimal pvSheng;		// 扣税剩余积分
	private BigDecimal pvDues;		// 扣税5%积分
	private String pvtype;		// 奖项类型
	private String note;		// 来源说明

    private BigDecimal pvTotal1;
    private BigDecimal pvSheng1;
    private BigDecimal pvDues1;

    private BigDecimal pvTotal2;
    private BigDecimal pvSheng2;
    private BigDecimal pvDues2;

    private BigDecimal pvTotal3;
    private BigDecimal pvSheng3;
    private BigDecimal pvDues3;
    private BigDecimal pvZong; //可提现和

    private String pvDate;
	
	public PvDetail() {
		super();
	}

	public PvDetail(String id){
		super(id);
	}

	@Length(min=1, max=100, message="会员编号长度必须介于 1 和 100 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=1, max=100, message="积分来源关联会员，产生管理奖的会员长度必须介于 1 和 100 之间")
	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	
	@Length(min=1, max=100, message="注册会员，产生合作奖，直推奖的会员长度必须介于 1 和 100 之间")
	public String getZhuceName() {
		return zhuceName;
	}

    public void setZhuceName(String zhuceName) {
        this.zhuceName = zhuceName;
    }

    public BigDecimal getPvTotal() {
        return pvTotal;
    }

    public void setPvTotal(BigDecimal pvTotal) {
        this.pvTotal = pvTotal;
    }

    public BigDecimal getPvSheng() {
        return pvSheng;
    }

    public void setPvSheng(BigDecimal pvSheng) {
        this.pvSheng = pvSheng;
    }

    public BigDecimal getPvDues() {
        return pvDues;
    }

    public void setPvDues(BigDecimal pvDues) {
        this.pvDues = pvDues;
    }

    @Length(min=1, max=2, message="奖项类型长度必须介于 1 和 2 之间")
	public String getPvtype() {
		return pvtype;
	}

	public void setPvtype(String pvtype) {
		this.pvtype = pvtype;
	}
	
	@Length(min=0, max=200, message="来源说明长度必须介于 0 和 200 之间")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

    public BigDecimal getPvTotal1() {
        return pvTotal1;
    }

    public void setPvTotal1(BigDecimal pvTotal1) {
        this.pvTotal1 = pvTotal1;
    }

    public BigDecimal getPvSheng1() {
        return pvSheng1;
    }

    public void setPvSheng1(BigDecimal pvSheng1) {
        this.pvSheng1 = pvSheng1;
    }

    public BigDecimal getPvDues1() {
        return pvDues1;
    }

    public void setPvDues1(BigDecimal pvDues1) {
        this.pvDues1 = pvDues1;
    }

    public BigDecimal getPvTotal2() {
        return pvTotal2;
    }

    public void setPvTotal2(BigDecimal pvTotal2) {
        this.pvTotal2 = pvTotal2;
    }

    public BigDecimal getPvSheng2() {
        return pvSheng2;
    }

    public void setPvSheng2(BigDecimal pvSheng2) {
        this.pvSheng2 = pvSheng2;
    }

    public BigDecimal getPvDues2() {
        return pvDues2;
    }

    public void setPvDues2(BigDecimal pvDues2) {
        this.pvDues2 = pvDues2;
    }

    public BigDecimal getPvTotal3() {
        return pvTotal3;
    }

    public void setPvTotal3(BigDecimal pvTotal3) {
        this.pvTotal3 = pvTotal3;
    }

    public BigDecimal getPvSheng3() {
        return pvSheng3;
    }

    public void setPvSheng3(BigDecimal pvSheng3) {
        this.pvSheng3 = pvSheng3;
    }

    public BigDecimal getPvDues3() {
        return pvDues3;
    }

    public void setPvDues3(BigDecimal pvDues3) {
        this.pvDues3 = pvDues3;
    }

    public String getPvDate() {
        return pvDate;
    }

    public void setPvDate(String pvDate) {
        this.pvDate = pvDate;
    }

    public BigDecimal getPvZong() {
        return pvZong;
    }

    public void setPvZong(BigDecimal pvZong) {
        this.pvZong = pvZong;
    }
}