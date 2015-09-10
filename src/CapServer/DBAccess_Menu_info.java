package CapServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess_Menu_info {
	
	private String shop_id;
	private String shop_menuname;
	private int shop_menuprice;

	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	
	public String getShop_menuname() {
		return shop_menuname;
	}
	public void setShop_menuname(String shop_menuname) {
		this.shop_menuname = shop_menuname;
	}
	public int getShop_menuprice() {
		return shop_menuprice;
	}
	public void setShop_menuprice(int shop_menuprice) {
		this.shop_menuprice = shop_menuprice;
	}
	final static int SIZE = 100;
	
	private String[] shop_menuname_arr = new String[SIZE];		//각 매점별 메뉴정보들을 가져오기위해 필요한 배열 
	private int[] shop_menuprice_arr= new int[SIZE];		//
	private String[] shop_menuinfo_arr = new String[SIZE];
	
	public String[] getShop_menuname_arr() {
		return shop_menuname_arr;
	}
	public int[] getShop_menuprice_arr() {
		return shop_menuprice_arr;
	}
	public String[] getShop_menuinfo_arr() {
		return shop_menuinfo_arr;
	}
	
	public void addDB_to_Menu_info(String shop_id, String shop_menuname, int shop_menuprice, String shop_menuinfo) {
		//Menu_info table에 insert 하는 함수
		try {
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			
			orderStatement.executeUpdate("insert into menu_info values('"
					+ shop_id + "','" + shop_menuname + "'," + shop_menuprice + ",'"+shop_menuinfo+"');");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterDB_to_Menu_info(String shop_id, String shop_menuname, int shop_menuprice, String shop_menuinfo, String oldmenuname){
		//Menu_info table에 update 하는 함수
		try {
			
			Statement orderStatement = DBConnect.connect().createStatement();
			
			orderStatement = DBConnect.connect().createStatement();
			orderStatement.executeUpdate("update menu_info set shop_menuname = '"
					+ shop_menuname + "', shop_menuprice = " + shop_menuprice + ", shop_menuinfo = '"+shop_menuinfo+
					"' where shop_id = '"+ shop_id + "' and shop_menuname = '"+oldmenuname+"';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteDB_to_Menu_info(String shop_id, String shop_menuname){
		//Menu_info table에 delete 하는 함수
		try {
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			
			orderStatement.executeUpdate("delete from menu_info where shop_id= '"
					+ shop_id + "' and shop_menuname = '"+shop_menuname+"';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getDB_from_Menu_info(String shop_id){
		//Menu_info table에서 정보를 얻어오는 함수
		
		try {
			Statement orderStatement = DBConnect.connect().createStatement();
			ResultSet orderResultset = orderStatement.executeQuery("select shop_menuname, shop_menuprice, shop_menuinfo"
							+ " from menu_info where shop_id = '"+shop_id+"';");
			int iter = 0;
			while (orderResultset.next()) {
				this.shop_menuname_arr[iter] = orderResultset.getString("shop_menuname");
				this.shop_menuprice_arr[iter] = orderResultset.getInt("shop_menuprice");
				this.shop_menuinfo_arr[iter] = orderResultset.getString("shop_menuinfo");
				iter++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkMenuNameDB(String shop_id, String shop_menuname) {
		//Menu_name이 있는지 체크하는 함수
		boolean checkstate = false;
		String tempmenu_name =null;
		try {

			Statement orderStatement = DBConnect.connect().createStatement();
			ResultSet orderResultset = orderStatement
					.executeQuery("select shop_menuname from menu_info where shop_id = '"+shop_id + "'"
							+ "and shop_menuname = '"+shop_menuname+"';");

			while (orderResultset.next()) {
				tempmenu_name = orderResultset.getString("shop_menuname");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!(shop_menuname.equals(tempmenu_name)) ){
			checkstate = true;
		}
		return checkstate;
	}
	
	
}
