package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PastBids {
	public static ArrayList<String[]> getBids(String auction) {
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
            
            pst = conn.prepareStatement("Select * from Bid where AuctionName=?");
            pst.setString(1, auction);
            rs = pst.executeQuery();
            
            ArrayList<String[]> arr = new ArrayList<String[]>();
            while (rs.next()) {
            	String[] tuple = new String[2];
            	tuple[0] = rs.getString(3);
            	tuple[1] = rs.getString(4);
            	arr.add(tuple);
            }
            return arr;
        } catch (Exception e) {
        	return null;
        }
	}
}
