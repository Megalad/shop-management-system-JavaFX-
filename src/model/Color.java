package model;

public class Color {
	
	private String colorName;

	public Color(String colorName) {
		super();
		this.colorName = colorName;
	}

	public String getColorName() {
		return colorName;
	}


	@Override
	public String toString() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}






	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
