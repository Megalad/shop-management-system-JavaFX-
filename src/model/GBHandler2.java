package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import view.*;
import java.util.*;

public class GBHandler2 {
	

		
		private static Connection con;
		private static String userName="root";
		private static String password="12345678";
		private static int port=3306; 
		private static String dbName="shoptest";
		private static String host="localhost";
		
		public static boolean openConnection()
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url="jdbc:mysql://"+host+":"+port+"/"+dbName; 
				con=DriverManager.getConnection (url, userName, password);
				return true;
				}
			catch(Exception e)
			{
				return false;
			}
		}
		public static boolean closeConnection()
		{
			try {
					con.close();
					return true;
				}
			catch(Exception e)
				{
				return false;
				}
		}

		
		public static int getCustomerID(String customerName,String phone)
		{
			int customerID=0;
			try {
				openConnection();
			    String query = "select customerID from customer where customerName=?";
			    PreparedStatement ps = con.prepareStatement(query);
			    ps.setString(1, customerName);
			    ResultSet rs = ps.executeQuery();
			    if (rs.next()) {
			    	customerID = rs.getInt("customerID");
			    }
			    else {
			    	insertCustomerTable(customerName,phone);
			    }
			    closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		    return customerID;
		}  
		public static boolean insertCustomerTable(String customerName, String phone) {
		    
		    try {
		        openConnection();
		        String query = "INSERT INTO customer(customerName, phone) VALUES (?, ?)";
		        PreparedStatement ps = con.prepareStatement(query);
		        ps.setString(1, customerName);
		        ps.setString(2, phone);
		        int line = ps.executeUpdate();
		        closeConnection();
		        return line>0;
		    } catch (Exception e) {
		        e.printStackTrace();
		        
		    }
		    return false;
		    
		    
		    
		}
		public static int getAmountID(int amount)
		{
			int amountid=0;
			try {
				openConnection();
			    String query = "select amountID from amount where amount=?";
			    PreparedStatement ps = con.prepareStatement(query);
			    ps.setInt(1, amount);
			    ResultSet rs = ps.executeQuery();
			    if (rs.next()) {
			    	amountid = rs.getInt("amountID");
			    }
			    else {
			    	amountid=insertAmountTable(amount);
			    }
			    closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		    return amountid;
		}
		
		public static int insertAmountTable(int amount) {
		    int amountID = 0;
		    try {
		        openConnection();
		        String query = "INSERT INTO amount(amount) VALUES (?)";
		        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		        ps.setInt(1, amount);
		        int rowsAffected = ps.executeUpdate();
		        if (rowsAffected > 0) {
		            ResultSet generatedKeys = ps.getGeneratedKeys();
		            if (generatedKeys.next()) {
		                amountID = generatedKeys.getInt(1);
		            }
		        }
		        closeConnection();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return amountID;
		}
		public static boolean insertCreditTable(String customerName, String phone, String note, java.sql.Date startDate, int amount) {
		    
			try {
		        openConnection();
		        int customerID = getCustomerID(customerName, phone);
		        int amountID = getAmountID(amount);
		        String query = "INSERT INTO credit(customer_ID, note, startDate, amount_ID) VALUES (?, ?, ?, ?)";
		        PreparedStatement ps = con.prepareStatement(query);
		        ps.setInt(1, customerID);
		        ps.setString(2, note);
		        ps.setDate(3, startDate);
		        ps.setInt(3, amountID);

		        int line = ps.executeUpdate();
		        closeConnection();

		        return line > 0;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}
		
		public static void main(String[] args)
		{
			String customerName = "John Doe";
		    String phone = "123-456-7890";
		    String note = "Payment for services";
		    java.sql.Date startDate = java.sql.Date.valueOf("2023-09-11"); // Replace with the desired start date
		    int amount = 1000;
		    
		    boolean success = insertCreditTable(customerName, phone, note, startDate, amount);

		    if (success) {
		        System.out.println("Data inserted successfully!");
		    } else {
		        System.out.println("Data insertion failed.");
		    }
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
