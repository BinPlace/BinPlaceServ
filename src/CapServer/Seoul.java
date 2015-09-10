package CapServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Seoul {
	
	static ArrayList <SeoulCity> seoulcity;
	
	public Seoul() {
		
		seoulcity = new ArrayList <SeoulCity>();
		
		try {
			File seoul = new File("/Users/cho/workspace/BinPlaceApp/seoul.txt");
			
			FileReader fr = new FileReader(seoul);
			BufferedReader bfr = new BufferedReader(fr);
			
			String line;
			String city ="";
			String town="";
			String buf="";
			
			while((line = bfr.readLine())!=null) {
				for(int i = 0 ;i<line.length();i++){
					if(line.charAt(i) != ' '){
						buf += line.charAt(i);
					}
					else{
						city= buf;
						buf = "";
					}
				}
				town = buf;
				buf = "";
				if(town.charAt(town.length()-1) != '°è') {
					seoulcity.add(new SeoulCity(city, town));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
 	}
	
	public ArrayList <SeoulCity> getCityList() {
		return seoulcity;
	}
}

class SeoulCity {
	
	String city;
	String town;
	
	public SeoulCity(String city, String town) {
		this.city = city;
		this.town = town;
	}
	
	public String getCity () {
		return city;
	}
	
	public String getTown () {
		return town;
	}
}
