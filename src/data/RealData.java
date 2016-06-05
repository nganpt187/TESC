package data;

import java.util.List;

import data.Feature;

// Định nghĩa 1 "thể hiện"

public class RealData{
	
	private int numOfFeature;
	private List<Feature> dataOfFeature;
	
	public RealData(int numOfFeature, List<Feature> dataOfFeature) {
		super();
		this.numOfFeature = numOfFeature;
		this.dataOfFeature = dataOfFeature;
	}
	public int getNumOfFeature() {
		return numOfFeature;
	}
	public void setNumOfFeature(int numOfFeature) {
		this.numOfFeature = numOfFeature;
	}
	public List<Feature> getDataOfFeature() {
		return dataOfFeature;
	}
	public void setDataOfFeature(List<Feature> dataOfFeature) {
		this.dataOfFeature = dataOfFeature;
	}
		
}

