package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetListing {
	public static ArrayList<String> insertListing(String auction) {
		  
        Connection conn = null;  
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<String> arr = new ArrayList<String>();
  
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
  
            pst = conn.prepareStatement("Select * From BookListing Where AuctionName = ?");
            pst.setString(1, auction);
            rs = pst.executeQuery();
            rs.next();
            
        	arr.add(rs.getString(1)); //status
        	arr.add(rs.getString(2)); //Reserve Price
        	arr.add(rs.getString(3)); //AuctionName
        	arr.add(rs.getString(4)); //Name
        	arr.add(rs.getString(5)); //GenreName
        	arr.add(rs.getString(6)); //SubGenreName
        	arr.add(rs.getString(7)); //BookTitle
        	arr.add(rs.getString(8)); //Author
        	arr.add(rs.getString(9)); //Publisher
        	arr.add(rs.getString(10)); //PrintYear
        	arr.add(rs.getString(11)); //Description
        	arr.add(rs.getString(12)); //EndTime
        	
        	rs.close();
        	pst.close();
            
            pst = conn.prepareStatement("Select Name, Amount From Bid Where AuctionName = ? Order By Amount DESC Limit 1");
            pst.setString(1, auction); 
            rs = pst.executeQuery();
            rs.next();
            arr.add(rs.getString(1)); //Name
            arr.add(rs.getString(2)); //Amount
            
        } catch (Exception e) {
        	System.out.println("GetListing.java is not working");
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
        return arr;
	}
}
