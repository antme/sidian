package com.sidian.bean;

import java.util.Map;

public class TDefSku extends BaseEntity {

	// 条码
	private String Sku;

	// 名字
	private String PName;

	// 外部条形码
	private String SkuOut;

	// 打印条码
	private String PrintSku;

	// 是否明星产品
	private boolean isStarProduct;

	// 是否拇指
	private boolean isFinger;

	// 样式名
	private String StyleName;

	private String Style;
	
	private long checkedDateTime;
	
	//备注
	private String remark;
	
	//备注
	private Map<String, Object> sizeMap;

	// 
	private String Store;

	private String SizeName;
	
	private String Clr;
	
	private String size;
	
	public String getSku() {
		return Sku;
	}

	public void setSku(String sku) {
		Sku = sku;
	}

	public String getSkuOut() {
		return SkuOut;
	}

	public void setSkuOut(String skuOut) {
		SkuOut = skuOut;
	}

	public String getPrintSku() {
		return PrintSku;
	}

	public void setPrintSku(String printSku) {
		PrintSku = printSku;
	}

	public String getPName() {
		return PName;
	}

	public void setPName(String pName) {
		PName = pName;
	}

	public boolean isStarProduct() {
		return isStarProduct;
	}

	public void setStarProduct(boolean isStarProduct) {
		this.isStarProduct = isStarProduct;
	}

	public String getStyleName() {
		return StyleName;
	}

	public void setStyleName(String styleName) {
		StyleName = styleName;
	}

	public String getStyle() {
		return Style;
	}

	public void setStyle(String style) {
		Style = style;
	}

	public long getCheckedDateTime() {
    	return checkedDateTime;
    }

	public void setCheckedDateTime(long checkedDateTime) {
    	this.checkedDateTime = checkedDateTime;
    }

	public String getRemark() {
    	return remark;
    }

	public void setRemark(String remark) {
    	this.remark = remark;
    }

	public String getStore() {
    	return Store;
    }

	public void setStore(String store) {
    	Store = store;
    }

	public String getClr() {
    	return Clr;
    }

	public void setClr(String clr) {
    	Clr = clr;
    }

	public String getSize() {
    	return size;
    }

	public void setSize(String size) {
    	this.size = size;
    }

	public String getSizeName() {
    	return SizeName;
    }

	public void setSizeName(String sizeName) {
    	SizeName = sizeName;
    }

	public Map<String, Object> getSizeMap() {
    	return sizeMap;
    }

	public void setSizeMap(Map<String, Object> sizeMap) {
    	this.sizeMap = sizeMap;
    }

	public boolean isFinger() {
    	return isFinger;
    }

	public void setFinger(boolean isFinger) {
    	this.isFinger = isFinger;
    }
	
	
	
	


}
