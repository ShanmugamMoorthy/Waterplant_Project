package org.we5.waterplant.javaclass;

import java.sql.Date;


public class RawMaterialVo {

	
	int vendId;
	int prodId;
	String prodName;
	String prodDesc;
	int quantity;
	int price;
	Date dateOfCreation;
	
	
	public int getVendId() {
		return vendId;
	}
	public void setVendId(int vendId) {
		this.vendId = vendId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
}
