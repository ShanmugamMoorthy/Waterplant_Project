package org.we5.waterplant.javaclass;

import java.sql.Date;

import org.we5.waterplant.exception.WaterPlantException;

public class Product extends WaterPlantException {
	
	int prodId;
	int vend_Id;
	String prod_Name;
	String product_desc;
	int quantity;
	int price;
	
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getVend_Id() {
		return vend_Id;
	}
	public void setVend_Id(int vend_Id) {
		this.vend_Id = vend_Id;
	}
	public String getProd_Name() {
		return prod_Name;
	}
	public void setProd_Name(String prod_Name) {
		this.prod_Name = prod_Name;
	}
	public String getProduct_desc() {
		return product_desc;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
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
	public Date getDate_of_creation() {
		return date_of_creation;
	}
	public void setDate_of_creation(Date date_of_creation) {
		this.date_of_creation = date_of_creation;
	}
	Date date_of_creation; 

}
