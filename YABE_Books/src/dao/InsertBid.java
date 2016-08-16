package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertBid {
	public static boolean insertBid(String username, String auctionName, String startingBid) {
		boolean status = false;  
        Connection conn = null;  
        PreparedStatement pst = null;
  
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
  
            pst = conn.prepareStatement("Insert into Bid(AuctionName, Name, Amount) Values(?,?,?)");  
            pst.setString(1, auctionName);
            pst.setString(2, username);
            pst.setString(3, startingBid);
  
            pst.executeUpdate();
            status = true;
        } catch (Exception e) {
        	status = false;
        } finally {
        	if (conn != null) {  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (pst != null) {  
                try {  
                    pst.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }
        }
		
		return status;
		
	}
}
