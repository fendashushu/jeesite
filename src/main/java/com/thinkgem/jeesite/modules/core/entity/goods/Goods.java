/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.entity.goods;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息Entity
 * @author li
 * @version 2018-11-07
 */
public class Goods extends DataEntity<Goods> {
	
	private static final long serialVersionUID = 1L;
	private String goodsName;		// 商品名称
	private String goodsType;		// 商品类型
	private BigDecimal price;		// 零售价
	private BigDecimal vipPrice;		// 会员价
	private Integer inventory;		// 库存
	private String image;		// 商品图片
	private String goodsDesc;		// 商品描述
	private String xh;		// 序号
	private String isMust;		// 是否必点
	private String ext1;		// 扩展1
	private String ext2;		// 扩展2
    private String  status;
    private Date publishDate;
	
	public Goods() {
		super();
	}

	public Goods(String id){
		super(id);
	}

	@Length(min=1, max=500, message="商品名称长度必须介于 1 和 500 之间")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	@Length(min=1, max=2, message="商品类型长度必须介于 1 和 2 之间")
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    @Length(min=0, max=1000, message="商品图片长度必须介于 0 和 1000 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=0, max=5000, message="商品描述长度必须介于 0 和 5000 之间")
	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	
	@Length(min=0, max=11, message="序号长度必须介于 0 和 11 之间")
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
	
	@Length(min=0, max=2, message="是否必点长度必须介于 0 和 2 之间")
	public String getIsMust() {
		return isMust;
	}

	public void setIsMust(String isMust) {
		this.isMust = isMust;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}