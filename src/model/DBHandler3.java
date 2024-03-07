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

public class DBHandler3 {
	
	private static Connection con;
	private static String userName="localhost";
	private static String password="12345678";
	private static int port=3306; 
	private static String dbName="sms";
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
			e.printStackTrace();
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
			e.printStackTrace();
			return false;
			}
	}
	
	//---------------For Home Page
	
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

//	-------------------------------Start Methods For Inputproduct Page-----------------------------
	
//	---------------To Get ALl Items---------------
	public static ArrayList<Item> getAllItem()
	{
		ArrayList<Item> items=new ArrayList<Item>();
		
		
		try {
			
			openConnection();
			
			String sql="select distinct * from allItemForInput order by itemID;";
			PreparedStatement ps=con.prepareStatement (sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Item it=new Item(rs.getInt("itemID"),rs.getString("itemName"),rs.getInt("quantity"),rs.getString("categoryName"),rs.getString("subcategoryName"),rs.getString("pprice"),rs.getDate("startDate"),rs.getString("sprice"),rs.getDate("sstartDate"));
				items.add(it);
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
		}
		
		return items;
	}
	
//	----------------The whole process to  insert item------------------
//-------------Get IDS
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
	public static int getSubCategoryID(int category_ID,String subcategory) throws SQLException {
		openConnection();
	    String query = "select subcategoryID from subcategory where category_ID=? and subcategoryName=?;";
	    PreparedStatement ps = con.prepareStatement(query);
	   
	    ps.setInt(1, category_ID);
	    ps.setString(2, subcategory);
	    ResultSet rs = ps.executeQuery();
	    if (rs.next()) {
	        return rs.getInt("subcategoryID");
	    }
	    
	    closeConnection();
	    return -1; 
	}
	public static int getPurchaseID(int purchasePrice){
		
		try {
			openConnection();
		    String query = "select purchasePriceID from purchaseprice where pprice=? order by purchasePriceID desc limit 1;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, purchasePrice);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("purchasePriceID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	public static int getSellPriceID(int sellPrice){
		
		try {
			openConnection();
		    String query = "select sellPriceID from sellPrice where sprice=? and sendDate is null order by sstartDate desc limit 1;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, sellPrice);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("sellPriceID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
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
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return -1;
	}
	public static int getSizeID(String size){
		try {
			openConnection();
		    String query = "select sizeID from size where sizeName=?";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setString(1, size);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("sizeID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return -1;
	}
	public static int getItemID(String itemName){
		try {
			openConnection();
			String query = "select itemID from item where itemName=? order by itemID desc limit 1;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setString(1, itemName);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("itemID");
		    }
			
			closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return-1;
	}
//-------------Testing
	public static int getPurchaseIDNew(int purchasePrice){
		
		try {
			openConnection();
		    String query = "select purchasePriceID from purchasepricenew where purchasePrice=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, purchasePrice);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("purchasePriceID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	public static int getSellIDNew(int sellPrice){
		
		try {
			openConnection();
		    String query = "select sellPriceID from sellpricenew where sellPrice=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, sellPrice);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("sellPriceID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	public static int getPriceDetail(int purchasePrice,int sellPrice,String itemName){

		try {
			
			int purchaseID=getPurchaseIDNew(purchasePrice);
			int sellID=getSellIDNew(sellPrice);
			int itemID=getItemID(itemName);
			openConnection();
		    String query = "select pricedetailID from pricedetail where purchasePrice_ID=? and sellPrice_ID=? and item_ID=? and endDate is null;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, purchaseID);
		    ps.setInt(2, sellID);
		    ps.setInt(3, itemID);
		    
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("pricedetailID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	
	public static String getCategoryNameForAutoFill(String itemName)
	{
		int itemID=getItemID(itemName);

	    try {
	        openConnection();
	        String sql = "select categoryName from category join subcategory on categoryID=category_ID join item on subcategory_ID=subcategoryID where itemID=?;\n"
	        		+ "";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1,itemID);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
		        return rs.getString("categoryName");
		    }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	public static String getSubategoryNameForAutoFill(String itemName)
	{
		int itemID=getItemID(itemName);

	    try {
	        openConnection();
	        String sql = "select subcategoryName from category join subcategory on categoryID=category_ID join item on subcategory_ID=subcategoryID where itemID=?;\n"
	        		+ "";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1,itemID);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
		        return rs.getString("subcategoryName");
		    }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	
//	-----------Insert Data
	public static boolean inserPurchasePricesTable(int purchasePrice,int quantity,int itemID)
	{
		try {
			openConnection();
			String qForOriginal = " insert into purchaseprice(startDate,quantity,pprice,item_ID) values(now(),?,?,?);";
	        PreparedStatement po = con.prepareStatement(qForOriginal);
	        po.setInt(1, quantity);
	        po.setInt(2, purchasePrice);
	        po.setInt(3, itemID);
	        
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
	public static boolean insertSellPricesTable(int sellPrice,int itemID)
	{
		try {
			openConnection();
			String qForOriginal = "insert into sellPrice(sprice, sstartDate,item_ID) values (?,now(),?)";
	        PreparedStatement po = con.prepareStatement(qForOriginal);
	        po.setInt(1, sellPrice);
	        po.setInt(2, itemID);
	        
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
	
//	------------------Testing
	public static int insertPurchasePricesTableNew(int purchasePrice)
	{
		try {
			openConnection();
			String qForOriginal = "insert into purchasepricenew(purchasePrice) values(?);";
	        PreparedStatement po = con.prepareStatement(qForOriginal);
	        po.setInt(1, purchasePrice);
	        
	        int line=po.executeUpdate();
			closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
        return getPurchaseIDNew(purchasePrice);
	}
	public static int insertSellPricesTableNew(int sellPrice)
	{
		try {
			openConnection();
			String qForOriginal = "insert into sellpricenew(sellPrice) values(?);";
	        PreparedStatement po = con.prepareStatement(qForOriginal);
	        po.setInt(1, sellPrice);
	        
	        int line=po.executeUpdate();
			closeConnection();
		}
		catch(Exception e)
		{
//			e.printStackTrace();
		}
		
        return getSellIDNew(sellPrice);
	}
	
	public static boolean insertIntoPriceDetail(int quantity,int purchasePrice,int sellPrice,int itemID,String subcategory,String category) 
	{
		
		try {
			int categoryID=getCategoryID(category);
			int subcategoryID=getSubCategoryID(categoryID,subcategory);
			
			int ppriceID=insertPurchasePricesTableNew(purchasePrice);
			int spriceID=insertSellPricesTableNew(sellPrice);
//			int itemID=insertIntoItemTable(itemName,subcategoryID);
			
			openConnection();
			String qForItem = "insert into pricedetail(startDate,quantity,purchasePrice_ID,sellPrice_ID,item_ID) values(now(),?,?,?,?);";
	        PreparedStatement pi = con.prepareStatement(qForItem);
	        pi.setInt(1, quantity); 
	        pi.setInt(2,ppriceID);
	        pi.setInt(3,spriceID);
	        pi.setInt(4,itemID);
	        
	        int line=pi.executeUpdate();
	        if(line>0)
	        {
	        	return true;
	        }
			closeConnection();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
		
		
	}
	public static int insertSizesTable(String sizeName)
	{
		try {
			openConnection();
			 String qForSize="insert into size(sizeName) values(?)";
		     PreparedStatement psize = con.prepareStatement(qForSize);
		     psize.setString(1, sizeName);
		     
		     int line=psize.executeUpdate();
			closeConnection();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			
		}
		return getSizeID(sizeName);
		
	}
	public static int insertColorsTable(String colorName)
	{
		try {
			openConnection();
			 String qForSize="insert into color(colorName) values(?)";
		     PreparedStatement psize = con.prepareStatement(qForSize);
		     psize.setString(1, colorName);
		     
		     int line=psize.executeUpdate();
				closeConnection();
				
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return getColorID(colorName);
	}
	public static boolean insertColorSizeTable(int colorID,int sizeID,int itemID) 
	{
		try {
			openConnection();
			String qForItem = " insert into colorsize(color_ID,size_ID,item_ID) values(?,?,?);";
	        PreparedStatement pi = con.prepareStatement(qForItem);
	        pi.setInt(1, colorID);
	        pi.setInt(2, sizeID);
	        pi.setInt(3, itemID);
	        
	        
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
	public static int insertIntoItemTable(String itemName,int subCategoryID) 
	{
		try {
			openConnection();
			String qForItem = "insert into item(itemName,startDate, subCategory_ID) values (?, now(), ?)";
	        PreparedStatement pi = con.prepareStatement(qForItem);
	        pi.setString(1, itemName); 
	        pi.setInt(2,subCategoryID);
	        
	        int line=pi.executeUpdate();
			closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getItemID(itemName);
		
		
	}
	
	// Testing
	
	
	
	//inserting item											insertItem("Pan","Men","Shoe",12,2000,3000,"11",)
	public static boolean insertItem(String itemName,String category,String subcategory,int quantity,int purchasePrice,int sellPrice,String size,String color)
	{
		try {
	    	  openConnection();
	    	  
	    	  int categoryID=getCategoryID(category);
	    	  int subcategoryID=getSubCategoryID(categoryID,subcategory);
		      int itemID=insertIntoItemTable(itemName,subcategoryID);
		      
		      
		      inserPurchasePricesTable(purchasePrice,quantity,itemID);
		      insertSellPricesTable(sellPrice,itemID);
		      
//		      insertColorSizeTable(colorID,sizeID,itemID);
		      
		      

		      closeConnection();
		      
		      return true;
	       
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
		return false;
	}

	// Testing
	public static boolean insertItemNew(String itemName,String category,String subcategory,int quantity,int purchasePrice,int sellPrice)
	{
		try {
	    	  openConnection();
	    	  
	    	  int categoryID=getCategoryID(category);
	    	  int subcategoryID=getSubCategoryID(categoryID,subcategory);
		      int itemID=insertIntoItemTable(itemName,subcategoryID);
		      
		      insertIntoPriceDetail(quantity,purchasePrice,sellPrice,itemID,subcategory,category);
		      closeConnection();
		      
		      return true;
	       
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
		return false;
		
		
	}

//	-------Find the Matching CategoryName
	public static ArrayList<String> getMatchingCategoryNames()
	{

	    ArrayList<String> matchingCategoryNames = new ArrayList<>();

	    try {
	        openConnection();
	        String sql = "select categoryName from category where cdeleteDate is null";
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
//	-------Find the Matching SubcategoryName
	public static ArrayList<String> getMatchingSubCategoryNames(String categoryName) throws SQLException
	{
		ArrayList<String> matchingSubCategoryNames = new ArrayList<>();
		
		int categoryID=getCategoryID(categoryName);
//		int subCategoryID=getSubcategoryID(subCategoryName,categoryID);
		
	    try {
	        openConnection();
	        String sql = " select subCategoryName from subcategory where sdeleteDate is null and category_ID=? ";
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
	
//  --------Add new Category
	public static boolean addNewCategory(String categoryName)
	{
		try {
			openConnection();
			 String qc="insert into category(categoryName,cinsertDate) values(?,now());";
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
//	---------Add new Subcategory
	public static boolean addNewSubcategory(String subcategoryName,String categoryName)
	{
		try {
			
			int category_ID=getCategoryID(categoryName);
			openConnection();
			
			String qc="insert into subcategory(subcategoryName,category_ID,sinsertDate) values(?,?,now())";
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
// 	---------Delete Category
	public static boolean deleteCategory(String categoryName)
		{
			try {
				
				openConnection();
				int categoryID=getCategoryID(categoryName);
				
				String sql=" update category set cdeleteDate=now() where categoryID=?;";
				PreparedStatement ps=con.prepareStatement (sql);
//				ResultSet rs=ps.executeQuery();
				ps.setInt(1,categoryID);
				int line=ps.executeUpdate();
				
				return line>0;
				
			}catch(Exception e)
			{
				e.printStackTrace() ;
				return false;
			}
		}
// ----------Delete Subcategory
	public static boolean deleteSubCategory(String subcategoryName,String categoryName)
	{
		try {
			
			openConnection();
			int categoryID=getCategoryID(categoryName);
			
			
			
			String sql=" update subcategory set sdeleteDate=now() where subcategoryName=? and category_ID=?;";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setString(1,subcategoryName);
			ps.setInt(2,categoryID );
			int line=ps.executeUpdate();
			
			return line>0;
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
			return false;
		}
	}
	
	
	
// ---------For Category And Subcategory
	

	
	
	public static ArrayList<Category> getAllCategoryName()
	{
		ArrayList<Category> categories=new ArrayList<Category>();		
	
		try {
			
			openConnection();
			String sql="select categoryName from category;";
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
			String sql="select subcategoryName from subcategory where sdeleteDate is null;";
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
			String query = "select distinct subcategoryName from subcategory,category where category_ID=? and sdeleteDate is null;";
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
	
	public static ArrayList<Item> getItemList()
	{

		ArrayList<Item> itemAl=new ArrayList<Item>();
		
		try {
			
			openConnection();
			String query = "select itemID,itemName,categoryName,subcategoryName from item join subcategory on subcategoryID=item.subcategory_ID join category on categoryID=subcategory.category_ID;";
		    PreparedStatement ps = con.prepareStatement(query);
		    
		    ResultSet rs = ps.executeQuery();
		    while(rs.next())
			{
				Item it=new Item(rs.getInt("itemID"),rs.getString("itemName"),rs.getString("categoryName"),rs.getString("subcategoryName"));
				itemAl.add(it);
				
			}
			closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return itemAl;
	}
	public static int getPriceDetailIDToSetEndDateNull(int item_ID)
	{
		try {
			openConnection();
		    String query = "select priceDetailID from pricedetail where endDate is null and item_ID=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, item_ID);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("priceDetailID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	
	
	public static boolean updateEndDateNullBeforeNewPrice(String itemName)
	{
		try {
			int itemID=getItemID(itemName);
			int priceDetailID=getPriceDetailIDToSetEndDateNull(itemID);
			
			openConnection();
			
			String sql="update pricedetail set endDate=now() where priceDetailID=?;";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setInt(1,priceDetailID);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
//	-------------------------------End Methods For Inputproduct Page-----------------------------
//	-------------------------------Start Methods For Orders Page-----------------------------
	public static ArrayList<Order> getAllOrder()
	{
		ArrayList<Order> orderList = new ArrayList<Order>();

	    try {
	        openConnection();
	        String sql = "select orderID,customerName,phone,orderDate,\n"
	        		+ "(select (sum((orderdetail.quantity*(sellPrice-discountAmount))))\n"
	        		+ "from orderdetail\n"
	        		+ "join pricedetail on pricedetail.item_ID=orderdetail.item_ID \n"
	        		+ "join purchasepricenew on purchasePriceID=pricedetail.purchasePrice_ID\n"
	        		+ " join sellpricenew on sellPriceID=pricedetail.sellPrice_ID \n"
	        		+ "where ((orders.orderDate>=pricedetail.startDate and orders.orderDate<pricedetail.endDate) or (orders.orderDate>=pricedetail.startDate and pricedetail.endDate is null))\n"
	        		+ "and orderID=order_ID\n"
	        		+ " ) as amount,\n"
	        		+ "remark\n"
	        		+ "from orders join customer on customerID=orders.customer_ID order by orderID desc\n"
	        		+ "; ";
	        PreparedStatement ps = con.prepareStatement(sql);
//	        ps.setString(1, "%" + inputText + "%");
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Order o=new Order(rs.getInt("orderID"),rs.getString("customerName"),rs.getString("phone"),rs.getDate("orderDate"),rs.getString("amount"),rs.getString("remark"));
	        	orderList.add(o);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return orderList;
	}
	public static ArrayList<RecentAdded> getRecentAddedItem()
	{
		ArrayList<RecentAdded> recent = new ArrayList<RecentAdded>();

	    try {
	        openConnection();
	        String sql = "select * from recentadded;";
	        PreparedStatement ps = con.prepareStatement(sql);
//	        ps.setString(1, "%" + inputText + "%");
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	RecentAdded r=new RecentAdded(rs.getInt("pricedetailID"),rs.getString("itemName"),rs.getString("categoryName"),rs.getString("subcategoryName"),rs.getInt("quantity"),rs.getInt("purchasePrice"),rs.getInt("sellPrice"),rs.getDate("Date"));
	        	recent.add(r);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return recent;
	}
	
	
	public static int getOrderNumber()
	{
		try {
			openConnection();
		    String query = "select orderID from orders order by orderID desc limit 1;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("orderID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	
	public static String getCustomerName(int orderID)
	{
		try {
			openConnection();
		    String query = "select customerName from orders join customer on customerID=customer_ID where orders.orderID=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, orderID);
		    
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getString("customerName");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return "Error";
	}
	public static String getCustomerPhone(int orderID)
	{
		try {
			openConnection();
		    String query = "select phone from orders join customer on customerID=customer_ID where orders.orderID=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, orderID);
		    
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getString("phone");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return "Error";
	}
	
	public static ArrayList<String> getMatchingItemNames()
	{

	    ArrayList<String> matchingItemNames = new ArrayList<>();

	    try {
	        openConnection();
	        String sql = "select distinct itemName from item where deleteDate is null;";
	        PreparedStatement ps = con.prepareStatement(sql);
//	        ps.setString(1, "%" + inputText + "%");
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            matchingItemNames.add(rs.getString("itemName"));
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return matchingItemNames;
		
	}

	// Insert  customer
	public static int inserCustomerTable(String customerName,String phone)
	{
		
		try {
			openConnection();
			String qForOriginal = "insert into customer(customerName,phone) values(?,?);";
	        PreparedStatement po = con.prepareStatement(qForOriginal);
	        po.setString(1, customerName);
	        po.setString(2, phone);
	        
	        int line=po.executeUpdate();
			closeConnection();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
        return getCustomerID(customerName,phone);
	}
	public static int getCustomerID(String customerName,String phone)
	{
		try {
			openConnection();
		    String query = "select customerID from customer where customerName=? and phone=?";
		    PreparedStatement ps = con.prepareStatement(query);
		    
		    ps.setString(1, customerName);
		    ps.setString(2, phone);
		    ResultSet rs = ps.executeQuery();
		    
		    if (rs.next()) {
		        return rs.getInt("customerID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	
	// insert OrderTable
	public static boolean insertOrderTable(String customerName,String phone)
	{
		
		int customerID=inserCustomerTable(customerName,phone);
		
		try {
			openConnection();
			String qForOriginal = "insert into orders(orderDate,customer_ID) values(now(),?);";
	        PreparedStatement po = con.prepareStatement(qForOriginal);
	        po.setInt(1, customerID);
	        
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
	
	// insert orderdetailTable
	
	public static boolean insertOrderDetailDiscount(int quantity,int discountAmount,int order_ID,String productName)
	{
		int item_ID=getItemID(productName);
		
		try {
			openConnection();
			String qForOriginal = " insert into orderdetail(quantity,discountAmount,order_ID,item_ID) values(?,?,?,?);";
	        PreparedStatement ps = con.prepareStatement(qForOriginal);
	        ps.setInt(1, quantity);
	        ps.setInt(2, discountAmount);
	        ps.setInt(3, order_ID);
	        ps.setInt(4, item_ID);
	        
	        
	        int line=ps.executeUpdate();
			closeConnection();
			
			return line>0;
		}
		catch(Exception e)
		{
			e.printStackTrace();

			return false;
		}
	}
	
	// delete item from OrderdetailTable
	public static boolean deleteItemFromOrderdetail(int order_ID,String itemName)
	{
		
		int itemID=getItemID(itemName);
		
		try {
			
			openConnection();
			
			String sql="delete from orderdetail where order_ID=? and item_ID=?;";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setInt(1,order_ID);
			ps.setInt(2, itemID);
			int line=ps.executeUpdate();
			
			return line>0;
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
			return false;
		}
	}
	
	// delete order from Orders
	public static boolean deleteOrdersFromOrderdetail(int orderID)
	{
		
		try {
			
			openConnection();
			
			String sql="delete from orders where orderID=?;";
			PreparedStatement ps=con.prepareStatement (sql);

			ps.setInt(1,orderID);
			int line=ps.executeUpdate();
			
			return line>0;
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
			return false;
		}
	}
	
	
	// get avaliable color
	public static int getAvaliableSizeID(String itemName,String colorName)
	{
		int itemID=getItemID(itemName);
//		"select distinct colorID from color join colorsize on colorID=color_ID where colorName=? and item_ID=?;";
		
		try {
			openConnection();
		    String query = "select distinct colorID from color join colorsize on colorID=color_ID where colorName=? and item_ID=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setString(1, colorName);
		    ps.setInt(2, itemID);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("colorID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return -1;
		
	}
	
 	public static ArrayList<Color> getAvailableColor(String itemName)
	{
//		int itemID=getItemID(itemName);
		ArrayList<Color> colors=new ArrayList<Color>();
		try {
			
			openConnection();
			String sql="select distinct colorName from colorsize join color on colorID=color_ID join item on itemID=item_ID where itemName=?";
			PreparedStatement ps=con.prepareStatement (sql);
			ps.setString(1, itemName);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				
				Color c=new Color(rs.getString("colorName"));
				colors.add(c);
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return colors;
	}
	public static ArrayList<Size> getAvailableSize(String itemName,String colorName )
	{
		int itemID=getItemID(itemName);
		int colorID=getAvaliableSizeID(itemName,colorName);
		
		ArrayList<Size> sizes=new ArrayList<Size>();
		try {
			
			openConnection();
			String sql="select sizeName from size join colorsize on size.sizeID=size_ID where colorsize.color_ID=? and colorsize.item_ID=?";
			PreparedStatement ps=con.prepareStatement (sql);
			ps.setInt(1,colorID );
			ps.setInt(2, itemID);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				
				Size c=new Size(rs.getString("sizeName"));
				sizes.add(c);
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return sizes;
	}
	
	public static ArrayList<Item> getItemList(int orderID)
	{
		ArrayList<Item> orderDetails=new ArrayList<Item>();
//		int orderID=getOrderNumber();
		try {
			
			openConnection();
			String sql="select distinct itemName,orderdetail.quantity,discountAmount,\n"
					+ "(select sellPrice from pricedetail,orders\n"
					+ "join sellpricenew on sellPriceID=pricedetail.sellPrice_ID where pricedetail.item_ID=item.itemID  order by startDate desc limit 1) as sellPrice,\n"
					+ "orderdetail.quantity*((select sellPrice from pricedetail,orders  \n"
					+ "join sellpricenew on sellPriceID=pricedetail.sellPrice_ID where item_ID=itemID  order by startDate desc limit 1) -discountAmount) as totalPrice from orderdetail\n"
					+ "join item on itemID=orderdetail.item_ID\n"
					+ "join pricedetail on pricedetail.item_ID=orderdetail.item_ID\n"
					+ "join sellpricenew on sellpricenew.sellPriceID=pricedetail.sellPrice_ID\n"
					+ "join orders on orderID=orderdetail.order_ID\n"
					+ "where ((orders.orderDate>=pricedetail.startDate and orders.orderDate<pricedetail.endDate) or (orders.orderDate>=pricedetail.startDate and pricedetail.endDate is null)) and orderdetail.order_ID=?;";
			PreparedStatement ps=con.prepareStatement (sql);
			ps.setInt(1,orderID );
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Item it=new Item(rs.getString("itemName"),rs.getInt("quantity"),rs.getString("discountAmount"),rs.getString("sellPrice"),rs.getString("totalPrice"));
				
				orderDetails.add(it);
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderDetails;
	}
	
	
	
	public static int getTotalAmountOrderDetail(int orderID)
	{
		try {
			openConnection();
		    String query = "select sum((orderdetail.quantity*(sellprice-discountAmount))) as totalAmount \n"
		    		+ "from orderdetail\n"
		    		+ "join orders on orderID=order_ID\n"
		    		+ "join pricedetail on pricedetail.item_ID=orderdetail.item_ID\n"
		    		+ "join sellpricenew on sellpriceID=sellPrice_ID\n"
		    		+ "where ((orders.orderDate>=pricedetail.startDate and orders.orderDate<pricedetail.endDate) or (orders.orderDate>=pricedetail.startDate and pricedetail.endDate is null)) and\n"
		    		+ "order_ID=?;\n"
		    		+ "";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, orderID);
		    
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("totalAmount");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return -1;
	}
	
	public static int getTotalProduct(int orderID)
	{
		try {
			openConnection();
		    String query = "select count(item_ID) as totalProduct from orderdetail where order_ID=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, orderID);
		    
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("totalProduct");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return -1;
	}
	
	public static int getTodayAmount()
	{
		try {
			openConnection();
		    String query = "select sum(orderdetail.quantity*((sellPrice-discountAmount))) as todayAmount from orderdetail,sellpricenew join pricedetail on sellPriceID=pricedetail.sellPrice_ID where pricedetail.endDate is null and pricedetail.item_ID=orderdetail.item_ID and order_ID in (select orderID from orders where orderDate=curDate());";
		    PreparedStatement ps = con.prepareStatement(query);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("todayAmount");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return -1;
	}
	public static String getBestSellingItem()
	{
		
		
		try {
			openConnection();
		    String query = "select itemName from orderdetail,item where orderdetail.item_ID=itemID group by itemID order by sum(quantity) desc limit 1;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getString("itemName");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return "Error";
	}
	public static ArrayList<Item> getBestSellingTop6Item()
	{
		
		ArrayList<Item> al=new ArrayList<Item>();
		try {
			openConnection();
		    String query = " select itemID,itemName,sum(quantity) as quantity from orderdetail,item where orderdetail.item_ID=itemID group by itemID order by sum(quantity)\n"
		    		+ " desc limit 6;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ResultSet rs = ps.executeQuery();
		    while (rs.next()) {
		        Item i=new Item(rs.getInt("itemID"),rs.getString("itemName"),rs.getInt("quantity"));
		        al.add(i);
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return al;
	}
	public static ArrayList<Item> getBestSellingTop6ItemCurrentMonth()
	{
		
		ArrayList<Item> al=new ArrayList<Item>();
		try {
			openConnection();
		    String query = "select \n"
		    		+ "itemID,\n"
		    		+ "itemName,\n"
		    		+ "sum(quantity) as quantity \n"
		    		+ "from orderdetail,item,orders \n"
		    		+ "where orderdetail.item_ID=itemID and orders.orderID=orderdetail.order_ID and year(orderDate)=year(curdate()) and month(orderDate)=month(curdate())\n"
		    		+ "group by itemID \n"
		    		+ "order by sum(quantity)\n"
		    		+ "desc limit 5;\n"
		    		+ "";
		    PreparedStatement ps = con.prepareStatement(query);
		    ResultSet rs = ps.executeQuery();
		    while (rs.next()) {
		        Item i=new Item(rs.getInt("itemID"),rs.getString("itemName"),rs.getInt("quantity"));
		        al.add(i);
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return al;
	}
	
	public static ArrayList<Item> getItemListNeedToOrder()
	{
		
		ArrayList<Item> top5ItemList=new ArrayList<Item>();
		try {
			openConnection();
		    String query = "select * from itemsneedtoorder";
		    PreparedStatement ps = con.prepareStatement(query);
		    ResultSet rs = ps.executeQuery();
		    while(rs.next()) {
		       Item it=new Item(rs.getInt("itemID"),rs.getString("itemName"),rs.getString("categoryName"),rs.getString("subcategoryName"),rs.getInt("quantity"));
		       top5ItemList.add(it);
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return top5ItemList;
	}
	
	
	
	
	public static int getTodayProfit()
	{
		try {
			openConnection();
		    String query = "select sum(orderdetail.quantity*(((select  sellPrice from sellpricenew join pricedetail on pricedetail.sellPrice_ID=sellPriceID where pricedetail.item_ID=orderdetail.item_ID and pricedetail.endDate is null) - (select purchasePrice from purchasepricenew join pricedetail on pricedetail.purchasePrice_ID=purchasePriceID where pricedetail.item_ID=orderdetail.item_ID and pricedetail.endDate is null))-(orderdetail.discountAmount))) as todayProft from orderdetail join orders on orderID=order_ID where orderDate=curdate();\n"
		    		+ "";
		    PreparedStatement ps = con.prepareStatement(query);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("todayProft");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    return -1;
	}	
	
	//	-------------------------------End Methods For Orders Page-----------------------------

//	-----------------To Delete Item---------------
	public static boolean deleteItemFromRecent(int priceDetailID)
	{
		try {
			
			openConnection();
			
			String sql="delete from pricedetail where priceDetailID=?";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setInt(1,priceDetailID);
			int line=ps.executeUpdate();
			
			closeConnection();
			return line>0;
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
			return false;
		}
		
		
	}
	public static ArrayList<OrderDetail> getOrderDetail(int orderDetailID)
	{
		ArrayList<OrderDetail> orderDetail = new ArrayList<OrderDetail>();

	    try {
	        openConnection();
	        String sql = "select orderDetailID,quantity,discountAmount,order_ID,item_ID from orderdetail where orderDetailID=?;";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1,orderDetailID);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	OrderDetail o=new OrderDetail(rs.getInt("orderDetailID"),rs.getInt("quantity"),rs.getInt("discountAmount"),rs.getInt("order_ID"),rs.getInt("item_ID"));
	        	orderDetail.add(o);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return orderDetail;
	}
	
	public static int getQuantityToUpdate(int orderDetailID)
	{
		try {
			openConnection();
		    String query = "select quantity from orderdetail where orderDetailID=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, orderDetailID);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("quantity");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	public static int getDiscountAmoutToUpdate(int orderDetailID)
	{
		try {
			openConnection();
		    String query = "select discountAmount from orderdetail where orderDetailID=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, orderDetailID);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("discountAmount");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	
	public static boolean updateQuantityFromOrderdetail(int newQuantity,int orderDetailID)
	{
		
		try {
			openConnection();
			
			String sql="update orderdetail set quantity=? where orderDetailID=?;";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setInt(1,newQuantity);
			ps.setInt(2, orderDetailID);
			int line=ps.executeUpdate();
			
			closeConnection();
			return line>0;
						
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean updateDiscountAmountFromOrderdetail(int newDiscountAmount,int orderDetailID)
	{
		
		try {
			openConnection();
			
			String sql="update orderdetail set discountAmount=? where orderDetailID=?;";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setInt(1,newDiscountAmount);
			ps.setInt(2, orderDetailID);
			int line=ps.executeUpdate();
			
			closeConnection();
			return line>0;
						
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static int getOrderDetailID(int quantity,int discountAmount,int order_ID,int item_ID)
	{
		try {
			openConnection();
		    String query = "select orderDetailID from orderdetail where quantity=? and discountAmount=? and order_ID=? and item_ID=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, quantity);
		    ps.setInt(2, discountAmount);
		    ps.setInt(3, order_ID);
		    ps.setInt(4, item_ID);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("orderDetailID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	
//	-------------------------------Start Methods For Orders Page-----------------------------
	public static ArrayList<Item> getTotalItemList()
	{
		ArrayList<Item> itemList = new ArrayList<Item>();

	    try {
	        openConnection();
	        String sql = "select * from totalItem;";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Item i=new Item(rs.getInt("itemID"),rs.getString("itemName"),rs.getString("categoryName"),rs.getString("subcategoryName"),rs.getInt("totalQuantity"),rs.getInt("purchasePrice"),rs.getInt("sellPrice"),rs.getInt("profitAmount"));
	        	itemList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return itemList;
	}
	
	public static ArrayList<Item> getPriceDetail(int itemID)
	{
		ArrayList<Item> priceDetail = new ArrayList<Item>();

	    try {
	        openConnection();
	        String sql = "select itemID,itemName,quantity,purchasePrice,sellPrice,pricedetail.startDate,pricedetail.endDate from item join pricedetail on pricedetail.item_ID=itemID join purchasepricenew on purchasePriceID=pricedetail.purchasePrice_ID join sellpricenew on sellPriceID=pricedetail.sellPrice_ID where itemID=?;\n";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, itemID);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Item i=new Item(rs.getInt("itemID"),rs.getString("itemName"),rs.getInt("quantity"),rs.getInt("purchasePrice"),rs.getInt("sellPrice"),rs.getDate("pricedetail.startDate"),rs.getDate("pricedetail.endDate"));
	        	priceDetail.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return priceDetail;
	}
	public static boolean updateItemName(String itemName,int itemID)
	{
		
		try {
			openConnection();
			
			String sql="update item set itemName=? where itemID=?;";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setString(1,itemName);
			ps.setInt(2, itemID);
			int line=ps.executeUpdate();
			
			closeConnection();
			return line>0;
						
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean updateSubategoryName(String categoryName,String SubcategoryName,int itemID)
	{
		
		
		try {
			int categoryID=getCategoryID(categoryName);
			int subcategoryID=getSubCategoryID(categoryID,SubcategoryName);
			
			openConnection();
			
			String sql="update item set subcategory_ID=? where itemID=?;";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setInt(1,subcategoryID);
			ps.setInt(2, itemID);
			int line=ps.executeUpdate();
			
			closeConnection();
			return line>0;
						
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static int getPriceDetailIDToUpdate(int purchasePrice,int sellPrice,String itemName)
	{
		int purchasePriceID=getPurchaseIDNew(purchasePrice);
		int sellPriceID=getSellIDNew(sellPrice);
		int itemID=getItemID(itemName);
		
		try {
			openConnection();
		    String query = "select priceDetailID from pricedetail where purchasePrice_ID=? and sellPrice_ID=? and item_ID=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, purchasePriceID);
		    ps.setInt(2, sellPriceID);
		    ps.setInt(3, itemID);
		    
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("priceDetailID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	public static int getQuantityFromPricedetailToUpdate(int priceDetailID)
	{
		try {
			openConnection();
		    String query = " select quantity from pricedetail where priceDetailID=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, priceDetailID);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("quantity");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	public static int getPurchasePriceIDFromPricedetailToUpdate(int priceDetailID)
	{
		try {
			openConnection();
		    String query = " select purchasePrice_ID from pricedetail where priceDetailID=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, priceDetailID);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("purchasePrice_ID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	public static int getSellPriceIDFromPricedetailToUpdate(int priceDetailID)
	{
		try {
			openConnection();
		    String query = " select sellPrice_ID from pricedetail where priceDetailID=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, priceDetailID);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("sellPrice_ID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	public static boolean updateQuantityFromPriceDetail(int newQuantity,int pricedetailID)
	{
		
		try {
			
			
			openConnection();
			
			String sql="update pricedetail set quantity=? where priceDetailID=?;";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setInt(1,newQuantity);
			ps.setInt(2, pricedetailID);
			int line=ps.executeUpdate();
			
			closeConnection();
			return line>0;
						
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean updatePurchasePriceFromPriceDetail(int newPurchasePrice,int pricedetailID)
	{
		
		try {
			
			int purchasePriceID=insertPurchasePricesTableNew(newPurchasePrice);
			openConnection();
			
			String sql="update pricedetail set purchasePrice_ID=? where priceDetailID=?;";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setInt(1,purchasePriceID);
			ps.setInt(2, pricedetailID);
			int line=ps.executeUpdate();
			
			closeConnection();
			return line>0;
						
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public static boolean updateSellPriceFromPriceDetail(int newSellPrice,int pricedetailID)
	{
		
		try {
			
			int sellPriceID=insertSellPricesTableNew(newSellPrice);
			openConnection();
			
			String sql="update pricedetail set sellPrice_ID=? where priceDetailID=?;";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setInt(1,sellPriceID);
			ps.setInt(2, pricedetailID);
			int line=ps.executeUpdate();
			
			closeConnection();
			return line>0;
						
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	//For Income
	public static ArrayList<Budget> getDailyIncomeForLineChart()
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "select day(orderDate) as day,\n"
	        		+ "(sum((orderdetail.quantity*(sellPrice-discountAmount)))) as amount\n"
	        		+ "from orderdetail\n"
	        		+ "join pricedetail on pricedetail.item_ID=orderdetail.item_ID \n"
	        		+ "join purchasepricenew on purchasePriceID=pricedetail.purchasePrice_ID\n"
	        		+ "join sellpricenew on sellPriceID=pricedetail.sellPrice_ID \n"
	        		+ "join orders on orderID=orderdetail.order_ID \n"
	        		+ "where year(orderDate)=year(now()) and month(orderDate)=month(now()) and ((orders.orderDate>=pricedetail.startDate and orders.orderDate<pricedetail.endDate) or 	(orders.orderDate>=pricedetail.startDate and pricedetail.endDate is null))\n"
	        		+ "and orderID=order_ID group by day(orderDate);\n"
	        		+ "";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("day"),rs.getInt("amount"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	public static ArrayList<Budget> getDailyIncomeForTable()
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "select orderDate,\n"
	        		+ "(sum((orderdetail.quantity*(sellPrice-discountAmount)))) as amount\n"
	        		+ "from orderdetail\n"
	        		+ "join pricedetail on pricedetail.item_ID=orderdetail.item_ID \n"
	        		+ "join purchasepricenew on purchasePriceID=pricedetail.purchasePrice_ID\n"
	        		+ "join sellpricenew on sellPriceID=pricedetail.sellPrice_ID \n"
	        		+ "join orders on orderID=orderdetail.order_ID \n"
	        		+ "where year(orderDate)=year(now()) and month(orderDate)=month(now()) and ((orders.orderDate>=pricedetail.startDate and orders.orderDate<pricedetail.endDate) or 	(orders.orderDate>=pricedetail.startDate and pricedetail.endDate is null))\n"
	        		+ "and orderID=order_ID group by orderDate;\n"
	        		+ "";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getDate("orderDate"),rs.getInt("amount"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	
	public static ArrayList<Budget> getDailyIncomeForLineChart(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call dailyIncomeForLineChart(?,?);";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("day"),rs.getInt("amount"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	public static ArrayList<Budget> getDailyIncomeForTable(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call dailyIncomeForTable(?,?)";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getDate("orderDate"),rs.getInt("amount"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	
	public static ArrayList<Budget> getMonthlyIncomeForLineChart(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call monthlyIncome(?,?) ";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("month"),rs.getInt("amount"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	public static ArrayList<Budget> getMonthlyIncomeForTable(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call monthlyIncome(?,?);";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("month"),rs.getInt("amount"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	
	public static ArrayList<Budget> getAnnualIncomeForLineChart(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call annualIncome(?,?) ";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("year"),rs.getInt("amount"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	public static ArrayList<Budget> getAnnualIncomeForTable(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call annualIncome(?,?) ";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("year"),rs.getInt("amount"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	
	//For Expense
	
	public static ArrayList<Budget> getDailyExpenseForLineChart(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call dailyExpenseForLineChart(?,?);";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("day"),rs.getInt("amount"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	public static ArrayList<Budget> getDailyExpenseForTable(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call dailyExpenseForTable(?,?)";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getDate("pricedetail.startDate"),rs.getInt("amount"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	public static ArrayList<Budget> getMonthlyExpense(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call monthlyExpense(?,?);";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("month"),rs.getInt("amount"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	public static ArrayList<Budget> getAnnualExpense(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call annualExpense(?,?) ";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("year"),rs.getInt("amount"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	
	//For Profit
	
	public static ArrayList<Budget> getDailyProfitForLineChart(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call dailyProfitForLineChart(?,?);";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("day"),rs.getInt("profit"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	public static ArrayList<Budget> getDailyProfitForTable(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call dailyProfitForTable(?,?);";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getDate("orderDate"),rs.getInt("profit"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	public static ArrayList<Budget> getMonthlyProfit(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call monthlyProfit(?,?);";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("month"),rs.getInt("profit"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	public static ArrayList<Budget> getAnnualProfit(java.sql.Date localDate,java.sql.Date localDate2)
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "call annualProfit(?,?) ";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1,localDate);
	        ps.setDate(2, localDate2);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("year"),rs.getInt("profit"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	public static ArrayList<Budget> getCurrentMonthExpense()
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "SELECT month(pricedetail.startDate) As date, SUM(pricedetail.quantity * purchaseprice) AS expense\n"
	        		+ "FROM purchasepricenew\n"
	        		+ "JOIN pricedetail ON pricedetail.purchasePrice_ID = purchasePriceID\n"
	        		+ "WHERE MONTH(pricedetail.startDate) = MONTH(CURDATE()) \n"
	        		+ "AND YEAR(pricedetail.startDate) = YEAR(CURDATE())\n"
	        		+ "GROUP BY month(pricedetail.startDate);";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("date"),rs.getInt("expense"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	public static ArrayList<Budget> getCurrentMonthIncome()
	{
		ArrayList<Budget> incomeList = new ArrayList<Budget>();

	    try {
	        openConnection();
	        String sql = "select month(orderDate) as date,\n"
	        		+ "	(sum((orderdetail.quantity*(sellPrice-discountAmount)))) as income\n"
	        		+ "	from orderdetail\n"
	        		+ "	join pricedetail on pricedetail.item_ID=orderdetail.item_ID \n"
	        		+ "	join purchasepricenew on purchasePriceID=pricedetail.purchasePrice_ID\n"
	        		+ "	join sellpricenew on sellPriceID=pricedetail.sellPrice_ID \n"
	        		+ "	join orders on orderID=orderdetail.order_ID \n"
	        		+ "	where ((orders.orderDate>=pricedetail.startDate and orders.orderDate<pricedetail.endDate) or 	(orders.orderDate>=pricedetail.startDate and pricedetail.endDate is null)) and year(orderDate)=year(curdate()) and month(orderDate)=month(curdate())\n"
	        		+ "group by month(orderDate)\n"
	        		+ "order by month(orderDate) ;";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Budget i=new Budget(rs.getInt("date"),rs.getInt("income"));
	        	incomeList.add(i);
	        }

	        closeConnection();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return incomeList;
	}
	
	
	public static boolean deleteFromItemTable(int itemID)
	{
		try {
			
			openConnection();
			
			String sql="delete from item where itemID=?;";
			PreparedStatement ps=con.prepareStatement (sql);
//			ResultSet rs=ps.executeQuery();
			ps.setInt(1,itemID);
			int line=ps.executeUpdate();
			
			closeConnection();
			return line>0;
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
			return false;
		}
	}
	
	
	public static int insertRole(String role)
	{
		try {
			
			openConnection();
			String sql="insert into role(roleName) values(?);";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, role);
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
			
		}
		return getRoleID(role);
	}
	public static boolean insertStaffDetail(String staffName,String phone,String role)
	{
		
		int roleID=getRoleID(role);
		int staffID=getStaffID(staffName,phone);
		System.out.println(staffID);
		System.out.println(roleID);
		
		try {
			openConnection();
			String sql="insert into staffdetail(role_ID,staff_ID,startDate) values(?,?,now());";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, roleID);
			ps.setInt(2, staffID);
			int line=ps.executeUpdate();
			closeConnection();
				
			return line>0;
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
			return false;
			
		}
		
	}
	public static int getRoleID(String roleName)
	{
		try {
			openConnection();
		    String query = "select roleID from role where roleName=?;";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setString(1, roleName);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("roleID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	public static int getStaffID(String roleName,String phone)
	{
//		password=Checker.digestMsg(password); update staff set password=? where staffID=?;
		try {
			openConnection();
		    String query = "select staffID from staff where staffName=? and phone=?";
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setString(1, roleName);
		    ps.setString(2, phone);
		    ResultSet rs = ps.executeQuery();
		    if (rs.next()) {
		        return rs.getInt("staffID");
		    }
		    closeConnection();
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		
	    return -1;
	}
	public static boolean updatePassword(int staffID,String newPassword)
	{
		String password=Checker.digestMsg(newPassword);
		
		
		try {
			
			openConnection();
			String sql="update staff set password=? where staffID=?;";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, password);
			ps.setInt(2, staffID);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
			return false;
		}
	}
	public static boolean insertStaff(String name,String phone,java.sql.Date dob,String password,String gender,String role)
	{
		password=Checker.digestMsg(password);

		
		try {
			
			openConnection();
			String sql="insert into staff(staffName,phone,dob,password,gender,startDate) values(?,?,?,?,?,now())";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phone);
			ps.setDate(3, dob);
			ps.setString(4, password);
			ps.setString(5, gender);
			int line=ps.executeUpdate();
			closeConnection();
			return line>0;
			
		}catch(Exception e)
		{
			e.printStackTrace() ;
			return false;
		}
	}
	public static boolean addStaff(String name,String phone,java.sql.Date dob,String password,String gender,String role)
	{
		try {
			insertStaff(name,phone,dob,password,gender,role);
			insertStaffDetail(name,phone,role);
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return false;
	}
	
	public static int login(String userName,String password)
	{
		int id=-1;
		try {
			openConnection();
			password=Checker.digestMsg(password);
			System.out.print(password);
			String sql="select staffID from staff where staffName=? and password=? and endDate is null;";
			PreparedStatement ps=con.prepareStatement (sql);
			ps. setString (1, userName); 
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				id=rs.getInt(1);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return id;
	}
	public static Staff getStaffgetStaffInformation(String staffName,String password)
	{
		password=Checker.digestMsg(password);
		Staff staff=null;
		
		
		try {
			
			openConnection();
			String sql="select roleID,roleName,staffID,staffName from staff join staffDetail on staff_ID=staffID join role on roleID=role_ID where staffDetail.endDate is null and staff.endDate is null and staffName=? and password=?;";
			PreparedStatement ps=con.prepareStatement (sql);
			ps.setString(1, staffName);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				staff=new Staff(rs.getInt("roleID"),rs.getString("roleName"),rs.getInt("staffID"),rs.getString("staffName"));
				
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return staff;
	}
	
	public static Staff getStaffgetStaffInformationTempo()
	{
		String password=Checker.digestMsg("Mg123");
		String userName="Mg Mg";
		Staff staff=null;
		
		
		try {
			
			openConnection();
			String sql="select roleID,roleName,staffID,staffName from staff join staffDetail on staff_ID=staffID join role on roleID=role_ID where staffDetail.endDate is null and staff.endDate is null and staffName=? and password=?;";
			PreparedStatement ps=con.prepareStatement (sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				staff=new Staff(rs.getInt("roleID"),rs.getString("roleName"),rs.getInt("staffID"),rs.getString("staffName"));
				
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return staff;
	}
	public static int getTotalNumberOfStaff()
	{
		try {
			openConnection();
			String sql="select count(staffID) from staff where endDate is null;";
			PreparedStatement ps=con.prepareStatement (sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				return rs.getInt("count(staffID)");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return -1;
	}
	
	public static ArrayList<Staff> getRoleName()
	{
		ArrayList<Staff> roleNames=new ArrayList<Staff>();		
		
		try {
			
			openConnection();
			String sql="select roleName from role;";
			PreparedStatement ps=con.prepareStatement (sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Staff c=new Staff(rs.getString("roleName"));
				roleNames.add(c);
			}
			closeConnection();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return roleNames;
	}
	
	
	
	public static void main(String[] args) throws Exception{
		
		System.out.print(getTotalNumberOfStaff());
	}
	}
