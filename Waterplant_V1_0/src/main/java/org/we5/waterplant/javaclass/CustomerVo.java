package org.we5.waterplant.javaclass;

public class CustomerVo {
//	int custId;
	String code;
	String name;
	String company;
	String email;
	String mobileNumber;
	int credit;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	/*public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getcompany() {
		return company;
	}
	public void setcompany(String company) {
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
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	@Override
	public String toString() {
		return "CustomerVo [name=" + name + ",  code=" + code + ",company=" + company + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + ", credit=" + credit + "]";
	}

}
