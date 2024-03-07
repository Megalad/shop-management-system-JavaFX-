package model;

public class Index {
	
	private int itemID;

	public Index(int itemID) {
		super();
		this.itemID = itemID;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	@Override
	public String toString() {
		return "Index [itemID=" + itemID + "]";
	}
	
	

}
