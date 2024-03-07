package model;

public class Subcategory {
	private int subcategoryID;
	private String subcategoryName;
	private String categoryName;
	private int category_ID;
	
	
	


	public Subcategory(int subcategoryID, String subcategoryName,int category_ID) {
		super();
		this.subcategoryID = subcategoryID;
		this.subcategoryName = subcategoryName;
		this.category_ID=category_ID;
	}
	
	public Subcategory(String subcategoryName)
	{
		this.subcategoryName = subcategoryName;
	}

	
	public int getSubcategoryID() {
		return subcategoryID;
	}

	public void setSubcategoryID(int subcategoryID) {
		this.subcategoryID = subcategoryID;
	}

	public String getSubcategoryName() {
		return subcategoryName;
	}

	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	
	public int getCategory_ID() {
		return category_ID;
	}

	public void setCategory_ID(int category_ID) {
		this.category_ID = category_ID;
	}

	@Override
	public String toString() {
		return getSubcategoryName();
	}

	
	

}
