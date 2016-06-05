package data;

import java.util.List;

// Định nghĩa 1 "nhãn"

public class Label {
	private int numOfAttr;					// Sô thứ tự của nhãn trong tập Attr
	private String nameOfLabel;				// Tên của nhãn
	private List<Integer> haveLabel;		// Tập dương của nhãn - số thứ tự của các thể hiện
	private List<Integer> nonHaveLable;		// Tập âm của nhãn - số thứ tự của các thể hiện
	
	public Label(int numOfAttr, String nameOfLabel) {
		super();
		this.numOfAttr = numOfAttr;
		this.nameOfLabel = nameOfLabel;
	}
	
	public Label(int numOfAttr, String nameOfLabel, List<Integer> haveLabel, List<Integer> nonHaveLable) {
		super();
		this.numOfAttr = numOfAttr;
		this.nameOfLabel = nameOfLabel;
		this.haveLabel = haveLabel;
		this.nonHaveLable = nonHaveLable;
	}

	public int getNumOfAttr() {
		return numOfAttr;
	}
	public void setNumOfAttr(int numOfAttr) {
		this.numOfAttr = numOfAttr;
	}
	public String getNameOfLabel() {
		return nameOfLabel;
	}
	public void setNameOfLabel(String nameOfLabel) {
		this.nameOfLabel = nameOfLabel;
	}
	public List<Integer> getHaveLabel() {
		return haveLabel;
	}
	public void setHaveLabel(List<Integer> haveLabel) {
		this.haveLabel = haveLabel;
	}
	public List<Integer> getNonHaveLable() {
		return nonHaveLable;
	}
	public void setNonHaveLable(List<Integer> nonHaveLable) {
		this.nonHaveLable = nonHaveLable;
	}
	public boolean isContained(List<String> labels){
		for (String label : labels) {
			if(nameOfLabel == label) {
				return true;
			}
		}
		return false;
	}

	public void printLabel() {
		System.out.println(numOfAttr);
		System.out.print("name: "); System.out.println(nameOfLabel);
		System.out.print("Tap duong: "); 
		for (int i : haveLabel) {
			System.out.print(i);
			System.out.print(", ");
		}
		System.out.print("\nTap am: "); 
		for (int i : nonHaveLable) {
			System.out.print(i);
			System.out.print(", ");
		}
		System.out.print("\n");
	}
}
