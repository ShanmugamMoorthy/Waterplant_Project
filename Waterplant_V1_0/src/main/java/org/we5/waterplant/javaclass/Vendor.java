package org.we5.waterplant.javaclass;

import org.we5.waterplant.exception.WaterPlantException;

public class Vendor extends WaterPlantException {
	
	public Vendor()
	{
		
	}
	
	public Vendor(String message) {
		super(message);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	int vendorId;
	String name;
	String company;
	String email;
	String mobileNumber;
	
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
}
