package CapServer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess_Seoul {
	
	private String[] gu = new String[1000];
	private String[] dong = new String[1000];
	
	public String[] getGu() {
		return gu;
	}

	public String[] getDong() {
		return dong;
	}
	
	public void get_from_Seoul(){
		//Seoul table에서 정보를 가져오는 함수
		try {
			
			
			
			Statement orderStatement = DBConnect.connect().createStatement();
			
			ResultSet orderResultset = orderStatement
					.executeQuery("select * from seoul;");
			int iter = 0;
			while (orderResultset.next()) {
				gu[iter] = orderResultset.getString("gu");
				dong[iter] = orderResultset.getString("dong");
				
				iter++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
