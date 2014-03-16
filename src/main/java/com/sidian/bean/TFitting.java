package com.sidian.bean;

import java.util.Date;

public class TFitting extends BaseEntity {

	// 试穿编号
	private String fittingCode;

	// 店铺
	private String Store;

	// 试穿日期 yyyy-mm-dd
	private Date fittingDate;

	// 试穿时间 hh:mm:ss
	private String fittingTime;

	// 客人编号
	private String customerCode;

	// 年龄
	private int customerAge;

	// 条码
	private int Sku;

	// 是否成交
	private Boolean isSaled;

	// 意见
	private String feedBack;

	// 选项1价格
	private Boolean isPriceOk;

	// 选项2颜色
	private Boolean isColorOk;

	// 选项3尺码
	private Boolean isSizeOk;

	// 选项4搭配
	private Boolean isSuitable;

	// 选项5款式
	private Boolean isStyleOk;

	// 选项6其它
	private Boolean isOtherOk;

	public String getFittingCode() {
		return fittingCode;
	}

	public void setFittingCode(String fittingCode) {
		this.fittingCode = fittingCode;
	}

	public String getStore() {
		return Store;
	}

	public void setStore(String store) {
		Store = store;
	}

	public Date getFittingDate() {
		return fittingDate;
	}

	public void setFittingDate(Date fittingDate) {
		this.fittingDate = fittingDate;
	}

	public String getFittingTime() {
		return fittingTime;
	}

	public void setFittingTime(String fittingTime) {
		this.fittingTime = fittingTime;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public int getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(int customerAge) {
		this.customerAge = customerAge;
	}

	public int getSku() {
		return Sku;
	}

	public void setSku(int sku) {
		Sku = sku;
	}

	public Boolean getIsSaled() {
		return isSaled;
	}

	public void setIsSaled(Boolean isSaled) {
		this.isSaled = isSaled;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String customerFeedBack) {
		this.feedBack = customerFeedBack;
	}

	public Boolean getIsPriceOk() {
		return isPriceOk;
	}

	public void setIsPriceOk(Boolean isPriceOk) {
		this.isPriceOk = isPriceOk;
	}

	public Boolean getIsColorOk() {
		return isColorOk;
	}

	public void setIsColorOk(Boolean isColorOk) {
		this.isColorOk = isColorOk;
	}

	public Boolean getIsSizeOk() {
		return isSizeOk;
	}

	public void setIsSizeOk(Boolean isSizeOk) {
		this.isSizeOk = isSizeOk;
	}

	public Boolean getIsSuitable() {
		return isSuitable;
	}

	public void setIsSuitable(Boolean isSuitable) {
		this.isSuitable = isSuitable;
	}

	public Boolean getIsStyleOk() {
		return isStyleOk;
	}

	public void setIsStyleOk(Boolean isStyleOk) {
		this.isStyleOk = isStyleOk;
	}

	public Boolean getIsOtherOk() {
		return isOtherOk;
	}

	public void setIsOtherOk(Boolean isOtherOk) {
		this.isOtherOk = isOtherOk;
	}

}
