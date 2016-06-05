package main;

import parseData.Data;
import parseData.FileIO;

import java.util.ArrayList;
import java.util.List;

import data.*;
//import net.sf.javaml.clustering.KMeans;

public class Main {

	public static void main(String[] args) {
		
		String fileInput = "Data/5.arff";
		
		FileIO input = new FileIO();
		
		Data inData = new Data();
		
		input.inputDataFromFile(fileInput, inData);	
		
		inData.liftPrint(inData);
		
		//Lấy các nhãn theo định nghĩa
		inData.setLabels(inData.getLabelFromAttr(inData.getAttribute()));
		
//		inData.liftPrint(inData.getLabels());
//		inData.getRealData();
		
		List<RealData> rDatas = inData.getRealData();
		
		//Xác định nhãn của các "dòng"
		/*
		for (RealData data : rDatas) {
			System.out.println(data.getNumOfFeature());
			inData.print(inData.findLabels(data));
		}
		*/
		
		// Bước 1
		//Xác định và phân loại tập thể hiện của các "nhãn"
		List<Label> labels = inData.getLabels();
		for (Label tmpLabel : labels) {
			List<Integer> haveLabel = new ArrayList<>();
			List<Integer> noHaveLabel = new ArrayList<>();
			for (RealData data : rDatas){
				List<String> nameOFLabels = inData.findLabels(data);
				if (tmpLabel.isContained(nameOFLabels)){
					haveLabel.add(data.getNumOfFeature());
				} else {
					noHaveLabel.add(data.getNumOfFeature());
				}
			}
			tmpLabel.setHaveLabel(haveLabel);
			tmpLabel.setNonHaveLable(noHaveLabel);
		}
		inData.setLabels(labels);
		for (Label sLabel : labels) {
			sLabel.printLabel();
		}

		// Bước 2
		// Phân cụm theo KMeans
		
		// Lấy dữ liệu từ theo tập đã phân từ inData
//		List<RealData> pData = inData.getRealData();
//		List<Label> testLabels = inData.getLabels();
//		for (Label sLabel : testLabels) {
//			List<RealData> T1 = new ArrayList<>();
//			;
//		}
//		KMeans testK = new KMeans();
		
		
	}

}
