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
	private BigDecimal pvtotal;		// 总积分
	private BigDecimal pvsheng;		// 扣税剩余积分
	private BigDecimal pvdues;		// 扣税5%积分
	private String pvtype;		// 奖项类型
	private String note;		// 来源说明
	
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

    public BigDecimal getPvtotal() {
        return pvtotal;
    }

    public void setPvtotal(BigDecimal pvtotal) {
        this.pvtotal = pvtotal;
    }

    public BigDecimal getPvsheng() {
        return pvsheng;
    }

    public void setPvsheng(BigDecimal pvsheng) {
        this.pvsheng = pvsheng;
    }

    public BigDecimal getPvdues() {
        return pvdues;
    }

    public void setPvdues(BigDecimal pvdues) {
        this.pvdues = pvdues;
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
	
}