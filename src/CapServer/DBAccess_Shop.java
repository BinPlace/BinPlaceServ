package CapServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import CapServer.DBConnect;

public class DBAccess_Shop {
	
	
	private String[] shop_name;
	private String[] shop_telnumber;
	private String[] shop_address;
	private String[] shop_id;
	private String[] shop_password;
	private int[] shop_numberOfTable;
	
	

	public int[] getShop_numberOfTable() {
		return shop_numberOfTable;
	}

	public String[] getShop_name() {
		return shop_name;
	}

	public String[] getShop_telnumber() {
		return shop_telnumber;
	}

	public String[] getShop_address() {
		return shop_address;
	}

	public String[] getShop_id() {
		return shop_id;
	}

	public String[] getShop_password() {
		return shop_password;
	}

	public void addDB_to_Shop( String shop_id, String shop_password,
			String shop_name, String shop_telnumber, String shop_address, int shop_numberOfTable) {
		//Shop table에 insert 하는 함수
		try {
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			
			orderStatement.executeUpdate("insert into shop values( '"+shop_id+ "','"+ shop_password
					+ "','" + shop_name + "','" + shop_telnumber + "','"+ shop_address + "',"+shop_numberOfTable +");");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterDB_to_Shop(String shop_name, String shop_telnumber, String shop_address,
			String shop_id, String shop_password, int shop_numberOfTable){
		//Shop table에 update 하는 함수
		try {
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			
			orderStatement.executeUpdate("update shop set shop_name = '"
					+ shop_name + "', shop_telnumber = '" + shop_telnumber + "'"
					+", shop_address = '" + shop_address +  "', shop_password = '" 
					+ shop_password +"', numberOfTable = "+shop_numberOfTable +"where shop_id = '" + shop_id + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteDB_to_Shop(String shop_id){
		//Shop table에 delete 하는 함수
		try {
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			
			orderStatement.executeUpdate("delete from shop where shop_id= '"
					+ shop_id + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private double[] latitude_arr;
	private double[] longdidude_arr;
	
	public double[] getLatitude_arr() {
		return latitude_arr;
	}
	
	public double[] getLongdidude_arr() {
		return longdidude_arr;
	}

	
	public void getAllDB_from_Shop(){
		//Shop table에서 정보를 가져오는 함수
		try {
			Statement orderStatement = DBConnect.connect().createStatement();
			
			int count = 0;
			
			ResultSet orderResultset = orderStatement
					.executeQuery("select count(*) as count_of_shop from shop;");

			while (orderResultset.next()) {
				count = orderResultset.getInt("count_of_shop");
			}
			
			shop_name = new String[count];
			shop_address = new String[count];
			shop_telnumber = new String[count];
			shop_numberOfTable = new int[count];
			
			latitude_arr = new double[count];
			longdidude_arr  = new double[count];
			
			orderResultset = orderStatement
					.executeQuery("select shop_name, shop_telnumber, shop_address, numberOfTable, latitude, longditude "
							+ " from shop;");
			int iter = 0;
			while (orderResultset.next()) {
				this.shop_name[iter] = orderResultset.getString("shop_name");
				this.shop_address[iter] = orderResultset.getString("shop_address");
				this.shop_telnumber[iter] = orderResultset.getString("shop_telnumber");
				this.shop_numberOfTable[iter] = orderResultset.getInt("numberOfTable");
				this.latitude_arr[iter] = orderResultset.getDouble("latitude");
				this.longdidude_arr[iter] = orderResultset.getDouble("longditude");
				
				iter++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean checkidDB(String shop_id) {
		//id가 있는지 체크하는 함수
		boolean checkstate = false;
		String tempshop_id ="";
		try {

			Statement orderStatement = DBConnect.connect().createStatement();
			ResultSet orderResultset = orderStatement
					.executeQuery("select shop_id from shop where shop_id = '"+shop_id + "';");

			while (orderResultset.next()) {
				tempshop_id = orderResultset.getString("shop_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!(shop_id.equals(tempshop_id)) ){
			checkstate = true;
		}
		return checkstate;
	}
	
	
	public boolean checkDB(String shop_id, String shop_password) { 
		// 비밀번호 확인하는 메쏘드																	
		boolean checkstate = false;
		String temppass = "";
		try {

			Statement orderStatement = DBConnect.connect().createStatement();
			ResultSet orderResultset = orderStatement
					.executeQuery("select shop_password from shop where shop_id = '"+shop_id +"';");

			while (orderResultset.next()) {
				temppass = orderResultset.getString("shop_password");
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (shop_password.equals(temppass)) {
			checkstate = true;
		}
		return checkstate;
	}
	
	private int shop_numberOfTableAlone;
	
	public int getShop_numberOfTableAlone() {
		return shop_numberOfTableAlone;
	}

	public void getOneDB_from_Shop(String shop_id){
		//Shop table에서 정보를 가져오는 함수
		try {
			Statement orderStatement = DBConnect.connect().createStatement();
			
			ResultSet orderResultset = orderStatement
					.executeQuery("select numberOfTable "
							+ " from shop where shop_id = '"+shop_id+"';");
			
			while (orderResultset.next()) {
				this.shop_numberOfTableAlone = orderResultset.getInt("numberOfTable");				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void calculateDouble(/*double up, double down, double left, double right*/){
		
		try {
		Statement orderStatement = DBConnect.connect().createStatement();
		
		int count = 0;
		ResultSet orderResultset = orderStatement
				.executeQuery("select count(*) as count_of_shop from shop; ");

		while (orderResultset.next()) {
			count = orderResultset.getInt("count_of_shop");
		}
		
		shop_name = new String[count];
		shop_address = new String[count];
		shop_telnumber = new String[count];
		shop_numberOfTable = new int[count];
		shop_id = new String[count];
		
		latitude_arr = new double[count];
		longdidude_arr  = new double[count];
		
		orderResultset = orderStatement
				.executeQuery("select shop_id, shop_name, shop_address, latitude, longditude "
						+ " from shop ;");
		/*where ((latitude < "+up+""
				+ "and latitude > "+down+") and (longditude < "+right+"and longditude >"+left+")) ;"*/
		int iter = 0;
		while (orderResultset.next()) {
			this.shop_name[iter] = orderResultset.getString("shop_name");
			this.shop_address[iter] = orderResultset.getString("shop_address");
			this.latitude_arr[iter] = orderResultset.getDouble("latitude");
			this.longdidude_arr[iter] = orderResultset.getDouble("longditude");
			this.shop_id[iter] = orderResultset.getString("shop_id");
			iter++;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	
	public void searchLocal(String inputAddress){
		
		//Shop table에서 정보를 가져오는 함수
		try {
			Statement orderStatement = DBConnect.connect().createStatement();
			
			
			
			
			int count = 0;
			ResultSet orderResultset = orderStatement
					.executeQuery("select count(*) as count_of_shop from shop where shop_address like '%"+inputAddress+"%';");

			while (orderResultset.next()) {
				count = orderResultset.getInt("count_of_shop");
			}
			shop_id = new String[count];
			shop_name = new String[count];
			shop_address = new String[count];
			shop_telnumber = new String[count];
			shop_numberOfTable = new int[count];
			

			orderResultset = orderStatement
					.executeQuery("select shop_id, shop_name, shop_telnumber, shop_address, numberOfTable "
							+ " from shop where shop_address like '%"+inputAddress+"%';");
			int iter = 0;
			
			while (orderResultset.next()) {
				
				this.shop_id[iter] = orderResultset.getString("shop_id");
				this.shop_name[iter] = orderResultset.getString("shop_name");
				this.shop_address[iter] = orderResultset.getString("shop_address");
				this.shop_telnumber[iter] = orderResultset.getString("shop_telnumber");
				this.shop_numberOfTable[iter] = orderResultset.getInt("numberOfTable");
				
				iter++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
