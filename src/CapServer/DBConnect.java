package CapServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
   private static String userName = "root";
   private static String password = "soundofmusic4242";
   private static String url = "jdbc:mysql://localhost/CapServer";
   private static Connection con = null;
   
   public static String getUserName() {
      return userName;
   }
   public static String getPassword() {
      return password;
   }
   public static String geturl() {
      return url;
   }
   public static Connection connect() {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection(DBConnect.geturl(),DBConnect.getUserName(), DBConnect.getPassword());
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } 
      
      return con;
   }
}