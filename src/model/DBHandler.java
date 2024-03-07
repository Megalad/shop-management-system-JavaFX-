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

public class DBHandler {
	
	private static Connection con;
	private static String userName="root";
	private static String password="12345678";
	private static int port=3306; 
	private static String dbName="shoptest";
	private static String host="localhost";
	
	private static int categoryID;
	private static int subcategoryID;
	private static int originalPriceID;
	private static int sellPriceID;
	private static int sizeID;
	private static int colorID;
	
	
	
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
//	public static ArrayList<Item> getAllItem()
//	{
//		
//		ArrayList<Item> items=new ArrayList<Item>();
//		
//		
//		try {
//			
//			openConnection();
//			
//			String sql="select itemID,itemName,quantity,categoryName,subcategoryName,originalPrice,originalStartDate,originalEndDate,sellPrice,sellStartDate,sellEndDate,sizeName,colorName from item,category,subcategory,originalprice,sellprice,size,color where categoryID=subcategories.category_ID  and subcategory_ID=subcategoryID and originalPrice_ID=originalPriceID and sellPrice_ID=sellPriceID and size_ID=sizeID and color_ID=colorID";
//			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
//			while(rs.next())
//			{
////				Item it=new Item(rs.getInt("itemID"),rs.getString("itemName"),rs.getInt("quantity"),rs.getString("categoryName"),rs.getString("subcategoryName"),rs.getInt("originalPrice"),rs.getDate("originalStartDate"),rs.getDate("originalEndDate"),rs.getInt("sellPrice"),rs.getDate("sellStartDate"),rs.getDate("sellEndDate"),rs.getString("sizeName"),rs.getString("colorName"));
////				items.add(it);
//			}
//			closeConnection();
//			
//		}catch(Exception e)
//		{
//			e.printStackTrace() ;
//		}
//		
//		return items;
//	}
	public static ArrayList<Item> getAllItem()
	{
		ArrayList<Item> items=new ArrayList<Item>();
		
		
		try {
			
			openConnection();
			
			String sql="select * from allitem;";
			PreparedStatement ps=con.prepareStatement (sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
//				Item it=new Item(rs.getInt("itemID"),rs.getString("itemName"),rs.getInt("quantity"),rs.getString("categoryName"),rs.getString("subcategoryName"),rs.getInt("originalPrice"),rs.getDate("ostartDate"),rs.getInt("sellPrice"),rs.getDate("sstartDate"),rs.getString("sizeName"),rs.getString("colorName"));
//				items.add(it);
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
		}
		
		return items;
	}
	
	
	//For Inputproduct Table
	
	public static void insertIntoItems2(String itemName,int quantity,String category,String subcategory,int originalPrice,java.sql.Date startDateOriginal, int sellPrice,java.sql.Date startDateSell, String color, String size) {

	    try {
	    	  openConnection();

	        categoryID=getCategoryID(category);
	        subcategoryID=getSubcategoryID(subcategory, categoryID);
	        originalPriceID=getOriginalPriceID(originalPrice,startDateOriginal);
	        sellPriceID=getSellPriceID(sellPrice,startDateSell);
	        sizeID=getSizeID(size);
	        colorID=getColorID(color);
	        
	        System.out.println("Category ID"+categoryID);
	        System.out.println("Subcategory ID"+subcategoryID);
	        System.out.println("Original Price"+originalPriceID);
	        System.out.println("Sell Price"+sellPriceID);
	        System.out.println("Size"+sizeID);
	        System.out.println("Color"+colorID);
	        
	        
	        
	        System.out.print(insertSizesTable(size));
	        System.out.print(insertColorsTable(color));
	        System.out.print(insertOriginalPricesTable(originalPrice,startDateOriginal));
	        System.out.print(insertSellPricesTable(sellPrice,startDateSell));
	        System.out.print(insertIntoItemTable(itemName,quantity,subcategoryID,originalPriceID,sellPriceID,sizeID,colorID));
	        
	        
	        
	       
	        
	       
	        closeConnection();
	       
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
	    
	}
	public static boolean insertIntoItemTable(String itemName,int quantity,int subCategoryID,int originalPriceID,int sellPriceID,int sizeID,int colorID) 
	{
		try {
			openConnection();
			String qForItem = "insert into item(itemName, quantity, subCategory_ID, originalPrice_ID, sellPrice_ID) values (?, ?, ?, ?, ?)";
	        PreparedStatement pi = con.prepareStatement(qForItem);
	        pi.setString(1, itemName);
	        pi.setInt(2, quantity);
	        pi.setInt(3,subCategoryID);
	        pi.setInt(4, originalPriceID);
	        pi.setInt(5, sellPriceID);
	        
	        
	        int line=pi.executeUpdate();
			closeConnection();
			
			return line>0;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	public static boolean insertOriginalPricesTable(int originalPrice,java.sql.Date startDate)
	{
		try {
			openConnection();
			String qForOriginal = "insert into originalprice(originalPrice, originalStartDate) values (?, ?)";
	        PreparedStatement po = con.prepareStatement(qForOriginal);
	        po.setInt(1, originalPrice);
//	        po.setDate(2, new java.sql.Date(startDateOriginal.getTime()));
	        po.setObject(2,startDate);
	        
	        int line=po.executeUpdate();
			closeConnection();
			
			return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();

			return false;
		}
		
        
	}
	public static boolean insertSellPricesTable(int sellPrice,java.sql.Date startDate)
	{
		try {
			openConnection();
			String qForSell = "insert into sellprice(sellPrice,sellStartDate) values(?,?)";
	        PreparedStatement po = con.prepareStatement(qForSell);
	        po.setInt(1, sellPrice);
//	        po.setDate(2, new java.sql.Date(startDateOriginal.getTime()));
	        po.setObject(2,startDate);
	        
	        int line=po.executeUpdate();
			closeConnection();
			
			return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	public static boolean insertColorsTable(String colorName)
	{
		try {
			openConnection();
			 String qForSize="insert into color(colorName) values(?)";
		     PreparedStatement psize = con.prepareStatement(qForSize);
		     psize.setString(1, colorName);
		     
		     int line=psize.executeUpdate();
				closeConnection();
				
				return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean insertSizesTable(String sizeName)
	{
		try {
			openConnection();
			 String qForSize="insert into size(sizeName) values(?)";
		     PreparedStatement psize = con.prepareStatement(qForSize);
		     psize.setString(1, sizeName);
		     
		     int line=psize.executeUpdate();
				closeConnection();
				
				return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean insertColorSize(String itemName,String color,String size)
	{
		
		
		
		try {
			openConnection();
			
			
			 String qForSize="insert into colorsizes(color_ID,size_ID,item_ID) values(?,?,?)";
		     PreparedStatement psize = con.prepareStatement(qForSize);
//		     psize.setString(1, sizeName);
		     
		     int line=psize.executeUpdate();
				closeConnection();
				
				return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public static int getItemID(String itemName) throws SQLException {
		openConnection();
		String query = "select itemID from item where itemName=?";
	    PreparedStatement ps = con.prepareStatement(query);
	    ps.setString(1, itemName);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
	        return rs.getInt("itemID");
	    }
		
		closeConnection();
		return-1;
	}
	public static int getCategoryID(String category) throws SQLException {
		openConnection();
	    String query = "select categoryID from category where categoryName=?";
	    PreparedStatement ps = con.prepareStatement(query);
	    ps.setString(1, category);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
	        return rs.getInt("categoryID");
	    }
	    
	    closeConnection();
	    return -1;
	}
	public static int getSubcategoryID(String subcategory, int categoryId) throws SQLException {
		openConnection();
	    String query = "select subcategoryID from subcategory where subcategoryName=? and category_ID=?";
	    PreparedStatement ps = con.prepareStatement(query);
	    ps.setString(1, subcategory);
	    ps.setInt(2, categoryId);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
	        return rs.getInt("subcategoryID");
	    }
	    
	    closeConnection();
	    return -1; // or throw an exception if the subcategory name and category ID combination is not found
	}
	public static int getColorID(String color) {
		try {
			openConnection();
		    String query = "select colorID from color where colorName=?";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setString(1, color);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("colorID");
		    }
		    else {
		    	insertColorsTable(color);
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return getColorID(color); // or throw an exception if the color name is not found
	}
	public static int getSizeID(String size){
		try {
			openConnection();
		    String query = "select sizeID from sizes where sizeName=?";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setString(1, size);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("sizeID");
		    }
		    else {
		    	insertSizesTable(size);
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return getSizeID(size); // or throw an exception if the size name is not found
	}
	public static int getOriginalPriceID(int originalPrice,java.sql.Date startDate){
		
		try {
			openConnection();
		    String query = "select originalPriceID from originalprice where originalPrice=?";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, originalPrice);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("originalPriceID");
		    }
		    else {
		    	insertOriginalPricesTable(originalPrice,startDate);
		    	
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return getOriginalPriceID(originalPrice,startDate); // or throw an exception if the original price value is not found
	}
	public static int getSellPriceID(int sellPrice,java.sql.Date startDate){
		try
		{
			openConnection();
		    String query = "select sellPriceID from sellprice where sellPrice = ?";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, sellPrice);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("sellPriceID");
		    }
		    else {
		    	insertSellPricesTable(sellPrice,startDate);
		    	
		    }
		    
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	   
	    return getSellPriceID(sellPrice,startDate); // or throw an exception if the sell price value is not found
	}
	
	public static boolean deleteItem(int itemID)
	{
		try {
			
			openConnection();
			
			String sql=" delete from item where itemID=?";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setInt(1,itemID);
			int line=ps.executeUpdate();
			
			return line>0;
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
			return false;
		}
		
		
	}
	
	

	public static boolean addNewCategory(String categoryName)
	{
		try {
			openConnection();
			 String qc="insert into categories(categoryName) values(?)";
		     PreparedStatement pc = con.prepareStatement(qc);
		     pc.setString(1, categoryName);
		     
		     int line=pc.executeUpdate();
			closeConnection();
			return line>0;
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		
	}
	public static boolean addNewSubcategory(String subcategoryName,String categoryName)
	{
		try {
			
			int category_ID=getCategoryID(categoryName);
			openConnection();
			
			String qc="insert into subcategory(subcategoryName,category_ID) values(?,?)";
		    PreparedStatement pc = con.prepareStatement(qc);
		    pc.setString(1, subcategoryName);
		    pc.setInt(2, category_ID);
		    
		    int line=pc.executeUpdate();
			closeConnection();
				
			return line>0;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	public static ArrayList<Category> getAllCategoryName()
	{
		ArrayList<Category> categories=new ArrayList<Category>();		
	
		try {
			
			openConnection();
			String sql="select categoryName from category";
			PreparedStatement ps=con.prepareStatement (sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Category c=new Category(rs.getString("categoryName"));
				categories.add(c);
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return categories;
	}
	public static ArrayList<Subcategory> getAllSubcategoryName()
	{
		ArrayList<Subcategory> subcategories=new ArrayList<Subcategory>();
		try {
			
			openConnection();
			String sql="select subcategoryName from subcategory";
			PreparedStatement ps=con.prepareStatement (sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Subcategory sc=new Subcategory(rs.getString("subcategoryName"));
				subcategories.add(sc);
				
				
	
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return subcategories;
	}
	public static ArrayList<Subcategory> getSubcategoryNameForComboBox(String categoryName)
	{
		ArrayList<Subcategory> subcategories=new ArrayList<Subcategory>();
		
		try {
			int categoryID=getCategoryID(categoryName);
			
			openConnection();
			String query = "select distinct subcategoryName from subcategory,category where category_ID=?";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, categoryID);
		    
		    ResultSet rs = ps.executeQuery();
		    while(rs.next())
			{
				Subcategory sc=new Subcategory(rs.getString("subcategoryName"));
				subcategories.add(sc);
				
			}
			closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return subcategories;
	}
	
	//autoComplete
	public static ArrayList<String> getMatchingCategoryNames()
	{

	    ArrayList<String> matchingCategoryNames = new ArrayList<>();

	    try {
	        openConnection();
	        String sql = "select categoryName from categories";
	        PreparedStatement ps = con.prepareStatement(sql);
//	        ps.setString(1, "%" + inputText + "%");
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            matchingCategoryNames.add(rs.getString("categoryName"));
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return matchingCategoryNames;
		
	}
	public static ArrayList<String> getMatchingSubCategoryNames(String categoryName) throws SQLException
	{
		ArrayList<String> matchingSubCategoryNames = new ArrayList<>();
		
		int categoryID=getCategoryID(categoryName);
//		int subCategoryID=getSubcategoryID(subCategoryName,categoryID);
		
	    try {
	        openConnection();
	        String sql = " select subCategoryName from subcategory where category_ID=?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, categoryID);
	        ResultSet rs = ps.executeQuery();
	        

	        while (rs.next()) {
	            matchingSubCategoryNames.add(rs.getString("subCategoryName"));
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return matchingSubCategoryNames;
		
		
		
	}
	
	
	
	
	//delete functions
	public static boolean deleteCategory(String categoryName)
	{
		try {
			
			openConnection();
			int categoryID=getCategoryID(categoryName);
			
			String sql=" delete from categories where categoryID=?";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setInt(1,categoryID);
			int line=ps.executeUpdate();
			
			return line>0;
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
			return false;
		}
	}
	
	
	
// End for Inputoroduct
	
	
	
	//For DashBoard
	
	public static String getTotalNumberOfItem()
	{
		String totalItem=null;
		try {
			openConnection();
			String sql="select count(*) from item";
			PreparedStatement ps=con.prepareStatement (sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				totalItem = rs.getString(1);
			}
			closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalItem;
	}
	
	//end For dashBoard
	
	
	//Start For Report
	public static ArrayList<Item> getItemForReport()
	{
		ArrayList<Item> reports=new ArrayList<Item>();
		try {
			openConnection();
			
			String sql="select itemID,itemName,quantity,categoryName,subcategoryName,originalPrice,originalStartDate,originalEndDate,sellPrice,sellStartDate,sellEndDate,sizeName,colorName from items,categories,subcategories,originalprices,sellprices,sizes,colors where categoryID=subcategories.category_ID  and subcategory_ID=subcategoryID and originalPrice_ID=originalPriceID and sellPrice_ID=sellPriceID and size_ID=sizeID and color_ID=colorID and quantity<=10 order by quantity";
			PreparedStatement ps=con.prepareStatement (sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
//				Item it=new Item(rs.getInt("itemID"),rs.getString("itemName"),rs.getInt("quantity"),rs.getString("categoryName"),rs.getString("subcategoryName"),rs.getInt("originalPrice"),rs.getDate("originalStartDate"),rs.getDate("originalEndDate"),rs.getInt("sellPrice"),rs.getDate("sellStartDate"),rs.getDate("sellEndDate"),rs.getString("sizeName"),rs.getString("colorName"));
////				Item items=new Item();
//				reports.add(it);
			}
			
			
			
			closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return reports;
	}
	
	
	
	//End for report
	
	//Start For Order
	public static void insertOrderDetail(String itemName,java.util.Date date,int quantity,int discountAmount)
	{
		try {
			
			int itemID=getItemID(itemName);
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static int getOrderNumber()
	{
		
		try {
			
			openConnection();
			String sql="select max(orderID) as maxorderID from orders";
			PreparedStatement ps=con.prepareStatement (sql);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
		        return rs.getInt("maxorderID");
		    }
			
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	public static ArrayList<String> getMatchingProductNames() {

	    ArrayList<String> matchingNames = new ArrayList<>();

	    try {
	        openConnection();
	        String sql = "select itemName from item";
	        PreparedStatement ps = con.prepareStatement(sql);
//	        ps.setString(1, "%" + inputText + "%");
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            matchingNames.add(rs.getString("itemname"));
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return matchingNames;
	}
	
//	-------------------------------------End for order------------------------------------------
	
	
	
//	-------------------------------------Start for crerdit------------------------------------------
	
	
	public static int getCustomerID(String customerName,String phone)
	{

		try {
			openConnection();
		    String query = "select customerID from customer where customerName=?";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setString(1, customerName);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		    	return rs.getInt("customerID");
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
		
	    return getCustomerID(customerName,phone);
	}  
	public static int getAmountID(int amount)
	{
		
		try {
			openConnection();
		    String query = "select amountID from amount where amount=?";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, amount);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		    	return rs.getInt("amountID");
		    }
		    else {
		    	insertAmountTable(amount);
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return getAmountID(amount);
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
	public static boolean insertAmountTable(int amount) {
	   int amountID=0;
	    try {
	        openConnection();
	        String query = "INSERT INTO amount(amount) VALUES (?)";
	        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        ps.setInt(1, amount);
	        int line=ps.executeUpdate();
	        
	        closeConnection();
	        return line>0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	public static boolean insertCreditTable(String customerName, String phone, String note, java.sql.Date startDate, int amount) {
	    
		try {
			int customerID = getCustomerID(customerName, phone);
	        int amountID = getAmountID(amount);
	        
	        openConnection();
	        
	        String query = "INSERT INTO credit(customer_ID, note, startDate, amount_ID) VALUES (?, ?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setInt(1, customerID);
	        ps.setString(2, note);
	        ps.setDate(3, startDate);
	        ps.setInt(4, amountID);

	        int line = ps.executeUpdate();
	        closeConnection();

	        return line > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	    }
		return false;
	}
	public static void setEndDate(int creditID)
	{
		try {
			openConnection();
			
			String sql="update credit set endDate=current_date() where creditID=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,creditID);
			int line=ps.executeUpdate();
			closeConnection();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static ArrayList<Credit> getAllCredit()
	{
		ArrayList<Credit> credits=new ArrayList<Credit>();
		
		
		try {
			
			openConnection();
			String sql="select creditID,customerName,phone,amount,note,startDate,endDate from credit,customer,amount where customer_ID=customerID and amount_ID=amountID order by endDate";
			PreparedStatement ps=con.prepareStatement (sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				 int creditID = rs.getInt("creditID");
				    String customerName = rs.getString("customerName");
				    String phone = rs.getString("phone");
				    int amount = rs.getInt("amount");
				    String note = rs.getString("note");
				    LocalDate startDate = rs.getDate("startDate").toLocalDate();

				    // Check if endDateSql is null before converting
				    LocalDate endDate = null;
				    java.sql.Date endDateSql = rs.getDate("endDate");
				    if (endDateSql != null) {
				        endDate = endDateSql.toLocalDate();
				    }

				    Credit ct = new Credit(creditID, customerName, phone, amount, note, startDate, endDate);
				    credits.add(ct);
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
		}
		
		return credits;
		
	}
	
	public static void main(String[] args) throws SQLException {
		
		System.out.print(getAllItem());
		
		
		
	}
	
}
