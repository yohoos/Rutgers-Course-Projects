package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertListingDao {
	public static boolean insertListing(String author, String publisher, String bookTitle, String genre, String subgenre, String printYear, String description, 
			String reservePrice, String auctionName, String username, String endtime) {
		
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
  
            pst = conn.prepareStatement("Insert into BookListing(ReservePrice, AuctionName, Name, GenreName, SubGenreName, "
            		+ "BookTitle, Author, Publisher, PrintYear, Description, EndTime) Values(?,?,?,?,?,?,?,?,?,?,?)");  
            pst.setString(1, reservePrice);
            pst.setString(2, auctionName);
            pst.setString(3, username);
            pst.setString(4, genre);
            pst.setString(5, subgenre);
            pst.setString(6, bookTitle);
            pst.setString(7, author);
            pst.setString(8, publisher);
            pst.setString(9, printYear);
            pst.setString(10, description);
            pst.setString(11, endtime);
  
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
