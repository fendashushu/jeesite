/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.entity.statistics;

import java.math.BigDecimal;
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
	private Integer newMembers;		// 新增会员数
	private Integer allMembers;		// 会员总数
	private BigDecimal newBonus;		// 新增业绩
	private BigDecimal allBonus;		// 业绩总数
	private BigDecimal outBonus;		// 奖金发放
	private BigDecimal outAllBonus;		// 奖金发放总数
	private BigDecimal bobi;		// 拨比
	private BigDecimal allBobi;		// 总波比
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

    public Integer getNewMembers() {
        return newMembers;
    }

    public void setNewMembers(Integer newMembers) {
        this.newMembers = newMembers;
    }

    public Integer getAllMembers() {
        return allMembers;
    }

    public void setAllMembers(Integer allMembers) {
        this.allMembers = allMembers;
    }

    public BigDecimal getNewBonus() {
        return newBonus;
    }

    public void setNewBonus(BigDecimal newBonus) {
        this.newBonus = newBonus;
    }

    public BigDecimal getAllBonus() {
        return allBonus;
    }

    public void setAllBonus(BigDecimal allBonus) {
        this.allBonus = allBonus;
    }

    public BigDecimal getOutBonus() {
        return outBonus;
    }

    public void setOutBonus(BigDecimal outBonus) {
        this.outBonus = outBonus;
    }

    public BigDecimal getOutAllBonus() {
        return outAllBonus;
    }

    public void setOutAllBonus(BigDecimal outAllBonus) {
        this.outAllBonus = outAllBonus;
    }

    public BigDecimal getBobi() {
        return bobi;
    }

    public void setBobi(BigDecimal bobi) {
        this.bobi = bobi;
    }

    public BigDecimal getAllBobi() {
        return allBobi;
    }

    public void setAllBobi(BigDecimal allBobi) {
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