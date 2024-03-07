package model;

import java.sql.Date;

public class RecentAdded {
	
	private int pricedetailID,quantity,purchasePrice,sellPrice;
	private String categoryName,subcategoryName,itemName;
	private java.sql.Date date;
	
	
	
	

	public RecentAdded(int pricedetailID,String itemName,String categoryName,String subcategoryName ,int quantity, int purchasePrice, int sellPrice, Date date) {
		super();

		this.pricedetailID = pricedetailID;
		this.itemName=itemName;
		this.categoryName=categoryName;
		this.subcategoryName=subcategoryName;
		this.quantity = quantity;
		this.purchasePrice = purchasePrice;
		this.sellPrice = sellPrice;
		this.date = date;
	}
	
	public int getPricedetailID() {
		return pricedetailID;
	}

	public void setPricedetailID(int pricedetailID) {
		this.pricedetailID = pricedetailID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	
	

	@Override
	public String toString() {
		return "RecentAdded [pricedetailID=" + pricedetailID + ", quantity=" + quantity + ", purchasePrice="
				+ purchasePrice + ", sellPrice=" + sellPrice + ", categoryName=" + categoryName + ", subcategoryName="
				+ subcategoryName + ", itemName=" + itemName + ", date=" + date + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
