package CapServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess_Waiting {
	
	private String shop_id;
	private String[] waiting_Arr;
	private int[] waiting_NumberArr;

	
	public String getShop_id() {
		return shop_id;
	}
	public String[] getWaiting_Arr() {
		return waiting_Arr;
	}
	public int[] getWaiting_NumberArr() {
		return waiting_NumberArr;
	}
	
	public void addDB_to_Waiting( String shop_id, String waiting_person) {
		//Shop table에 insert 하는 함수
		try {
			int waiting_number=1;
			
			
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			
			ResultSet orderResultset = orderStatement.executeQuery("select shop_id, MAX(waiting_number) as numberOfWaiting from waiting where shop_id = '"+
			shop_id +"' group by shop_id;");
			
			int count =0 ;
			while (orderResultset.next()) {
				waiting_number = orderResultset.getInt("numberOfWaiting");
				count++;
			}
			
			if(count != 0){
			
				waiting_number++;
				orderStatement.executeUpdate("insert into waiting values( '"+shop_id+ "','"+ waiting_person+ "', "+ waiting_number+");");
			}
			else{
				waiting_number = 1;
				orderStatement.executeUpdate("insert into waiting values( '"+shop_id+ "','"+ waiting_person+ "', "+ waiting_number+");");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterDB_to_Waiting(String shop_id, String waiting_person, int waiting_number){
		//Shop table에 update 하는 함수
		try {
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			
			orderStatement.executeUpdate("update waiting set  wait_person= '"
					+ waiting_person +"', waiting_number = "+waiting_number+" where shop_id = '" + shop_id + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteDB_to_Waiting(String shop_id, String waiting_person){
		//Shop table에 delete 하는 함수
		try {
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			
			orderStatement.executeUpdate("delete from waiting where shop_id= '"
					+ shop_id + "'and wait_person = '"+waiting_person + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getDB_from_Waiting(String shop_id){
		//Shop table에서 정보를 가져오는 함수
		try {
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			
			int count = 0;
			
			ResultSet orderResultset = orderStatement
					.executeQuery("select shop_id , count(*) as waitcount "
							+ " from waiting where shop_id = '"+shop_id+"'"
									+ "group by shop_id;");

			while (orderResultset.next()) {
				count = orderResultset.getInt("waitcount");
			}
			
			waiting_Arr = new String[count];
			waiting_NumberArr = new int[count];
			orderResultset = orderStatement
					.executeQuery("select wait_person , waiting_number "
							+ " from waiting where shop_id = '"+shop_id+"' order by waiting_number;");
			int iter = 0;
			while (orderResultset.next()) {
				this.waiting_Arr[iter] = orderResultset.getString("wait_person");
				this.waiting_NumberArr[iter] =orderResultset.getInt("waiting_number");
				iter++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
