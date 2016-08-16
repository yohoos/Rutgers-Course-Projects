package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PastListingsDao {
	public static ArrayList<String> getListings(String username) {
		Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
  
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "yabe22";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
        String password = "Becaused7Ka";
//        String password = "magicdust50";
        
        try {
        	Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);
            
            pst = conn.prepareStatement("Select DISTINCT AuctionName from Bid where Name=?");
            pst.setString(1, username);
            rs = pst.executeQuery();
            
            ArrayList<String> arr = new ArrayList<String>();
            while (rs.next()) {
            	arr.add(rs.getString(1));
            }
            return arr;
        } catch (Exception e) {
        	return null;
        }
	}
}
