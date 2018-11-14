/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.entity.statistics;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 统计Entity
 * @author li
 * @version 2018-11-14
 */
public class DayStatistics extends DataEntity<DayStatistics> {
	
	private static final long serialVersionUID = 1L;
	private Date dataDate;		// 统计日期
	private String newMembers;		// 新增会员数
	private String allMembers;		// 会员总数
	private String newBonus;		// 新增业绩
	private String allBonus;		// 业绩总数
	private String outBonus;		// 奖金发放
	private String outAllBonus;		// 奖金发放总数
	private String bobi;		// 拨比
	private String allBobi;		// 总波比
	private String ext1;		// 扩展1
	private String ext2;		// 扩展2
	
	public DayStatistics() {
		super();
	}

	public DayStatistics(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="统计日期不能为空")
	public Date getDataDate() {
		return dataDate;
	}

	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	
	@Length(min=0, max=11, message="新增会员数长度必须介于 0 和 11 之间")
	public String getNewMembers() {
		return newMembers;
	}

	public void setNewMembers(String newMembers) {
		this.newMembers = newMembers;
	}
	
	@Length(min=0, max=11, message="会员总数长度必须介于 0 和 11 之间")
	public String getAllMembers() {
		return allMembers;
	}

	public void setAllMembers(String allMembers) {
		this.allMembers = allMembers;
	}
	
	public String getNewBonus() {
		return newBonus;
	}

	public void setNewBonus(String newBonus) {
		this.newBonus = newBonus;
	}
	
	public String getAllBonus() {
		return allBonus;
	}

	public void setAllBonus(String allBonus) {
		this.allBonus = allBonus;
	}
	
	public String getOutBonus() {
		return outBonus;
	}

	public void setOutBonus(String outBonus) {
		this.outBonus = outBonus;
	}
	
	public String getOutAllBonus() {
		return outAllBonus;
	}

	public void setOutAllBonus(String outAllBonus) {
		this.outAllBonus = outAllBonus;
	}
	
	public String getBobi() {
		return bobi;
	}

	public void setBobi(String bobi) {
		this.bobi = bobi;
	}
	
	public String getAllBobi() {
		return allBobi;
	}

	public void setAllBobi(String allBobi) {
		this.allBobi = allBobi;
	}
	
	@Length(min=0, max=200, message="扩展1长度必须介于 0 和 200 之间")
	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	
	@Length(min=0, max=200, message="扩展2长度必须介于 0 和 200 之间")
	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	
}