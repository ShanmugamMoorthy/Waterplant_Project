package org.we5.waterplant.javaclass;

public class GetRawmaterial {
	String vendor;
	String prod_name;
	int quantity;
	double price;
  
	public GetRawmaterial(){
		
	}
	
	public GetRawmaterial(String vendor, String product, int quantity, double price) {
		super();
		this.vendor = vendor;
		this.quantity = quantity;
		this.prod_name = product;
		this.price = price;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getVendor() {
		return this.vendor;
	}

	

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return this.price;
	}
}