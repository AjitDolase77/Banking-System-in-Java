package com.bank.bankinsystem;

import java.sql.SQLException;
import java.util.Scanner;

import com.bank.bankinsystem.dao.AccountantDao;
import com.bank.bankinsystem.dao.AccountantDaoImplementation;
import com.bank.bankinsystem.dao.CustomerDao;
import com.bank.bankinsystem.dao.CustomerDaoImplementation;
import com.bank.bankinsystem.entity.Accountant;
import com.bank.bankinsystem.entity.Customer;
import com.bank.bankinsystem.exception.AccountantException;
import com.bank.bankinsystem.exception.CustomerException;


public class App {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        boolean f=true;
        
        while(f) {
        	System.out.println(" ");
        	System.out.println("-------------------------");
        	System.out.println("Welcome To Banking System");
        	System.out.println("-------------------------");
        	System.out.println(" ");
        	System.out.println(" ");
        	System.out.println("1. Admin Login Portal \r\n"
        			+ "2. Customer Login Portal");
        	System.out.println(" ");
        	System.out.println(" ");
        	System.out.println("Choose Your Option");
        	int choice = sc.nextInt();
        	System.out.println(" ");
        	
        	switch (choice) {
        	
//        	case 1 start
        	
			case 1:
				System.out.println("-------------------------------------------------");
				System.out.println("Admin Login Credentiols ----> Only For Accountant");
				System.out.println("-------------------------------------------------");
				System.out.println(" ");
	        	System.out.println(" ");
				System.out.println("Enter Username: ");
				String username= sc.next();
	        	System.out.println(" ");
				System.out.println("Enter Password: ");
				String pass=sc.next();
				System.out.println(" ");
	        	System.out.println(" ");
				
				
				
				AccountantDao ad=new AccountantDaoImplementation();
				try {
					Accountant a=ad.LoginAccountant(username, pass);
					if(a ==null) {
						System.out.println("Wrong Credentials");
						System.out.println(" ");
			        	System.out.println(" ");
						break;
					}
					
					System.out.println("Login Successfully!!!");
					System.out.println(" ");
		        	System.out.println(" ");
		        	System.out.println("------------------------------------------------------------------");
					System.out.println("Welcome "+a.getAccountantUsername()+" as Admin of Banking Syatem." );
					System.out.println("------------------------------------------------------------------");
					System.out.println(" ");
		        	System.out.println(" ");
					
					boolean y=true;
					while (y) {
						System.out.println("\r\n"
								+"1. Add New Customer: \r\n"
								+"2. Update Customer Information: \r\n"
								+"3. Remove Account By Account Number: \r\n"
								+"4. View Account Details By Account Number: \r\n"
								+"5. View All Customers: \r\n"
								+"6. Logout As Admin: \r\n"
								);
						System.out.println(" ");
			        	
					int x=sc.nextInt();
					if(x==1) {
						
						System.out.println("-----------------------------------------------");
						System.out.println("Please Fill The Information Of New Account --->");
						System.out.println("-----------------------------------------------");
						System.out.println(" ");
						System.out.println("Enter New Customer Name: ");
						String c1=sc.next();
						System.out.println("Enter New Customer Account Opening Balance: ");
						int c2=sc.nextInt();
						System.out.println("Enter New Customer Email: ");
						String c3=sc.next();
						System.out.println("Enter New Customer Password: ");
						String c4=sc.next();
						System.out.println("Enter New Customer Mobile: ");
						String c5=sc.next();
						System.out.println("Enter New Customer Address: ");
						String c6=sc.next();
						
						int s1=-1;
						
						try {
							
							s1=ad.addCustomer(c1, c3, c4, c5, c6);
							
								try {
									ad.addAccount(c2, s1);
								} catch (CustomerException e) {
									e.printStackTrace();
								}
			
						} catch (CustomerException e) {
							System.out.println(e.getMessage());
						}
						System.out.println("----------------------------------");
						
					}
					
					if(x==2) {
						System.out.println("-----------------------------------");
						System.out.println("Update Customer Information Here...");
						System.out.println("-----------------------------------");
						System.out.println(" ");
						System.out.println("Enter Existing Customer Account Number For Update Data --->");
						int up=sc.nextInt();
						System.out.println(" ");
						System.out.println(" ");
						System.out.println("Enter New Name: ");
						String up1=sc.next();
						System.out.println("Enter New Email: ");
						String up2=sc.next();
						System.out.println("Enter New Password: ");
						String up3=sc.next();
						System.out.println("Enter New Mobile: ");
						String up4=sc.next();
						System.out.println("Enter New Address: ");
						String up5=sc.next();
						
						try {
							String mess=ad.updateCustomer(up, up1, up2, up3, up4, up5);
						} catch (CustomerException e) {
							e.printStackTrace();
						}
						System.out.println("----------------------------------");
					}
					
					if(x==3) {
						System.out.println("-------------------------------------");
						System.out.println("Remove Account By Account Number --->");
						System.out.println("-------------------------------------");
						System.out.println(" ");
						System.out.println("Enter Existing Account Nubmer: ");
						int rac=sc.nextInt();
						System.out.println(" ");
						System.out.println(" ");
						String s=null;
						
						try {
							s=ad.Deleteaccount(rac);
						} catch (CustomerException e) {
							e.printStackTrace();
						}
						if(s!=null) {
							System.out.println(s);
						}
					}
					
					if(x==4) {
						
						System.out.println("Customer Details By Given Account Number");
						
						System.out.println(" ");
						
						System.out.println("Enter Account Number of Customer: ");
						String ac=sc.next();
						
						try {
							Customer cus=ad.viewcustomer(ac);
							
							if(cus!=null) {
								System.out.println("------------------------");
								System.out.println("Given Customer Data Is: ");
								System.out.println("------------------------");
								System.out.println(" ");
								
								
								System.out.println("Customer Account Number: "+ cus.getCustomerAccountNumber());
								System.out.println("Customer Account Name: "+cus.getCustomerName());
								System.out.println("Customer Account Balance: "+cus.getCustomerBalance());
								System.out.println("Customer Account Password: "+cus.getCustomerEmail());
								System.out.println("Customer Email: "+cus.getCustomerPassword());
								System.out.println("Customer Mobile: "+cus.getCustomerMobile());
								System.out.println("Customer Address: "+cus.getCustomerAddress());
							}else {
								System.out.println("Account Does Not Exist....");
								
								System.out.println(" ");
							}
							
							
							
							
							
							
						} catch (CustomerException e) {
							e.printStackTrace();
						}
					}
					
					
					if(x==5) {
						try {
							System.out.println("-------------------");
							System.out.println("All Customer List: ");
							System.out.println("-------------------");
							System.out.println(" ");
							Customer cus=ad.viewAllCustomers();
							
						} catch (CustomerException e) {
							e.printStackTrace();
						}
					}
					
					
					
					if(x==6) {
						System.out.println("Successfully Logged Out As a Admin...!!!");
						y=false;
					}
					
					
					
					}	
					break;
					
				} catch (AccountantException e) {
					System.out.println(e.getMessage());
				}
				break;
				
//			case 2 start
			case 2:
				System.out.println("--------------------------------------------------");
				System.out.println("Customer Login Credentiols ----> Only For Customer");
				System.out.println("--------------------------------------------------");
				System.out.println(" ");
				
				System.out.println("Enter Customer Username: ");
				String customerUsername=sc.next();
				System.out.println("Enter Customer Password: ");
				String customerPassword=sc.next();
				System.out.println("Enter Customer Account Number: ");
				int customerAccountNumber=sc.nextInt();
				System.out.println(" ");
				
				CustomerDao cd=new CustomerDaoImplementation();
				
				try {
					Customer cus=cd.loginCustomer(customerUsername, customerPassword, customerAccountNumber);
					
					
					 if (cus != null) {
						 	System.out.println("-----------------------------------------------------");
				            System.out.println("Welcome To Your Account " + cus.getCustomerName()+" !!!");
				            System.out.println("-----------------------------------------------------");
				            System.out.println(" ");
				            
				         				            
				        } else {
				        	
				            System.out.println("Login failed. Invalid credentials.");
				            
				            System.out.println(" ");
				        }
					 
					 
					 boolean m=true;
					 while(m) {
						 System.out.println("\r\n"
								 +"1. View Account Balance: \r\n"
								 +"2. Deposit Money To Your Account: \r\n"
								 +"3. Withdraw Money From Your Account: \r\n"
								 +"4. Transfer Money\r\n"
								 +"5. Logout...\r\n"
								 );
						 
						 System.out.println(" ");
						 System.out.println("Choose Your Options: ");
						 int x=sc.nextInt();
						 
						 
						 
						 if(x==1) {
							 System.out.println("--------------------------");
							 System.out.println("Your Account Balance");
							 System.out.println("--------------------------");
							 System.out.println(" ");
							 
							 System.out.println("Your Account Balance is: " + cd.viewBalance(customerAccountNumber));
							 
						 }
						 
						 
						 if(x==2) {
							 System.out.println("--------------------------");
							 System.out.println("Deposit Money To Your Account");
							 System.out.println("--------------------------");
							 System.out.println(" ");
							 
							 System.out.println("Enter Amount You Want To Deposit: ");
							 int amt=sc.nextInt();
							 cd.Deposit(customerAccountNumber, amt);
							 System.out.println(" ");
							 System.out.println("Your Balance After Deposit "+amt+"rs");
							 System.out.println(cd.viewBalance(customerAccountNumber));
							 System.out.println(" ");
						 }
						 
						 
						 if(x==3) {
							 System.out.println("--------------------------");
							 System.out.println("Withdraw Money From Your Account");
							 System.out.println("--------------------------");
							 System.out.println(" ");
							 
							 System.out.println("Enter Amount You Want To Withdraw: ");
							 int witdr=sc.nextInt();
							 cd.Withdraw(customerAccountNumber, witdr);
							 System.out.println(" ");
							 System.out.println("Your Balance After Withdraw "+witdr+"rs\"");
							 System.out.println(cd.viewBalance(customerAccountNumber));
							 System.out.println(" ");
						 }
						 
						 
						 
						 if(x==4) {
							 System.out.println("--------------");
							 System.out.println("Money Transfer");
							 System.out.println("--------------");
							 System.out.println(" ");
							 
							 System.out.println("Enter Amount: ");
							 int amt=sc.nextInt();
							 System.out.println(" ");
							 System.out.println("Enter Account Number For Transfer Amount: ");
							 int acn=sc.nextInt();
							 
							 
							 
							 try {
								cd.Transfer(customerAccountNumber, amt, acn);
								System.out.println("Amount Transfer Successfully!!!");
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
							 
						 }
						 
						 if(x==5) {
							 System.out.println("--------------");
							 System.out.println("Logout Customer");
							 System.out.println("--------------");
							 System.out.println(" ");
							 m=false;
							 
						 }
				 
					 }
					 
					 break;
					 	
				} catch (CustomerException e) {
					System.out.println("Login Failed ...!!!" +e.getMessage());
					
				}
				
			}
        }
    }
}
