/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.entity.orders;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单Entity
 * @author li
 * @version 2018-11-20
 */
public class Orders extends DataEntity<Orders> {
	
	private static final long serialVersionUID = 1L;
	private Long orderId;		// 订单编号
	private String goodsId;		// 商品编号
	private String loginName;		// 会员编号/购买人
	private String takeName;		// 收货人
	private String takeAddress;		// 收货地址
	private String takePhone;		// 收货电话
	private BigDecimal goodsPrice;		// 商品单价
	private BigDecimal vipPrice;		// 店铺商品单价
	private Integer goodsCount;		// 商品数量
	private String orderType;		// 订单类型
	private String status;		// 订单状态
	private String expressCompany;		// 物流公司
	private String expressNum;		// 物流单号
	private Date expressDate;		// 发货日期
	private String ext1;		// 扩展1
	private String ext2;		// 扩展2
	
	public Orders() {
		super();
	}

	public Orders(String id){
		super(id);
	}

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Length(min=1, max=64, message="商品编号长度必须介于 1 和 64 之间")
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	@Length(min=1, max=100, message="会员编号/购买人长度必须介于 1 和 100 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=1, max=100, message="收货人长度必须介于 1 和 100 之间")
	public String getTakeName() {
		return takeName;
	}

	public void setTakeName(String takeName) {
		this.takeName = takeName;
	}
	
	@Length(min=1, max=1000, message="收货地址长度必须介于 1 和 1000 之间")
	public String getTakeAddress() {
		return takeAddress;
	}

	public void setTakeAddress(String takeAddress) {
		this.takeAddress = takeAddress;
	}
	
	@Length(min=1, max=20, message="收货电话长度必须介于 1 和 20 之间")
	public String getTakePhone() {
		return takePhone;
	}

	public void setTakePhone(String takePhone) {
		this.takePhone = takePhone;
	}

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    @Length(min=0, max=2, message="订单类型长度必须介于 0 和 2 之间")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Length(min=0, max=2, message="订单状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=500, message="物流公司长度必须介于 0 和 500 之间")
	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}
	
	@Length(min=0, max=50, message="物流单号长度必须介于 0 和 50 之间")
	public String getExpressNum() {
		return expressNum;
	}

	public void setExpressNum(String expressNum) {
		this.expressNum = expressNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpressDate() {
		return expressDate;
	}

	public void setExpressDate(Date expressDate) {
		this.expressDate = expressDate;
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