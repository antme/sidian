package com.sidian.bean;

public class TDefSku extends BaseEntity {

	// 条码
	private String Sku;
	
	//名字
	private String PName;

	// 外部条形码
	private String SkuOut;

	// 打印条码
	private String PrintSku;

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
	
	

}
