package com.bank.bankinsystem.dao;

import com.bank.bankinsystem.entity.Customer;
import com.bank.bankinsystem.exception.CustomerException;

public interface CustomerDao {

	
	public Customer loginCustomer(String customerUsername, String customerPassword, int customerAccountNumber)throws CustomerException;
	
	public int viewBalance(int customerAccountNumber) throws CustomerException;
	
	public int Deposit(int customerAccountNumber, int Balance)throws CustomerException;
	
	public int Withdraw(int customerAccountNumber, int Withdraw)throws CustomerException;
	
	public int Transfer(int customerAccountNumber, int amount, int customerAccountNumber2)throws CustomerException;
	
	
}
