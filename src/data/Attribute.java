package data;

// Định nghĩa 1 "thuộc tính"

public class Attribute {
	
	public Attribute(String nameOfAttr, String typeOfAttr) {
		this.nameOfAttr = nameOfAttr;
		this.typeOfAttr = typeOfAttr;
	}

	private String nameOfAttr;
	private String typeOfAttr;
	
	public void setNameOfAttr(String nameOfAttr) {
		this.nameOfAttr = nameOfAttr;
	}
	
	public String getNameOfAttr() {
		return nameOfAttr;
	}
	
	public void setTypeOfAttr(String typeOfAttr) {
		this.typeOfAttr = typeOfAttr;
	}
	
	public String getTypeOfAttr() {
		return typeOfAttr;
	}
	
	public boolean isLabel(){
		if (nameOfAttr.contains("label")) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
