package com.bank.bankinsystem.entity;

public class Customer {

	public int customerAccountNumber;
	
	public String customerName;
	public int customerBalance;
	public String customerPassword;
	public String customerEmail;
	public String customerMobile;
	public String customerAddress;

	

	public Customer(int customerAccountNumber, String customerName, int customerBalance, String customerPassword,
			String customerEmail, String customerMobile, String customerAddress) {
		super();
		this.customerAccountNumber = customerAccountNumber;
		
		this.customerName = customerName;
		this.customerBalance = customerBalance;
		this.customerEmail = customerEmail;
		this.customerPassword = customerPassword;
		
		this.customerMobile = customerMobile;
		this.customerAddress = customerAddress;
	}

	public int getCustomerAccountNumber() {
		return customerAccountNumber;
	}

	public void setCustomerAccountNumber(int customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}

	public int getCustomerBalance() {
		return customerBalance;
	}

	public void setCustomerBalance(int customerBalance) {
		this.customerBalance = customerBalance;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}



	@Override
	public String toString() {
		return "Costomer [customerAccountNumber=" + customerAccountNumber + ", customerBalance=" + customerBalance
				+ ", customerName=" + customerName + ", customerPassword=" + customerPassword + ", customerEmail="
				+ customerEmail + ", customerMobile=" + customerMobile + ", customerAddress=" + customerAddress + "]";
	}
	
	
	
	
}
