package model;

import java.time.LocalDate;
import java.util.Date;

import javafx.scene.control.Button;

public class Item {
	private int itemID;
	private String itemName;
	private int quantity;
	private int subcategory_ID;
	private int purchasePrice_ID;
	private int sellPrice_ID;
	private int size_ID;
	private int color_ID;
	private int totalQuantity,pprice,sprice,profitAmount;
	
	private java.sql.Date purchaseStartDate,startDate,endDate;
	private java.sql.Date sellStartDate;
	
	private java.sql.Date originalEndDate;
	private java.sql.Date sellEndDate;
	
	private String categoryName,subcategoryName;
	private String purchasePrice;
	private String sellPrice;
	private String discountAmount,totalPrice;
	private Category category;
	private Subcategory subcategory;
	
	private Button editBtn,deleteBtn;


	public Item(int itemID,String itemName,int quantity,String categoryName,String subcategoryName,String purchasePrice,java.sql.Date purchaseStartDate,String sellPrice,java.sql.Date sellStartDate)
	{
		super();
		this.itemID=itemID;
		this.itemName=itemName;
		this.quantity=quantity;
		this.categoryName=categoryName;
		this.subcategoryName=subcategoryName;
		this.purchasePrice=purchasePrice;
		this.purchaseStartDate=purchaseStartDate;
		this.sellPrice=sellPrice;
		this.sellStartDate=sellStartDate;
//		this.editBtn=new Button("Edit");
	}
	public Item(String itemName,int quantity,String discountAmount,String sellPrice,String totalPrice)
	{
		super();
		this.itemName=itemName;
		this.quantity=quantity;
		this.discountAmount=discountAmount;
		this.sellPrice=sellPrice;
		this.totalPrice=totalPrice;
	}
	public Item(int itemID,String itemName,String categoryName,String subcategoryName)
	{
		super();
		this.itemID=itemID;
		this.itemName=itemName;
		this.categoryName=categoryName;
		this.subcategoryName=subcategoryName;
	}
	public Item(int itemID,String itemName,Category category,Subcategory subcategory)
	{
		super();
		this.itemID=itemID;
		this.itemName=itemName;
		this.category=category;
		this.subcategory=subcategory;
	}
	public Item(int itemID,String itemName,String categoryName,String subcategoryName,int totalQuantity,int purchasePrice,int sellPrice,int profitAmount)
	{
		
		super();
		this.itemID=itemID;
		this.itemName=itemName;
		this.categoryName=categoryName;
		this.subcategoryName=subcategoryName;
		this.totalQuantity=totalQuantity;
		this.pprice=purchasePrice;
		this.sprice=sellPrice;
		this.profitAmount=profitAmount;
		
	}
	public Item(int itemID,String itemName,int quantity,int purchasePrice,int sellPrice,java.sql.Date startDate,java.sql.Date endDate)
	{
		super();
		this.itemID=itemID;
		this.itemName=itemName;
		this.quantity=quantity;
		this.pprice=purchasePrice;
		this.sprice=sellPrice;
		this.startDate=startDate;
		this.endDate=endDate;
	}
	public Item(int itemID,String itemName,String categoryName,String subcategoryName,int quantity)
	{
		super();
		this.itemID=itemID;
		this.itemName=itemName;
		this.categoryName=categoryName;
		this.subcategoryName=subcategoryName;
		this.quantity=quantity;
	}
	public Item(int itemID,String itemName,int quantity)
	{
		super();
		this.itemID=itemID;
		this.itemName=itemName;
		this.quantity=quantity;
	}
	public Item(String categoryName,String subcategoryName)
	{
		super();
		this.categoryName=categoryName;
		this.subcategoryName=subcategoryName;
	}
	public Item(int itemID)
	{
		super();
		this.itemID=itemID;
	}



	public int getItemID() {
		return itemID;
	}


	public void setItemID(int itemID) {
		this.itemID = itemID;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity() {
		this.quantity=quantity;
	}

	

	public int getSubcategory_ID() {
		return subcategory_ID;
	}


	public void setSubcategory_ID(int subcategory_ID) {
		this.subcategory_ID = subcategory_ID;
	}


	public int getPurchasePrice_ID() {
		return purchasePrice_ID;
	}


	public void setPurchasePrice_ID(int originalPrice_ID) {
		this.purchasePrice_ID = originalPrice_ID;
	}


	public int getSellPrice_ID() {
		return sellPrice_ID;
	}


	public void setSellPrice_ID(int sellPrice_ID) {
		this.sellPrice_ID = sellPrice_ID;
	}


	public int getSize_ID() {
		return size_ID;
	}


	public void setSize_ID(int size_ID) {
		this.size_ID = size_ID;
	}


	public int getColor_ID() {
		return color_ID;
	}


	public void setColor_ID(int color_ID) {
		this.color_ID = color_ID;
	}

	
	

	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public String getSubcategoryName() {
		return subcategoryName;
	}



	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}



	public String getOriginalPrice() {
		return purchasePrice+" Ks";
	}



	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}



	public String getSellPrice() {
		return sellPrice;
	}



	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Button getEditBtn() {
        return editBtn;
    }

    public void setEditBtn(Button editBtn) {
        this.editBtn = editBtn;
    }
    
    public Button getDeleteBtn()
    {
    	return deleteBtn;
    }
   
	public java.sql.Date getOriginalStartDate() {
		return purchaseStartDate;
	}



	public void setOriginalStartDate(java.sql.Date originalStartDate) {
		this.purchaseStartDate = originalStartDate;
	}



	public java.sql.Date getSellStartDate() {
		return sellStartDate;
	}



	public void setSellStartDate(java.sql.Date sellStartDate) {
		this.sellStartDate = sellStartDate;
	}



	public java.sql.Date getOriginalEndDate() {
		return originalEndDate;
	}



	public void setOriginalEndDate(java.sql.Date originalEndDate) {
		this.originalEndDate = originalEndDate;
	}



	public java.sql.Date getSellEndDate() {
		return sellEndDate;
	}



	public void setSellEndDate(java.sql.Date sellEndDate) {
		this.sellEndDate = sellEndDate;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public String getPurchasePrice() {
		return purchasePrice;
	}



	public void setDeleteBtn(Button deleteBtn) {
		this.deleteBtn = deleteBtn;
	}
	
	
	



	public java.sql.Date getPurchaseStartDate() {
		return purchaseStartDate;
	}
	public void setPurchaseStartDate(java.sql.Date purchaseStartDate) {
		this.purchaseStartDate = purchaseStartDate;
	}
	public String getDiscountAmount() {
		
		return discountAmount;
	}
	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}
	public String getTotalPrice() {
		if(totalPrice==null)
		{
			return " - ";
		}
		
		return totalPrice+" Ks";
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public int getSprice() {
		return sprice;
	}
	public void setSprice(int sprice) {
		this.sprice = sprice;
	}
	public int getProfitAmount() {
		return profitAmount;
	}
	public void setProfitAmount(int profitAmount) {
		this.profitAmount = profitAmount;
	}
	public java.sql.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}
	public java.sql.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Subcategory getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}
	@Override
	public String toString() {
		return "Item [itemID=" + itemID + ", itemName=" + itemName + ", quantity=" + quantity + ", subcategory_ID="
				+ subcategory_ID + ", purchasePrice_ID=" + purchasePrice_ID + ", sellPrice_ID=" + sellPrice_ID
				+ ", purchaseStartDate=" + purchaseStartDate + ", sellStartDate=" + sellStartDate + ", originalEndDate="
				+ originalEndDate + ", sellEndDate=" + sellEndDate + ", categoryName=" + categoryName
				+ ", subcategoryName=" + subcategoryName + ", purchasePrice=" + purchasePrice + ", sellPrice="
				+ sellPrice + ", totalPrice=" + totalPrice + "]";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
