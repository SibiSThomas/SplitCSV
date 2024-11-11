package splitCSV;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetSecurityIds {

	public static void main(String[] args) {
		
		getSecurityIds("12.csv");

	}
//	int[] securityIds =	{50374};
	 public static List<String> getSecurityIds(String csvFile){
			List<String> securityIds = new ArrayList<>();
			
			try {
				FileReader fr = new FileReader(csvFile);
				BufferedReader br = new BufferedReader(fr);
				String line;
				while((line = br.readLine()) != null) {
					String [] columns = line.split(",");
					securityIds.add(columns[2]);
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println(securityIds);
	    	return securityIds;	
	    }

}
