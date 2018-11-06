/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.entity.setting;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会员设置Entity
 * @author 李延明
 * @version 2018-11-01
 */
public class MemberSetting extends DataEntity<MemberSetting> {
	
	private static final long serialVersionUID = 1L;
	private String name0;		// 会员名称
	private String name1;		// 1级代理名称
	private String name2;		// 2级代理名称
	private String name3;		// 3级代理名称
	private Integer pv0;		// 会员奖金级别；0
	private Integer pv1;		// 1级代理奖金级别；1000
	private Integer pv2;		// 2级代理奖金级别；3000
	private Integer pv3;		// 3级代理奖金级别；6000
	private Integer jion0;		// 会员加盟资格；500
	private Integer jion1;		// 1级代理加盟资格；1200
	private Integer jion2;		// 2级代理加盟资格；3600
	private Integer jion3;		// 3级代理加盟资格；7200
	private Integer zhitui0;		// 会员直推奖；0
	private Integer zhitui1;		// 1级代理直推奖；6%
	private Integer zhitui2;		// 2级代理直推奖；10%
	private Integer zhitui3;		// 3级代理直推奖；12%
	private String hezuo0;		// 会员合作奖；0
	private Integer hezuo1;		// 1级代理合作奖；14%
	private Integer hezuo2;		// 2级代理合作奖；16%
	private Integer hezuo3;		// 3级代理合作奖；18%
	private Integer guanli0;		// 会员管理奖；0
	private Integer guanli1;		// 1级代理管理奖；6%
	private Integer guanli2;		// 2级代理管理奖；10%
	private Integer guanli3;		// 3级代理管理奖；12%
	private Integer tuidian0;		// 会员推店奖；0
	private Integer tuidian1;		// 1级代理推店奖；10%
	private Integer tuidian2;		// 2级代理推店奖；15%
	private Integer tuidian3;		// 3级代理推店奖；15%
	private Integer zhou0;		// 会员周封顶；0
	private Integer zhou1;		// 1级代理周封顶；30000
	private Integer zhou2;		// 2级代理周封顶；60000
	private Integer zhou3;		// 3级代理周封顶；80000
    private Integer baodan;//报单奖
	
	public MemberSetting() {
		super();
	}

	public MemberSetting(String id){
		super(id);
	}

	@Length(min=1, max=100, message="会员名称长度必须介于 1 和 100 之间")
	public String getName0() {
		return name0;
	}

	public void setName0(String name0) {
		this.name0 = name0;
	}
	
	@Length(min=1, max=100, message="1级代理名称长度必须介于 1 和 100 之间")
	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}
	
	@Length(min=1, max=100, message="2级代理名称长度必须介于 1 和 100 之间")
	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}
	
	@Length(min=1, max=100, message="3级代理名称长度必须介于 1 和 100 之间")
	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}
	
	@NotNull(message="会员奖金级别；0不能为空")
	public Integer getPv0() {
		return pv0;
	}

	public void setPv0(Integer pv0) {
		this.pv0 = pv0;
	}
	
	@NotNull(message="1级代理奖金级别；1000不能为空")
	public Integer getPv1() {
		return pv1;
	}

	public void setPv1(Integer pv1) {
		this.pv1 = pv1;
	}
	
	@NotNull(message="2级代理奖金级别；3000不能为空")
	public Integer getPv2() {
		return pv2;
	}

	public void setPv2(Integer pv2) {
		this.pv2 = pv2;
	}
	
	@NotNull(message="3级代理奖金级别；6000不能为空")
	public Integer getPv3() {
		return pv3;
	}

	public void setPv3(Integer pv3) {
		this.pv3 = pv3;
	}
	
	@NotNull(message="会员加盟资格；500不能为空")
	public Integer getJion0() {
		return jion0;
	}

	public void setJion0(Integer jion0) {
		this.jion0 = jion0;
	}
	
	@NotNull(message="1级代理加盟资格；1200不能为空")
	public Integer getJion1() {
		return jion1;
	}

	public void setJion1(Integer jion1) {
		this.jion1 = jion1;
	}
	
	@NotNull(message="2级代理加盟资格；3600不能为空")
	public Integer getJion2() {
		return jion2;
	}

	public void setJion2(Integer jion2) {
		this.jion2 = jion2;
	}
	
	@NotNull(message="3级代理加盟资格；7200不能为空")
	public Integer getJion3() {
		return jion3;
	}

	public void setJion3(Integer jion3) {
		this.jion3 = jion3;
	}
	
	@NotNull(message="会员直推奖；0不能为空")
	public Integer getZhitui0() {
		return zhitui0;
	}

	public void setZhitui0(Integer zhitui0) {
		this.zhitui0 = zhitui0;
	}
	
	@NotNull(message="1级代理直推奖；6%不能为空")
	public Integer getZhitui1() {
		return zhitui1;
	}

	public void setZhitui1(Integer zhitui1) {
		this.zhitui1 = zhitui1;
	}
	
	@NotNull(message="2级代理直推奖；10%不能为空")
	public Integer getZhitui2() {
		return zhitui2;
	}

	public void setZhitui2(Integer zhitui2) {
		this.zhitui2 = zhitui2;
	}
	
	@NotNull(message="3级代理直推奖；12%不能为空")
	public Integer getZhitui3() {
		return zhitui3;
	}

	public void setZhitui3(Integer zhitui3) {
		this.zhitui3 = zhitui3;
	}
	
	@Length(min=1, max=11, message="会员合作奖；0长度必须介于 1 和 11 之间")
	public String getHezuo0() {
		return hezuo0;
	}

	public void setHezuo0(String hezuo0) {
		this.hezuo0 = hezuo0;
	}
	
	@NotNull(message="1级代理合作奖；14%不能为空")
	public Integer getHezuo1() {
		return hezuo1;
	}

	public void setHezuo1(Integer hezuo1) {
		this.hezuo1 = hezuo1;
	}
	
	@NotNull(message="2级代理合作奖；16%不能为空")
	public Integer getHezuo2() {
		return hezuo2;
	}

	public void setHezuo2(Integer hezuo2) {
		this.hezuo2 = hezuo2;
	}
	
	@NotNull(message="3级代理合作奖；18%不能为空")
	public Integer getHezuo3() {
		return hezuo3;
	}

	public void setHezuo3(Integer hezuo3) {
		this.hezuo3 = hezuo3;
	}
	
	@NotNull(message="会员管理奖；0不能为空")
	public Integer getGuanli0() {
		return guanli0;
	}

	public void setGuanli0(Integer guanli0) {
		this.guanli0 = guanli0;
	}
	
	@Length(min=1, max=11, message="1级代理管理奖；6%长度必须介于 1 和 11 之间")
	public Integer getGuanli1() {
		return guanli1;
	}

	public void setGuanli1(Integer guanli1) {
		this.guanli1 = guanli1;
	}
	
	@Length(min=1, max=11, message="2级代理管理奖；10%长度必须介于 1 和 11 之间")
	public Integer getGuanli2() {
		return guanli2;
	}

	public void setGuanli2(Integer guanli2) {
		this.guanli2 = guanli2;
	}
	
	@NotNull(message="3级代理管理奖；12%不能为空")
	public Integer getGuanli3() {
		return guanli3;
	}

	public void setGuanli3(Integer guanli3) {
		this.guanli3 = guanli3;
	}
	
	@NotNull(message="会员推店奖；0不能为空")
	public Integer getTuidian0() {
		return tuidian0;
	}

	public void setTuidian0(Integer tuidian0) {
		this.tuidian0 = tuidian0;
	}
	
	@NotNull(message="1级代理推店奖；10%不能为空")
	public Integer getTuidian1() {
		return tuidian1;
	}

	public void setTuidian1(Integer tuidian1) {
		this.tuidian1 = tuidian1;
	}
	
	@NotNull(message="2级代理推店奖；15%不能为空")
	public Integer getTuidian2() {
		return tuidian2;
	}

	public void setTuidian2(Integer tuidian2) {
		this.tuidian2 = tuidian2;
	}
	
	@NotNull(message="3级代理推店奖；15%不能为空")
	public Integer getTuidian3() {
		return tuidian3;
	}

	public void setTuidian3(Integer tuidian3) {
		this.tuidian3 = tuidian3;
	}
	
	@NotNull(message="会员周封顶；0不能为空")
	public Integer getZhou0() {
		return zhou0;
	}

	public void setZhou0(Integer zhou0) {
		this.zhou0 = zhou0;
	}
	
	@NotNull(message="1级代理周封顶；3000不能为空")
	public Integer getZhou1() {
		return zhou1;
	}

	public void setZhou1(Integer zhou1) {
		this.zhou1 = zhou1;
	}
	
	@NotNull(message="2级代理周封顶；6000不能为空")
	public Integer getZhou2() {
		return zhou2;
	}

	public void setZhou2(Integer zhou2) {
		this.zhou2 = zhou2;
	}
	
	@NotNull(message="3级代理周封顶；8000不能为空")
	public Integer getZhou3() {
		return zhou3;
	}

	public void setZhou3(Integer zhou3) {
		this.zhou3 = zhou3;
	}

    public Integer getBaodan() {
        return baodan;
    }

    public void setBaodan(Integer baodan) {
        this.baodan = baodan;
    }
}