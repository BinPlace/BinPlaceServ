package CapServer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess_Table {
	
	private String[] shop_id;
	private int[] table_no;
	private int[] table_state;
	private double[] x_coordinate;
	private double[] y_coordinate;
	private int[] table_size;
	
	
	public String[] getShop_id() {
		return shop_id;
	}
	public void setShop_id(String[] shop_id) {
		this.shop_id = shop_id;
	}
	public int[] getTable_no() {
		return table_no;
	}
	public void setTable_no(int[] table_no) {
		this.table_no = table_no;
	}
	public int[] getTable_state() {
		return table_state;
	}
	public void setTable_state(int[] table_state) {
		this.table_state = table_state;
	}
	public double[] getX_coordinate() {
		return x_coordinate;
	}
	public void setX_coordinate(double[] x_coordinate) {
		this.x_coordinate = x_coordinate;
	}
	public double[] getY_coordinate() {
		return y_coordinate;
	}
	public void setY_coordinate(double[] y_coordinate) {
		this.y_coordinate = y_coordinate;
	}
	public int[] getTable_size() {
		return table_size;
	}
	public void setTable_size(int[] table_size) {
		this.table_size = table_size;
	}
	
	
	public void updateDB_to_Table_Location( String shop_id, int[] table_no, int[] table_state, double[] x_coordinate, double[] y_coordinate, int[] table_size) {
		//Table_info table에 insert 하는 함수
		try {
			Statement orderStatement = DBConnect.connect().createStatement();

			orderStatement.executeUpdate("delete from table_location where shop_id = '"+ shop_id+"';");
			//일단 삭제를 다한다음에
			for(int i =0 ; i< table_no.length; i++){
				if(table_no[i] != 0){
					orderStatement.executeUpdate("insert into table_location values( '"+shop_id+"',"+table_no[i]
						+ "," +table_state[i] +", "+x_coordinate[i] +", "+y_coordinate[i]+", "+table_size[i] +" );");
				}
			}
			//모든 정보를 추가해줌

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getDB_from_Table_Location(String shop_id){
		
		try {
			Statement orderStatement = DBConnect.connect().createStatement();

			int count = 0;
			
			ResultSet orderResultset = orderStatement
					.executeQuery("select count(*) from table_location where shop_id = '"+shop_id+"';");

			while (orderResultset.next()) {
				count = orderResultset.getInt("count(*)");
			}
			
			this.table_no = new int[count];
			this.table_state = new int[count];
			this.x_coordinate = new double[count];
			this.y_coordinate = new double[count];
			this.table_size = new int [count];
			
			orderResultset = orderStatement
					.executeQuery("select table_no, table_state, x_coordinate, y_coordinate, table_size"
							+ " from table_location where shop_id = '"+shop_id+"';");
			
			int iter = 0;
			while (orderResultset.next()) {
				this.table_no[iter] = orderResultset.getInt("table_no");
				this.table_state[iter] = orderResultset.getInt("table_state");
				this.x_coordinate[iter] = orderResultset.getDouble("x_coordinate");
				this.y_coordinate[iter] = orderResultset.getDouble("y_coordinate");
				this.table_size[iter] = orderResultset.getInt("table_size");
				
				iter++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateTableState(String shop_id, int table_no, int table_state) {
		//Table_info table에 insert 하는 함수
		try {
			Statement orderStatement = DBConnect.connect().createStatement();
			
			orderStatement.executeUpdate("update table_location set table_state = "+table_state
					+ " where shop_id = '"+shop_id+"' and table_no = "+table_no+";");
			
			//모든 정보를 추가해줌

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
