package model;

import java.time.LocalDate;
import java.util.Date;

public class Credit {
	
	private int creditID;
	private String customerName;
	private String phone;
	private int amount;
	private String note;
	private LocalDate startDate,endDate;
	
	
	public Credit(int creditID, String customerName, String phone, int amount, String note,
			LocalDate startDate,LocalDate endDate) {
		super();
		this.creditID = creditID;
		this.customerName = customerName;
		this.phone = phone;
		this.amount = amount;
		this.note = note;
		this.startDate = startDate;
		this.endDate=endDate;
	}
	public int getCreditID() {
		return creditID;
	}
	public void setCreditID(int creditID) {
		this.creditID = creditID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Credit [creditID=" + creditID + ", customerName=" + customerName + ", phone=" + phone + ", amount="
				+ amount + ", note=" + note + ", creditDate=" + startDate + "]";
	}
	
	
	
	

}
