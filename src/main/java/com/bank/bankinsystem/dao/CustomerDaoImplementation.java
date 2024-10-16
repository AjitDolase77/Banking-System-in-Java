package com.bank.bankinsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.bankinsystem.databaseconnection.DatabaseConnection;
import com.bank.bankinsystem.entity.Customer;
import com.bank.bankinsystem.exception.CustomerException;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

public class CustomerDaoImplementation implements CustomerDao{

	@Override
	public Customer loginCustomer(String customerUsername, String customerPassword, int customerAccountNumber)
			throws CustomerException {
		Customer cust=null;
		try (Connection conn=DatabaseConnection.provideConnection()){
			PreparedStatement prsm=conn.prepareStatement("select * from customerInformation i inner join acc a on i.cid=a.cid where customerName=? and customerPassword=? and customerAccountNumber=? ");
			
			prsm.setString(1, customerUsername);
			prsm.setString(2, customerPassword);
			prsm.setInt(3, customerAccountNumber);
			
			ResultSet rs=prsm.executeQuery();
			
			if(rs.next()) {
		
				int ac=rs.getInt("customerAccountNumber");
				String n=rs.getString("customerName");
				int b=rs.getInt("customerBalance");
				String e=rs.getString("customerEmail");
				String p=rs.getString("customerPassword");
				
				String m=rs.getString("customerMobile");
				String ad=rs.getString("customerAddress");
				
				cust=new Customer(ac,n,b,e,p,m,ad);
			}
			else {
				throw new CustomerException("Invalid Customer Name & Password !!!");
			}
			
		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		}
		
		return cust;
	}

	@Override
	public int viewBalance(int customerAccountNumber) throws CustomerException {
			

		int cust_bal=-1;
		
		try (Connection conn=DatabaseConnection.provideConnection()){
			PreparedStatement prsm= conn.prepareStatement("select customerBalance from acc where customerAccountNumber=?");
			
			prsm.setInt(1,customerAccountNumber);
			
			ResultSet rs=prsm.executeQuery();
			
			if(rs.next()) {
				cust_bal=rs.getInt("customerBalance");
			}
		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		}
		
		
		return cust_bal;
	}

	@Override
	public int Deposit(int customerAccountNumber, int Balance) throws CustomerException {
		int bal=-1;
		
		try (Connection conn=DatabaseConnection.provideConnection()){
			PreparedStatement prsm=conn.prepareStatement("update acc set customerBalance = customerBalance+ ? where customerAccountNumber = ? ");
			
			prsm.setInt(1, Balance);
			prsm.setInt(2,customerAccountNumber);

			int rs=prsm.executeUpdate();
			
		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int Withdraw(int customerAccountNumber, int Withdraw) throws CustomerException {
		int witdrw=-1;
		
		
		try (Connection conn=DatabaseConnection.provideConnection()){
			
			PreparedStatement prsm=conn.prepareStatement("Update acc set customerBalance=customerBalance- ? where customerAccountNumber = ? ");
			
			prsm.setInt(1, Withdraw);
			prsm.setInt(2, customerAccountNumber);
			
			int rs=prsm.executeUpdate();
			
		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int Transfer(int customerAccountNumber, int amount, int customerAccountNumber2) throws CustomerException {
		int view_balance=viewBalance(customerAccountNumber);
		
		if(view_balance > amount && checkBalance(customerAccountNumber2)) {
			int witdr=Withdraw(customerAccountNumber, amount);
			int depst=Deposit(customerAccountNumber2, amount);
		}else {
			throw new CustomerException("Insufficient Balance!!!");
		}
		
		
		return 0;
	}

	private boolean checkBalance(int customerAccountNumber) {
		try (Connection conn=DatabaseConnection.provideConnection()){
			PreparedStatement prsm=conn.prepareStatement("select * from acc where customerAccountNumber= ? ");
			
			prsm.setInt(1, customerAccountNumber);
			
			ResultSet rs=prsm.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	
}
