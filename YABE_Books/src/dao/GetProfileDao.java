package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetProfileDao {
	public static ResultSet getProfile(String username) {
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
  
        String url = "jdbc:mysql://localhost:3306/";  
        String dbName = "yabe22";  
        String driver = "com.mysql.jdbc.Driver";  
        String userName = "root";  
//        String password = "magicdust50";
        String password = "Becaused7Ka";
        
        try {
        	Class.forName(driver).newInstance();  
            conn = DriverManager  
                    .getConnection(url + dbName, userName, password);
            
            pst = conn.prepareStatement("Select * from Account where Name=?");
            pst.setString(1, username);
            rs = pst.executeQuery();
            rs.next();
            return rs;
        } catch (Exception e) {
        	return null;
        }
	}

}
