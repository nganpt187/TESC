package parseData;

import java.util.ArrayList;
import java.util.List;

import data.*;

public class Data {
	
	private String name;
	private List<Attribute> attribute;
	private List<RealData> realData;
	private List<Label> labels;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Attribute> getAttribute() {
		return attribute;
	}

	public void setAttribute(List<Attribute> attribute) {
		this.attribute = attribute;
	}

	public List<RealData> getRealData() {
		return realData;
	}

	public void setRealData(List<RealData> realData) {
		this.realData = realData;
	}
		
	public void liftPrint(Data inputData) {
		System.out.println(inputData.getName());
		System.out.println("Tap Attri");
		for (Attribute obj : inputData.getAttribute()) {
			liftPrint(obj);
		}
		System.out.println("Tap data");
		for (RealData obj : inputData.getRealData()) {
			liftPrint(obj);
		}
	}
	
	public void liftPrint(RealData realDataPrint) {
		System.out.println(realDataPrint.getNumOfFeature());
		for (Feature obj : realDataPrint.getDataOfFeature()) {
			liftPrint(obj);
		}
	}
	
	public void liftPrint(Attribute attriPrint) {
		System.out.print(attriPrint.getNameOfAttr());
		System.out.print(" ");
		System.out.println(attriPrint.getTypeOfAttr());
	}
	
	public void liftPrint(Feature printFeature) {
		System.out.print(printFeature.getNumOfAttr());
		System.out.print(" ");
		System.out.println(printFeature.getValue());
	}
	
	public void liftPrint(Label labelPrint) {
		System.out.print(labelPrint.getNumOfAttr());
		System.out.print(" ");
		System.out.println(labelPrint.getNameOfLabel());
	}
	
	public void liftPrint(List<Label> labels) {
		for (Label obj : labels) {
			liftPrint(obj);
		}
	}
	
	public Feature parseFeature(String input) {
		
		String[] tmp = input.split("\\s");
		int nOF = Integer.parseInt(tmp[0]);
		float vOF = Float.parseFloat(tmp[1]);
		
		Feature outFeature = new Feature(nOF, vOF);
		
		return outFeature;
	}
	
	public RealData parseRealData(int numOfInput, String input){
		
		String dataCurrent = input.replace("{", "").replace("}", "");
		String[] tmpData = dataCurrent.split(",");
		List<Feature> features = new ArrayList<>();
		for (int i = 1;i < tmpData.length;i++){
			features.add(parseFeature(tmpData[i]));
		}
		RealData outData = new RealData(numOfInput, features);
		
		return outData;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}
		
	public List<Label> getLabelFromAttr(List<Attribute> inputAttrs){
		
		int i = 0;
		
		for(Attribute obj : inputAttrs) {
			if(obj.isLabel()) {
				Label tmp = new Label(i, obj.getNameOfAttr());
				this.labels.add(tmp);
			}
			i++;
		}
		
		return labels;
	}
	
	// Hàm trả về danh sách nhãn của 1 thể hiện
	public List<String> findLabels(RealData inputData) {
		List<String> out = new ArrayList<>();
		for (Label label : labels) {
			for (Feature rFeature : inputData.getDataOfFeature()) {
				if (rFeature.getNumOfAttr() == label.getNumOfAttr()) {
					out.add(label.getNameOfLabel());
				}
			}
		}
		
		return out;
	}
	
	public boolean isContainLabel() {
		
		return false;
	}
	
	public void print(List<String> input) {
		for (String obj : input) {
			System.out.println(obj);
		}
	}
}
