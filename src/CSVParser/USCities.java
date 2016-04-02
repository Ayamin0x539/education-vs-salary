package CSVParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class USCities {
	
	public static HashMap<String, String> geopoint;
	
	public USCities() {
		
		geopoint = new HashMap<String, String>();
		String csvFile = "res/uscities.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			int i = 0;
			while ((line = br.readLine()) != null) {

			    // use comma as separator
				String[] cityInfo = line.split(cvsSplitBy);
				geopoint.put(cityInfo[0].toLowerCase(), cityInfo[1] + "," + cityInfo[2]);
				i++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static String getLocation(String city) {
		return geopoint.get(city.toLowerCase());
	}
	
	public static void main (String[] argv) {
		USCities gp = new USCities();
		System.out.println(gp.getLocation("New Haven"));
	}
}
