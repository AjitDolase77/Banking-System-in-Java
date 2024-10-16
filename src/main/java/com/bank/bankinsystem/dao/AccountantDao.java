package com.bank.bankinsystem.dao;

import com.bank.bankinsystem.entity.Accountant;
import com.bank.bankinsystem.entity.Customer;
import com.bank.bankinsystem.exception.AccountantException;
import com.bank.bankinsystem.exception.CustomerException;

public interface AccountantDao {

	public Accountant LoginAccountant(String accountantUsername, String accountantPassword)throws AccountantException;
	
	public int addCustomer(String customerName , String customerMail, String customerPassword, String customerMobile, String customerAddress) throws CustomerException;

	public String addAccount(int customerBalance, int cid) throws CustomerException;
	
	public String updateCustomer(int customerAccountNumber, String customerName,String customerEmail, String customerPassword, String customerMobile, String customerAddress)throws CustomerException;
	
	public String Deleteaccount(int customerAccountNumber )throws CustomerException;
	
	public Customer viewcustomer(String customerAccountNumber) throws CustomerException;
	
	public Customer viewAllCustomers()throws CustomerException; 
}
