package model;

import javafx.application.Application;
import javafx.stage.Stage;

public class OrderDetail{

	private int orderDetailID,quantity,discountAmount,order_ID,item_ID;
	
	public OrderDetail(int orderDetailID, int quantity, int discountAmount, int order_ID, int item_ID) {
		super();
		this.orderDetailID = orderDetailID;
		this.quantity = quantity;
		this.discountAmount = discountAmount;
		this.order_ID = order_ID;
		this.item_ID = item_ID;
	}
	public int getOrderDetailID() {
		return orderDetailID;
	}
	public void setOrderDetailID(int orderDetailID) {
		this.orderDetailID = orderDetailID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getDiscountAmount() {
		return discountAmount;
	}


	public void setDiscountAmount(int discountAmount) {
		this.discountAmount = discountAmount;
	}


	public int getOrder_ID() {
		return order_ID;
	}


	public void setOrder_ID(int order_ID) {
		this.order_ID = order_ID;
	}


	public int getItem_ID() {
		return item_ID;
	}


	public void setItem_ID(int item_ID) {
		this.item_ID = item_ID;
	}

	

	
	@Override
	public String toString() {
		return "OrderDetail [orderDetailID=" + orderDetailID + ", quantity=" + quantity + ", discountAmount="
				+ discountAmount + ", order_ID=" + order_ID + ", item_ID=" + item_ID + "]";
	}


	public static void main(String args[])
	{
		
	}

}
