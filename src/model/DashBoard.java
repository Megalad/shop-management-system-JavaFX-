package model;

public class DashBoard {
	
	private String todayAmount,bestSellingItem,todayProfit;
	private int totalItem;
	
	
	

	public DashBoard(String todayAmount, String bestSellingPrice, int totalItem, String todayProfit) {
		super();
		this.todayAmount = todayAmount;
		this.bestSellingItem = bestSellingPrice;
		this.totalItem = totalItem;
		this.todayProfit = todayProfit;
	}

	public String getTodayAmount() {
		if(todayAmount==null)
			return " - ";
		return todayAmount+" Ks";
	}

	public void setTodayAmount(String todayAmount) {
		this.todayAmount = todayAmount;
	}

	public String getBestSellingItem() {
		if(bestSellingItem==null)
			return " - ";
		return bestSellingItem;
	}

	public void setBestSellingItem(String bestSellingPrice) {
		this.bestSellingItem= bestSellingPrice;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public String getTodayProfit() {
		return todayProfit;
	}

	public void setTodayProfit(String todayProfit) {
		this.todayProfit = todayProfit;
	}


	@Override
	public String toString() {
		return "DashBoard [todayAmount=" + todayAmount + ", bestSellingItem=" + bestSellingItem + ", todayProfit="
				+ todayProfit + ", totalItem=" + totalItem + "]";
	}

	public static void main(String[] args) {
		

	}

}
