package CapServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess_Table_info {
	
	final int TABLE_NUM = 20;
	
	private String shop_id;
	private int shop_table1;
	private int shop_table2;
	private int shop_table3;
	private int shop_table4;
	private int shop_table5;
	private int shop_table6;
	private int shop_table7;
	private int shop_table8;
	private int shop_table9;
	private int shop_table10;
	private int shop_table11;
	private int shop_table12;
	private int shop_table13;
	private int shop_table14;
	private int shop_table15;
	private int shop_table16;
	private int shop_table17;
	private int shop_table18;
	private int shop_table19;
	private int shop_table20;
	private int[] table_arrs;
	
	
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public int getShop_table1() {
		return shop_table1;
	}
	public void setShop_table1(int shop_table1) {
		this.shop_table1 = shop_table1;
	}
	public int getShop_table2() {
		return shop_table2;
	}
	public void setShop_table2(int shop_table2) {
		this.shop_table2 = shop_table2;
	}
	public int getShop_table3() {
		return shop_table3;
	}
	public void setShop_table3(int shop_table3) {
		this.shop_table3 = shop_table3;
	}
	public int getShop_table4() {
		return shop_table4;
	}
	public void setShop_table4(int shop_table4) {
		this.shop_table4 = shop_table4;
	}
	public int getShop_table5() {
		return shop_table5;
	}
	public void setShop_table5(int shop_table5) {
		this.shop_table5 = shop_table5;
	}
	public int getShop_table6() {
		return shop_table6;
	}
	public void setShop_table6(int shop_table6) {
		this.shop_table6 = shop_table6;
	}
	public int getShop_table7() {
		return shop_table7;
	}
	public void setShop_table7(int shop_table7) {
		this.shop_table7 = shop_table7;
	}
	public int getShop_table8() {
		return shop_table8;
	}
	public void setShop_table8(int shop_table8) {
		this.shop_table8 = shop_table8;
	}
	public int getShop_table9() {
		return shop_table9;
	}
	public void setShop_table9(int shop_table9) {
		this.shop_table9 = shop_table9;
	}
	public int getShop_table10() {
		return shop_table10;
	}
	public void setShop_table10(int shop_table10) {
		this.shop_table10 = shop_table10;
	}
	public int getShop_table11() {
		return shop_table11;
	}
	public void setShop_table11(int shop_table11) {
		this.shop_table11 = shop_table11;
	}
	public int getShop_table12() {
		return shop_table12;
	}
	public void setShop_table12(int shop_table12) {
		this.shop_table12 = shop_table12;
	}
	public int getShop_table13() {
		return shop_table13;
	}
	public void setShop_table13(int shop_table13) {
		this.shop_table13 = shop_table13;
	}
	public int getShop_table14() {
		return shop_table14;
	}
	public void setShop_table14(int shop_table14) {
		this.shop_table14 = shop_table14;
	}
	public int getShop_table15() {
		return shop_table15;
	}
	public void setShop_table15(int shop_table15) {
		this.shop_table15 = shop_table15;
	}
	public int getShop_table16() {
		return shop_table16;
	}
	public void setShop_table16(int shop_table16) {
		this.shop_table16 = shop_table16;
	}
	public int getShop_table17() {
		return shop_table17;
	}
	public void setShop_table17(int shop_table17) {
		this.shop_table17 = shop_table17;
	}
	public int getShop_table18() {
		return shop_table18;
	}
	public void setShop_table18(int shop_table18) {
		this.shop_table18 = shop_table18;
	}
	public int getShop_table19() {
		return shop_table19;
	}
	public void setShop_table19(int shop_table19) {
		this.shop_table19 = shop_table19;
	}
	public int getShop_table20() {
		return shop_table20;
	}
	public void setShop_table20(int shop_table20) {
		this.shop_table20 = shop_table20;
	}
	
	
	public void addDB_to_Table_info( String shop_id, int shop_table1, int shop_table2, int shop_table3, int shop_table4,
			 int shop_table5, int shop_table6, int shop_table7, int shop_table8, int shop_table9, int shop_table10, 
			 int shop_table11, int shop_table12, int shop_table13, int shop_table14, int shop_table15,
			 int shop_table16, int shop_table17, int shop_table18, int shop_table19,  int shop_table20) {
		//Table_info table에 insert 하는 함수
		try {
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			
			orderStatement.executeUpdate("insert into table_info values( '"+shop_id+ "',"+ shop_table1
					+ "," + shop_table2 + "," + shop_table3 + ","+ shop_table4 + "," + shop_table5 
					+ "," + shop_table6 + "," + shop_table7 + "," + shop_table8 + "," + shop_table9
					+ "," + shop_table10 + "," + shop_table11 + "," + shop_table12 + "," + shop_table13
					+ "," + shop_table14 + "," + shop_table15 + "," + shop_table16 + "," + shop_table17
					+ "," + shop_table18 + "," + shop_table19 + "," + shop_table20	+ ");");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void alterDB_to_Table_info(String shop_id, int table_num){
		//Table_info table에 update 하는 함수
		try {
			
			table_arrs = this.getDB_from_Table_info(shop_id);
			
			if(table_arrs[table_num-1] == 0){
				table_arrs[table_num-1] = 1;
			}
			else{
				table_arrs[table_num-1] = 0;
			}
			
			shop_table1 = table_arrs[0];
			shop_table2 = table_arrs[1];
			shop_table3 = table_arrs[2];
			shop_table4 = table_arrs[3];
			shop_table5 = table_arrs[4];
			shop_table6 = table_arrs[5];
			shop_table7 = table_arrs[6];
			shop_table8 = table_arrs[7];
			shop_table9 = table_arrs[8];
			shop_table10 = table_arrs[9];
			
			shop_table11 = table_arrs[10];
			shop_table12 = table_arrs[11];
			shop_table13 = table_arrs[12];
			shop_table14 = table_arrs[13];
			shop_table15 = table_arrs[14];
			shop_table16 = table_arrs[15];
			shop_table17 = table_arrs[16];
			shop_table18 = table_arrs[17];
			shop_table19 = table_arrs[18];
			shop_table20 = table_arrs[19];
			
			
			
			
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			
			orderStatement.executeUpdate("update table_info set shop_table1 = " +shop_table1 +", shop_table2= " + shop_table2
					+", shop_table3= " + shop_table3+", shop_table4= " + shop_table4+", shop_table5= " + shop_table5
					+", shop_table6= " + shop_table6+", shop_table7= " + shop_table7+", shop_table8= " + shop_table8
					+", shop_table9= " + shop_table9+", shop_table10= " + shop_table10+", shop_table11= " + shop_table11
					+", shop_table12= " + shop_table12+", shop_table13= " + shop_table13+", shop_table14= " + shop_table14
					+", shop_table15= " + shop_table15+", shop_table16= " + shop_table16+", shop_table17= " + shop_table17
					+", shop_table18= " + shop_table19+", shop_table20= " + shop_table20+" where shop_id = '" + shop_id + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteDB_to_Table_info(String shop_id){
		//Table_info table에 delete 하는 함수
		try {
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			
			orderStatement.executeUpdate("delete from table_info where shop_id= '"
					+ shop_id + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int[] getDB_from_Table_info(String shop_id){
		//Table_info table에서 정보를 가져오는 함수
		 
		table_arrs = null;
		try {
			Statement orderStatement = DBConnect.connect()
					.createStatement();
			ResultSet orderResultset = orderStatement
					.executeQuery("select shop_table1, shop_table2, shop_table3, shop_table4,"
							+ "shop_table5, shop_table6, shop_table7, shop_table8, shop_table9,"
							+ "shop_table10, shop_table11, shop_table12, shop_table13, shop_table14,"
							+ "shop_table15, shop_table16, shop_table17, shop_table18, shop_table19,"
							+ "shop_table20" + " from table_info where shop_id = '"+shop_id+"';");

			while (orderResultset.next()) {
				this.shop_table1 = orderResultset.getInt("shop_table1");
				this.shop_table2 = orderResultset.getInt("shop_table2");
				this.shop_table3 = orderResultset.getInt("shop_table3");
				this.shop_table4 = orderResultset.getInt("shop_table4");
				this.shop_table5 = orderResultset.getInt("shop_table5");
				this.shop_table6 = orderResultset.getInt("shop_table6");
				this.shop_table7 = orderResultset.getInt("shop_table7");
				this.shop_table8 = orderResultset.getInt("shop_table8");
				this.shop_table9 = orderResultset.getInt("shop_table9");
				this.shop_table10 = orderResultset.getInt("shop_table10");
				
				this.shop_table11 = orderResultset.getInt("shop_table11");
				this.shop_table12 = orderResultset.getInt("shop_table12");
				this.shop_table13 = orderResultset.getInt("shop_table13");
				this.shop_table14 = orderResultset.getInt("shop_table14");
				this.shop_table15 = orderResultset.getInt("shop_table15");
				this.shop_table16 = orderResultset.getInt("shop_table16");
				this.shop_table17 = orderResultset.getInt("shop_table17");
				this.shop_table18 = orderResultset.getInt("shop_table18");
				this.shop_table19 = orderResultset.getInt("shop_table19");
				this.shop_table20 = orderResultset.getInt("shop_table20");
			}
			table_arrs = new int[TABLE_NUM];
			
			table_arrs[0] =  shop_table1;
			table_arrs[1] =  shop_table2;
			table_arrs[2] =  shop_table3;
			table_arrs[3] =  shop_table4;
			table_arrs[4] =  shop_table5;
			table_arrs[5] =  shop_table6;
			table_arrs[6] =  shop_table7;
			table_arrs[7] =  shop_table8;
			table_arrs[8] =  shop_table9;
			table_arrs[9] =  shop_table10;
			
			
			table_arrs[10] =  shop_table11;
			table_arrs[11] =  shop_table12;
			table_arrs[12] =  shop_table13;
			table_arrs[13] =  shop_table14;
			table_arrs[14] =  shop_table15;
			table_arrs[15] =  shop_table16;
			table_arrs[16] =  shop_table17;
			table_arrs[17] =  shop_table18;
			table_arrs[18] =  shop_table19;
			table_arrs[19] =  shop_table20;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table_arrs;
	}
	
	
	
}
