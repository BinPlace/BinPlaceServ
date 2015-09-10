package CapServer;

import java.sql.SQLException;
import java.sql.Statement;

public class MakeSeoul {

	public static void addDB_to_Seoul(String Gu, String Dong) {
		//Menu_info table에 insert 하는 함수
		try {
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			
			orderStatement.executeUpdate("insert into seoul values('"
					+ Gu + "','" +Dong+"');");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Seoul s = new Seoul();
		
		int size = Seoul.seoulcity.size();
		
		for(int i = 0; i<size;i++){
			//addDB_to_Seoul(Seoul.seoulcity.get(i).getCity(), Seoul.seoulcity.get(i).getTown());
		}
		System.out.println(size);
		
	}
	*/

}
