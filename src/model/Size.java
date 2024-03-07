package model;

public class Size {
	private String sizeName;
	
	

	public Size(String sizeName) {
		super();
		this.sizeName = sizeName;
	}
	
	
	public String getSizeName() {
		return sizeName;
	}


	@Override
	public String toString() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
