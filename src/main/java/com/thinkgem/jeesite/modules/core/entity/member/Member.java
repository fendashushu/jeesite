/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.entity.member;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;

/**
 * 会员Entity
 * @author 李延明
 * @version 2018-10-30
 */
public class Member extends DataEntity<Member> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 用户id
	private String store;		// 店铺/服务中心id
	private String referee;		// 推荐人id
	private String contact;		// 节点人id
	private String area;		// 安置区域A/B
	private String memberlevel;		// 加盟资格；四个等级
	private String bank;		// 开户银行
	private String bankno;		// 银行账号
	private String bankname;		// 开户人姓名
	private String idcard;		// 身份证号
	private String sex;		// 性别；1：男；2：女
	private String address;		// 地址
	private String qq;		// QQ号
	private String isstore;		// 是否服务中心；0：否；1：是
	private String activate;		// 是否激活；0：否；1：是
    private String loginName;
	private String name;
	private String status;
	private String referees;
	private BigDecimal apv;
	private BigDecimal bpv;
	private BigDecimal apvTotal;
	private BigDecimal bpvTotal;

	public Member() {
		super();
	}

	public Member(String id){
		super(id);
	}

	@Length(min=1, max=64, message="用户id长度必须介于 1 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=1, max=64, message="店铺/服务中心id长度必须介于 1 和 64 之间")
	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}
	
	@Length(min=1, max=64, message="推荐人id长度必须介于 1 和 64 之间")
	public String getReferee() {
		return referee;
	}

	public void setReferee(String referee) {
		this.referee = referee;
	}
	
	@Length(min=1, max=64, message="节点人id长度必须介于 1 和 64 之间")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Length(min=1, max=5, message="安置区域A/B长度必须介于 1 和 5 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

    @Length(min=1, max=5000, message="安置区域A/B长度必须介于 1 和 5000 之间")
    public String getReferees() {
        return referees;
    }

    public void setReferees(String referees) {
        this.referees = referees;
    }

    @Length(min=1, max=5, message="加盟资格；四个等级长度必须介于 1 和 5 之间")
	public String getMemberlevel() {
		return memberlevel;
	}

	public void setMemberlevel(String memberlevel) {
		this.memberlevel = memberlevel;
	}
	
	@Length(min=1, max=100, message="开户银行长度必须介于 1 和 100 之间")
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}
	
	@Length(min=1, max=50, message="银行账号长度必须介于 1 和 50 之间")
	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}
	
	@Length(min=1, max=50, message="开户人姓名长度必须介于 1 和 50 之间")
	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	
	@Length(min=0, max=20, message="身份证号长度必须介于 0 和 20 之间")
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	@Length(min=0, max=2, message="性别；1：男；2：女长度必须介于 0 和 2 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=500, message="地址长度必须介于 0 和 500 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=15, message="QQ号长度必须介于 0 和 15 之间")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Length(min=1, max=2, message="是否服务中心；0：否；1：是长度必须介于 1 和 2 之间")
	public String getIsstore() {
		return isstore;
	}

	public void setIsstore(String isstore) {
		this.isstore = isstore;
	}
	
	@Length(min=1, max=2, message="是否激活；0：否；1：是长度必须介于 1 和 2 之间")
	public String getActivate() {
		return activate;
	}

    @Length(min=1, max=100, message="登录名长度必须介于 1 和 100 之间")
    @ExcelField(title="登录名", align=2, sort=30)
    public String getLoginName() {
        return loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

	public void setActivate(String activate) {
		this.activate = activate;
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
}