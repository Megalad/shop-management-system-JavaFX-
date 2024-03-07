package model;

import java.sql.Date;

public class Order {
	
	private int orderID;
	private String amount,totalAmount;
	private java.sql.Date orderDate;
	private String remark,customerName,phoneNumber;
	
	
	public Order(int orderID,String customerName,String phoneNumber, Date orderDate,String amount, String remark) {
		super();
		this.orderID = orderID;
		this.customerName=customerName;
		this.phoneNumber=phoneNumber;
		this.orderDate = orderDate;
		this.amount=amount;
		this.remark = remark;
		
		
	}
	public Order(int orderID)
	{
		this.orderID=orderID;
	}
	
	

	public int getOrderID() {
		return orderID;
	}


	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public java.sql.Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(java.sql.Date orderDate) {
		this.orderDate = orderDate;
	}
	
	

	public String getAmount() {
		
		return amount;
		
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	

	public String getTotalAmount() {
		
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", amount=" + amount + ", orderDate=" + orderDate + ", remark=" + remark
				+ ", customerName=" + customerName + ", phoneNumber=" + phoneNumber + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
