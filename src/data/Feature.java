package data;

// Định nghĩa 1 dữ liệu của 1 "thể hiện"

public class Feature {
	
	private int numOfAttr;
	private float value;
	
	public Feature(int numOfAttr, float value) {
		this.numOfAttr = numOfAttr;
		this.value = value;
	}
	
	public int getNumOfAttr() {
		return numOfAttr;
	}
	
	public void setNumOfAttr(int numOfAttr) {
		this.numOfAttr = numOfAttr;
	}
	
	public float getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
		
}
