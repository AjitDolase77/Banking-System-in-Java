package com.bank.bankinsystem.dao;

import java.security.Permission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.bankinsystem.databaseconnection.DatabaseConnection;
import com.bank.bankinsystem.entity.Accountant;
import com.bank.bankinsystem.entity.Customer;
import com.bank.bankinsystem.exception.AccountantException;
import com.bank.bankinsystem.exception.CustomerException;

public class AccountantDaoImplementation implements AccountantDao{

	
	
	
	
	
	
	@Override
	public Accountant LoginAccountant(String accountantUsername, String accountantPassword) throws AccountantException {
		Accountant acc=null;
		try(Connection conn=DatabaseConnection.provideConnection()) {
			
			PreparedStatement prsm=conn.prepareStatement("Select * from accountant where accountantUsername = ? and accountantPassword = ?");
			prsm.setString(1, accountantUsername);
			prsm.setString(2, accountantPassword);
			
			ResultSet rs= prsm.executeQuery();
			
			if(rs.next()) {
				String n=rs.getString("accountantUsername");
				String e=rs.getString("accountantEmail");
				String p=rs.getString("accountantPassword");
				
				acc=new Accountant(n, e, p);
				
			}
		} catch (SQLException e) {
			throw new AccountantException("Invalid Username and Password");
			
			
		}
		return acc;
	}

	
	
	
	
	
	
	
	@Override
	public int addCustomer(String customerName, String customerEmail, String customerPassword, String customerMobile,String customerAddress) throws CustomerException {
		
		
		int cid=-1;
		try (Connection conn=DatabaseConnection.provideConnection()){
			
			
			PreparedStatement prsm= 
					conn.prepareStatement("insert into customerinformation (customerName, customerEmail, customerPassword, customerMobile, customerAddress) values(?,?,?,?,?)");
			
			prsm.setString(1, customerName);
			prsm.setString(2, customerEmail);
			prsm.setString(3, customerPassword);
			prsm.setString(4, customerMobile);
			prsm.setString(5, customerAddress);
			
			int x=prsm.executeUpdate();
			
			if(x>0) {
				PreparedStatement prstm= conn.prepareStatement("SELECT cid FROM customerinformation WHERE customerEmail = ? AND customerMobile = ?");
				prstm.setString(1, customerEmail);
				prstm.setString(2, customerMobile);
				ResultSet rs=prstm.executeQuery();
				
				
				if(rs.next()) {
					cid=rs.getInt("cid");
				}else {System.out.println(" ");
					System.out.println("Inserted Data Is Incorrect !!! Please Try Again!!!");
					System.out.println(" ");
				}
			}
			
			
			
			if(x>0) {
				System.out.println("Customer Added Successfully!!!");
				System.out.println(" ");
			}else {
				System.out.println("Customer Not Added Successfully!!!");
				System.out.println(" ");
			}
		} catch (Exception e) {
			System.out.println(" ");
			System.out.println("SQL Database error");
			System.out.println(" ");
		}
		
		
		
		return cid;
	}








	@Override
	public String addAccount(int customerBalance, int cid) throws CustomerException {
		String message=null;
		
		try (Connection conn=DatabaseConnection.provideConnection()){
			
			PreparedStatement prsm= conn.prepareStatement("insert into acc(customerBalance , cid) values(?,?)");
			prsm.setInt(1, customerBalance);
			prsm.setInt(2, cid);
			
			int x= prsm.executeUpdate();
			
			if(x>0) {
				System.out.println(" ");
				System.out.println("Account Added Successfully!!!");
			}else {
				System.out.println(" ");
				System.out.println("Account Do not Added Successfully!!!");
			}
			
		} catch (SQLException e) {
			System.out.println(" ");
			System.out.println("SQL Related Exception!!!");
			System.out.println(" ");
		}
		return message;
	}







	
	

	@Override
	public String updateCustomer(int customerAccountNumber, String customerName, String customerEmail, String customerPassword, String customerMobile, String customerAddress) throws CustomerException {
		String message=null;
		
		try (Connection conn=DatabaseConnection.provideConnection()){
			
			PreparedStatement prsm= conn.prepareStatement("update customerinformation i inner join acc a on i.cid =a.cid and a.customerAccountNumber = ? set i.customerName=?,i.customerEmail=?, i.customerPassword=?, i.customerMobile=?,  i.customerAddress=?");
			
			prsm.setInt(1, customerAccountNumber);
			prsm.setString(2,customerName);
			prsm.setString(3,customerEmail);
			prsm.setString(4,customerPassword);
			prsm.setString(5,customerMobile);
			prsm.setString(6,customerAddress);
			
			int x=prsm.executeUpdate();
			
			if(x>0) {
				System.out.println(" ");
				System.out.println("Data Update Successfully!!!");
			}else {
				System.out.println(" ");
				System.out.println("Data not Update Please Try Again!!!");
				System.out.println(" ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		
		return message;
	}








	@Override
	public String Deleteaccount(int customerAccountNumber) throws CustomerException {
		String message=null;
		
		try (Connection conn= DatabaseConnection.provideConnection()){
			
			PreparedStatement prsm= conn.prepareStatement("delete i from customerinformation i inner join acc a on i.cid=a.cid where a.customerAccountNumber=? ");
			prsm.setInt(1, customerAccountNumber);
			
			int x=prsm.executeUpdate();
			
			if(x>0) {
				System.out.println(" ");
				System.out.println("Account Deleted Successfully!!!");
			}else {
				System.out.println(" ");
				System.out.println("Failed To Delete----------Account Not Found ");
				System.out.println(" ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		
		return message;
	}








	@Override
	public Customer viewcustomer(String customerAccountNumber) throws CustomerException {
		Customer cust=null;
		
		try (Connection conn= DatabaseConnection.provideConnection()){
			
			PreparedStatement prsm= conn.prepareStatement("select * from customerinformation i inner join acc a on a.cid=i.cid where customerAccountNumber = ? ");
			
			prsm.setString(1, customerAccountNumber);
			
			ResultSet rs=prsm.executeQuery();
			
			if(rs.next()) {
				int a=rs.getInt("customerAccountNumber");
				String n=rs.getString("customerName");
				int b=rs.getInt("customerBalance");
				String e=rs.getString("customerEmail");
				String p=rs.getString("customerPassword");
				
				String m=rs.getString("customerMobile");
				String ad=rs.getString("customerAddress");
				
				cust=new Customer(a,n,b,e,p,m,ad);
			}else {
				throw new CustomerException("Invalid Account Number...");
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return cust;
	}








	@Override
	public Customer viewAllCustomers() throws CustomerException {
		
		Customer cust=null;
		
		try (Connection conn=DatabaseConnection.provideConnection()){
			PreparedStatement prsm=conn.prepareStatement("select * from customerinformation i inner join acc a on a.cid=i.cid");
			ResultSet rs=prsm.executeQuery();
			
			while (rs.next()) {
				int a=rs.getInt("customerAccountNumber");
				String n=rs.getString("customerName");
				int b=rs.getInt("customerBalance");
				String e=rs.getString("customerEmail");
				String p=rs.getString("customerPassword");
				
				String m=rs.getString("customerMobile");
				String ad=rs.getString("customerAddress");
				
				System.out.println("-----------------------");
				System.out.println("Customer Account Number: "+a );
				System.out.println("Customer Account Name: "+n);
				System.out.println("Customer Account Balance: "+b);
				System.out.println("Customer Account Password: "+e);
				System.out.println("Customer Email: "+p);
				System.out.println("Customer Mobile: "+m);
				System.out.println("Customer Address: "+ad);
				System.out.println("-----------------------");
				
				cust=new Customer(a,n,b,e,p,m,ad);
			}
		} catch (SQLException e) {
			throw new CustomerException("Invalid Account Number!!!!");
		}
		
		return cust;
	}
	
	
	
	
	
	
	

	
	
}
