package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertBookDao {
	public static boolean insertBook(String isbn, String author, String publisher, String bookTitle, String genre, String printYear, String description) {
		
		boolean status = false;  
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
  
            pst = conn.prepareStatement("select * from Book where ISBN=?");  
            pst.setString(1, isbn); 
  
            rs = pst.executeQuery();  
            
            if (!rs.next()) {
            	PreparedStatement pst2 = conn.prepareStatement("Insert into Book(ISBN, Author, Publisher, Title, GenreName, Description) Values(?,?,?,?,?,?");
            	pst.setString(1, isbn);
            	pst.setString(2, author);
            	pst.setString(3, publisher);
            	pst.setString(4, genre);
            	pst.setString(5, printYear);
            	pst.setString(6, description);
            	pst2.executeUpdate();
            	pst2.close();
            }
            
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
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return status;
	}
}
