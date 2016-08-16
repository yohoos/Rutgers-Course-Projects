package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateGenres {
	public static boolean update(String genre, String subgenre) {
		boolean status = false;  
        Connection conn = null;  
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
  
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
            
            // set sql trigger to deal with duplicates
            PreparedStatement tmp = conn.prepareStatement("Select * from Genre where GenreName=?");
            tmp.setString(1, genre);
            ResultSet rs = tmp.executeQuery();
            
            PreparedStatement tmp2 = conn.prepareStatement("Select * from SubGenre where SubGenreName=?");
            tmp2.setString(1, subgenre);
            ResultSet rs2 = tmp2.executeQuery();
            
            status = (rs.next() || rs2.next());
            
            tmp.close();
            tmp2.close();
            rs.close();
            rs2.close();
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
            if (pst2 != null) {
            	try {  
                    pst2.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }
        }
        return status;
	}
}
