package com.sidian.bean;


public class TFitting extends BaseEntity {

	// 试穿编号
	private String fittingCode;

	// 店铺
	private String Store;

	// 试穿日期 yyyy-mm-dd
	private String fittingDate;

	// 试穿时间 hh:mm:ss
	private String fittingTime;

	// 客人编号
	private String customerCode;

	// 年龄
	private int customerAge = 0;

	// 条码
	private String Sku;

	// 是否成交
	private int isSaled = 0;

	// 意见
	private String feedBack;

	// 选项1价格, 0, 1 
	private int isPriceOk = 0;

	// 选项2颜色, 0, 1 
	private int isColorOk = 0;

	// 选项3尺码, 0, 1 
	private int isSizeOk = 0;

	// 选项4搭配, 0, 1 
	private int isSuitable = 0;

	// 选项5款式, 0, 1 
	private int isStyleOk = 0;

	// 选项6其它, 0, 1 
	private int isOtherOk = 0;
	
	// 是否推荐, 0, 1 
	private int isRecommend = 0;
	
	// 添加试衣记录的用户名
	private String UserName;

	public String getFittingCode() {
		return fittingCode;
	}

	public void setFittingCode(String fittingCode) {
		this.fittingCode = fittingCode;
	}

	public String getStore() {
		if(Store == null){
			return "";
		}
		return Store;
	}

	public void setStore(String store) {
		Store = store;
	}

	public String getFittingDate() {
		return fittingDate;
	}

	public void setFittingDate(String fittingDate) {
		this.fittingDate = fittingDate;
	}

	public String getFittingTime() {
		return fittingTime;
	}

	public void setFittingTime(String fittingTime) {
		this.fittingTime = fittingTime;
	}

	public String getCustomerCode() {
		if(customerCode == null){
			return "";
		}
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


	public String getSku() {
    	return Sku;
    }

	public void setSku(String sku) {
    	Sku = sku;
    }

	public int getIsSaled() {
		return isSaled;
	}

	public void setIsSaled(int isSaled) {
		this.isSaled = isSaled;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String customerFeedBack) {
		this.feedBack = customerFeedBack;
	}

	public int getIsPriceOk() {
		return isPriceOk;
	}

	public void setIsPriceOk(int isPriceOk) {
		this.isPriceOk = isPriceOk;
	}

	public int getIsColorOk() {
		return isColorOk;
	}

	public void setIsColorOk(int isColorOk) {
		this.isColorOk = isColorOk;
	}

	public int getIsSizeOk() {
		return isSizeOk;
	}

	public void setIsSizeOk(int isSizeOk) {
		this.isSizeOk = isSizeOk;
	}

	public int getIsSuitable() {
		return isSuitable;
	}

	public void setIsSuitable(int isSuitable) {
		this.isSuitable = isSuitable;
	}

	public int getIsStyleOk() {
		return isStyleOk;
	}

	public void setIsStyleOk(int isStyleOk) {
		this.isStyleOk = isStyleOk;
	}

	public int getIsOtherOk() {
		return isOtherOk;
	}

	public void setIsOtherOk(int isOtherOk) {
		this.isOtherOk = isOtherOk;
	}

	public int getIsRecommend() {
    	return isRecommend;
    }

	public void setIsRecommend(int isRecommend) {
    	this.isRecommend = isRecommend;
    }

	public String getUserName() {
    	return UserName;
    }

	public void setUserName(String userName) {
    	UserName = userName;
    }
	
	
	

}
