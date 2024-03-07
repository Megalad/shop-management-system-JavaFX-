package model;

public class Budget {
	
	private java.sql.Date date;
	private int amount,dmy,income,expense,profit;
	
	
	public Budget(java.sql.Date date,int amount)
	{
		super();
		this.date=date;
		this.amount=amount;
	}
	
	public Budget(int dmy,int amount)
	{
		super();
		this.dmy=dmy;
		this.amount=amount;
	}
	
	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

		

	public int getDmy() {
		return dmy;
	}

	public void setDmy(int dmy) {
		this.dmy = dmy;
	}
	
	public int getIncomer() {
		return income;
	}

	public void setIncomer(int income) {
		this.income = income;
	}

	public int getExpense() {
		return expense;
	}

	public void setExpense(int expense) {
		this.expense = expense;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	
	@Override
	public String toString() {
		return "Budget [date=" + date + ", amount=" + amount + ", dmy=" + dmy + ", income=" + income + ", expense="
				+ expense + ", profit=" + profit + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	

}
