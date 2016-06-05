package parseData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.Attribute;
import data.Label;
import data.RealData;

public class FileIO {
	
	public Data inputDataFromFile(String fileInput, Data inData) {
		// Đọc dữ liệu từ file
		List<Attribute> inAttributes = new ArrayList<>();
			
		List<RealData> inRealDatas = new ArrayList<>();
			
		List<Label> inLabel = new ArrayList<>();
			
		try (BufferedReader br = new BufferedReader(new FileReader(fileInput)))
		{

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine.contains("@relation")) {
					String[] tmp = sCurrentLine.split("\\s");
					inData.setName(tmp[1]);
				}
					
				if (sCurrentLine.contains("@attribute")) {
					String[] tmp = sCurrentLine.split("\\s");
					Attribute tmpAttri = new Attribute(tmp[1], tmp[2]);
					inAttributes.add(tmpAttri);
				}
									
				if (sCurrentLine.equals("@data")){
					int numOfInput = 0;
					while((sCurrentLine = br.readLine()) != null){
						numOfInput++;						
						inRealDatas.add(inData.parseRealData(numOfInput, sCurrentLine)); 
					}
				}
			}
			inData.setAttribute(inAttributes);
			inData.setRealData(inRealDatas);
			inData.setLabels(inLabel);

		} catch (IOException e) {
			e.printStackTrace();
		} 
			
		return inData;
	}
}
